package com.saami.app.projects.form.ui.monitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.saami.app.projects.form.R;

public class Laporan extends AppCompatActivity {
    TextView petugas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        petugas = findViewById(R.id.txtPetugas);
    }
}
