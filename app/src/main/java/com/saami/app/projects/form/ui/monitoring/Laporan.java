package com.saami.app.projects.form.ui.monitoring;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import com.saami.app.projects.form.model.monitoring.Data;
import com.saami.app.projects.form.model.monitoring.KunjunganItem;
import com.saami.app.projects.form.model.monitoring.MonitoringResponse;

import java.util.ArrayList;
import java.util.List;

public class Laporan extends AppCompatActivity {
    TextView petugas;
    RecyclerView rv;
    MonitoringViewModel monitoringViewModel;
    List<KunjunganItem> monitoring = new ArrayList<>();
    AdapterLaporan adapterLaporan;
    CurrentTarget data;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        petugas = findViewById(R.id.txtPetugas);
        petugas.setText("Petugas RO : "+getIntent().getStringExtra("name"));
        monitoringViewModel = ViewModelProviders.of(this).get(MonitoringViewModel.class);


        view();
        getData();
    }
    private void view() {
        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Laporan.this.getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this, 0));
        rv.setItemAnimator(new DefaultItemAnimator());
        adapterLaporan = new AdapterLaporan(monitoring, this, this,data );
        rv.setAdapter(adapterLaporan);
    }
    private void getData() {
        monitoringViewModel.liveGet().observe(this, new Observer<MonitoringResponse>() {
            @Override
            public void onChanged(MonitoringResponse monitoringResponse) {
                monitoring.clear();
                monitoring.addAll(monitoringResponse.getData().getKunjungan());
                rv.setAdapter(adapterLaporan);
                adapterLaporan.notifyDataSetChanged();
            }
        });
    }
}
