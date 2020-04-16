package com.saami.app.projects.form;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.saami.app.projects.form.sqlite.FormData;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity
{
    ImageView newdoc, posteddoc, draftdoc,laporandoc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Permissions.check(this/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
            @Override
            public void onGranted() {
                // do your task.
            }
        });


        view();

    }

    private void view()
    {
        newdoc = findViewById(R.id.ico_newdoc);
        newdoc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(HomeActivity.this, InsertDataBPJS.class);
                i.putExtra("home","200");
                i.putExtra("edit","0");
                i.putExtra("view","0");
                startActivity(i);
            }
        });


        draftdoc = findViewById(R.id.ico_draftdoc);
        draftdoc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(HomeActivity.this,ListView_BPJS_Draft.class);
                startActivity(i);
            }
        });

        posteddoc = findViewById(R.id.ico_posteddoc);
        posteddoc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(HomeActivity.this,ListView_BPJS.class);
                startActivity(i);
            }
        });

        laporandoc = findViewById(R.id.ico_laporan);
        laporandoc.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view)
            {
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
                kwaktu.add("1 Bulan Terakhir");
                kwaktu.add("6 Bulan Terakhir");
                kwaktu.add("1 tahun Terakhir");

                SearchableSpinner spncab = dialog.findViewById(R.id.spinner_cab);
                ArrayAdapter adaptercab = new ArrayAdapter(HomeActivity.this, android.R.layout.simple_spinner_item, kcab);
                adaptercab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spncab.setAdapter(adaptercab);
                spncab.setTitle("");

                TextView spnptugas = dialog.findViewById(R.id.spinner_petugas);
                spnptugas.setText(getIntent().getStringExtra("firstName") + " " +getIntent().getStringExtra("lastName"));
//                SearchableSpinner spnptugas = dialog.findViewById(R.id.spinner_petugas);
//                ArrayAdapter adapterptugas = new ArrayAdapter(HomeActivity.this, android.R.layout.simple_spinner_item, kptugas);
//                adapterptugas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spnptugas.setAdapter(adapterptugas);
//                spnptugas.setTitle("");

                SearchableSpinner spnwaktu = dialog.findViewById(R.id.spinner_waktu);
                ArrayAdapter adapterwaktu = new ArrayAdapter(HomeActivity.this, android.R.layout.simple_spinner_item, kwaktu);
                adapterwaktu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnwaktu.setAdapter(adapterwaktu);
                spnwaktu.setTitle("");


                Button tampilkan = dialog.findViewById(R.id.btn_tampilkan);
                tampilkan.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent i = new Intent(HomeActivity.this, Laporan.class);
                        startActivity(i);
                    }
                });


                dialog.show();
            }
        });


    }
}
