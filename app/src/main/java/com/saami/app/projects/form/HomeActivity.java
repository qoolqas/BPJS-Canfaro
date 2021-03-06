package com.saami.app.projects.form;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.saami.app.projects.form.ui.alamat.AlamatActivity;
import com.saami.app.projects.form.ui.kunjungan.InsertDataBPJS;
import com.saami.app.projects.form.ui.kunjungan.ListView_BPJS;
import com.saami.app.projects.form.ui.kunjungan.ListView_BPJS_Draft;
import com.saami.app.projects.form.ui.monitoring.Laporan;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {
    ImageView newdoc, posteddoc, draftdoc, laporandoc, alamatdoc;
    String state = "0";
    String waktu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Permissions.check(this/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
            @Override
            public void onGranted() {
                // do your task.
            }
        });
        try {
            state = getIntent().getExtras().getString("state");
            if (state.equals("6")) {
                startActivity(new Intent(HomeActivity.this, ListView_BPJS.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            } else if (state.equals("7")) {
                startActivity(new Intent(HomeActivity.this, AlamatActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        } catch (Exception e) {
            state = "0";
            Log.d("state fail", " not good ");
        }


        view();

    }

    private void view() {
        newdoc = findViewById(R.id.ico_newdoc);
        newdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, InsertDataBPJS.class);
                i.putExtra("home", "200");
                i.putExtra("edit", "0");
                i.putExtra("view", "0");
                startActivity(i);
            }
        });


        draftdoc = findViewById(R.id.ico_draftdoc);
        draftdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ListView_BPJS_Draft.class);
                startActivity(i);
            }
        });

        posteddoc = findViewById(R.id.ico_posteddoc);
        posteddoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ListView_BPJS.class);
                startActivity(i);
            }
        });
        alamatdoc = findViewById(R.id.ico_alamat);
        alamatdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AlamatActivity.class);
                startActivity(intent);
            }
        });

        laporandoc = findViewById(R.id.ico_laporan);
        laporandoc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(HomeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_filter_laporan);

                ArrayList<String> kcab = new ArrayList<String>();
                kcab.add("KC.Sibolga");
                kcab.add("KC.Jakarta");
                kcab.add("KC.Sumatera");
//                ArrayList<String> kptugas = new ArrayList<String>();
//                kptugas.add("Sahat Martua");
//                kptugas.add("Yanto");
//                kptugas.add("Mulyo");
                ArrayList<String> kwaktu = new ArrayList<String>();
                kwaktu.add("1 Minggu Terakhir");
                kwaktu.add("1 Bulan Terakhir");
                kwaktu.add("1 tahun Terakhir");

                SearchableSpinner spncab = dialog.findViewById(R.id.spinner_cab);
                ArrayAdapter adaptercab = new ArrayAdapter(HomeActivity.this, android.R.layout.simple_spinner_item, kcab);
                adaptercab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spncab.setAdapter(adaptercab);
                spncab.setTitle("");

                final TextView spnptugas = dialog.findViewById(R.id.spinner_petugas);
                spnptugas.setText(getIntent().getStringExtra("firstName") + " " + getIntent().getStringExtra("lastName"));
//                SearchableSpinner spnptugas = dialog.findViewById(R.id.spinner_petugas);
//                ArrayAdapter adapterptugas = new ArrayAdapter(HomeActivity.this, android.R.layout.simple_spinner_item, kptugas);
//                adapterptugas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spnptugas.setAdapter(adapterptugas);
//                spnptugas.setTitle("");

                final SearchableSpinner spnwaktu = dialog.findViewById(R.id.spinner_waktu);
                ArrayAdapter adapterwaktu = new ArrayAdapter(HomeActivity.this, android.R.layout.simple_spinner_item, kwaktu);
                adapterwaktu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnwaktu.setAdapter(adapterwaktu);
                spnwaktu.setTitle("");
                spnwaktu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View v,
                                               int pos, long id) {
                        waktu = String.valueOf(spnwaktu.getSelectedItem());
                        if (waktu.equals("1 Minggu Terakhir")) {
                            waktu = "week";
                        } else if (waktu.equals("1 Bulan Terakhir")) {
                            waktu = "month";
                        } else {
                            waktu = "year";
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {


                    }
                });


                Button tampilkan = dialog.findViewById(R.id.btn_tampilkan);
                tampilkan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(HomeActivity.this, Laporan.class);
                        i.putExtra("name", spnptugas.getText().toString());
                        i.putExtra("waktu", waktu);
                        startActivity(i);
                    }
                });


                dialog.show();
            }
        });


    }
}
