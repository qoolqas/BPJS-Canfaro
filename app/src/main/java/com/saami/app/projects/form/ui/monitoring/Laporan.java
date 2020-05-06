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
    TextView petugas, txttotalKunjungan, txttotalRekruitmen, txttargetKunjungan, txttargetRekruitmen,txtKunjungan, txtRekruitmen;
    RecyclerView rv;
    MonitoringViewModel monitoringViewModel;
    List<KunjunganItem> monitoring = new ArrayList<>();
    AdapterLaporan adapterLaporan;
    CurrentTarget data;
    String waktu = "";

    private ProgressBar pb;
    int tKunjungan = 0;
    int tRekruitmen = 0;
    int targetKunjungan = 0;
    int targetRekruitmen = 0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        petugas = findViewById(R.id.txtPetugas);
        txttotalKunjungan = findViewById(R.id.totalKunjungan);
        txttotalRekruitmen = findViewById(R.id.totalRekruitmen);
        txttargetKunjungan = findViewById(R.id.txtTargetKunjungan);
        txttargetRekruitmen = findViewById(R.id.txtTargetRekruitmen);
        txtKunjungan = findViewById(R.id.txtKunjunganA);
        txtRekruitmen = findViewById(R.id.txtRekruitmenA);

        petugas.setText("Petugas RO : " + getIntent().getStringExtra("name"));
        monitoringViewModel = ViewModelProviders.of(this).get(MonitoringViewModel.class);
//        waktu = getIntent().getStringExtra("waktu");
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
                    tKunjungan += monitoring.get(i).getTotalKunjungan();
                    tRekruitmen += monitoring.get(i).getTotalRecruitment();
                    targetKunjungan += monitoring.get(i).getTargetKunjungan();
                    targetRekruitmen += monitoring.get(i).getTargetRecruitment();


                }
                double persenKunjungan = tKunjungan/monitoring.size();
                double persenRekruitmen = tRekruitmen/monitoring.size();
                txttotalRekruitmen.setText(String.valueOf(tRekruitmen));
                txttotalKunjungan.setText(String.valueOf(tKunjungan));
                txttargetKunjungan.setText(String.valueOf(targetKunjungan));
                txttargetRekruitmen.setText(String.valueOf(targetRekruitmen));
                txtKunjungan.setText(persenKunjungan + " %");
                txtRekruitmen.setText(persenRekruitmen + " %");
            }
        });
    }
}
