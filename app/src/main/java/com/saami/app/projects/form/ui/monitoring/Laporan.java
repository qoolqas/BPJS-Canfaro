package com.saami.app.projects.form.ui.monitoring;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saami.app.projects.form.R;
import com.saami.app.projects.form.model.monitoring.CurrentTarget;
import com.saami.app.projects.form.model.monitoring.KunjunganItem;
import com.saami.app.projects.form.model.monitoring.MonitoringResponse;

import java.util.ArrayList;
import java.util.List;

public class Laporan extends AppCompatActivity {
    TextView petugas, txttotalKunjungan, txttotalRekruitmen;
    RecyclerView rv;
    MonitoringViewModel monitoringViewModel;
    List<KunjunganItem> monitoring = new ArrayList<>();
    AdapterLaporan adapterLaporan;
    CurrentTarget data;
    String waktu = "";

    private ProgressBar pb;
    int tKunjungan = 0;
    int tRekruitmen = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        petugas = findViewById(R.id.txtPetugas);
        txttotalKunjungan = findViewById(R.id.totalKunjungan);
        txttotalRekruitmen = findViewById(R.id.totalRekruitmen);
        petugas.setText("Petugas RO : " + getIntent().getStringExtra("name"));
        monitoringViewModel = ViewModelProviders.of(this).get(MonitoringViewModel.class);
        waktu = getIntent().getStringExtra("waktu");
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);

        view();
        getData();
    }

    private void view() {
        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Laporan.this.getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this, 0));
        rv.setItemAnimator(new DefaultItemAnimator());
        adapterLaporan = new AdapterLaporan(monitoring, this, this, data);
        rv.setAdapter(adapterLaporan);
    }

    private void getData() {
        monitoringViewModel.liveGet(waktu).observe(this, new Observer<MonitoringResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(MonitoringResponse monitoringResponse) {
                monitoring.clear();
                monitoring.addAll(monitoringResponse.getData().getKunjungan());
                rv.setAdapter(adapterLaporan);
                adapterLaporan.notifyDataSetChanged();
                pb.setVisibility(View.GONE);


                for (int i = 0; i < monitoring.size(); i++) {
                    tKunjungan = Integer.parseInt(String.valueOf(monitoring.get(i).getTotalKunjungan()));
                    tRekruitmen = Integer.parseInt(String.valueOf(monitoring.get(i).getTotalRecruitment()));
                }
                txttotalRekruitmen.setText("Total Rekruitmen : " + tRekruitmen);
                txttotalKunjungan.setText("Total Kunjungan : " + tKunjungan);
            }
        });
    }
}
