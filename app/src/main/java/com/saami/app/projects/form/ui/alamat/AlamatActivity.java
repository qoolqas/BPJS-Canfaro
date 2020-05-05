package com.saami.app.projects.form.ui.alamat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saami.app.projects.form.HomeActivity;
import com.saami.app.projects.form.R;
import com.saami.app.projects.form.model.alamat.get.AlamatResponse;
import com.saami.app.projects.form.model.alamat.get.DataItem;
import com.saami.app.projects.form.ui.monitoring.Laporan;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public class AlamatActivity extends AppCompatActivity {
    RecyclerView rv;
    AlamatAdapter alamatAdapter;
    AlamatViewModel alamatViewModel;
    List<DataItem> alamat = new ArrayList<>();
    ProgressBar pb;
    FloatingActionButton fab;
    ImageView cari, refresh,filter;
    EditText edtCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat);
        alamatViewModel = ViewModelProviders.of(this).get(AlamatViewModel.class);

        view();
        getData();
    }


    private void view() {
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlamatActivity.this, AlamatAddActivity.class);
                startActivity(intent);
            }
        });
        final ArrayList<String> kota = new ArrayList<>();
        kota.add("SIBOLGA");
        kota.add("TAPANULI TENGAH");
        kota.add("TAPANULI UTARA");
        kota.add("HUMBANG HASUNDUTAN");
        filter = findViewById(R.id.filter);
        cari = findViewById(R.id.icocari);
        refresh = findViewById(R.id.refresh);
        edtCari = findViewById(R.id.edt_cari);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(AlamatActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_filter_alamat);

                final SearchableSpinner spncab = dialog.findViewById(R.id.spinner_cab);
                ArrayAdapter adaptercab = new ArrayAdapter(AlamatActivity.this, android.R.layout.simple_spinner_item, kota);
                adaptercab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spncab.setAdapter(adaptercab);
                spncab.setTitle("");
                Button lanjut = dialog.findViewById(R.id.btn_lanjut);
                lanjut.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(AlamatActivity.this, AlamatSearch.class);
                        intent.putExtra("tujuan", spncab.getSelectedItem().toString());
                        startActivity(intent);
                    }
                });


                dialog.show();

            }
        });
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlamatActivity.this, AlamatSearch.class);
                intent.putExtra("tujuan", edtCari.getText().toString());
                startActivity(intent);
                Log.d("search", "refresh");
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlamatActivity.this, HomeActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("state", "7");
                startActivity(intent);
            }
        });
        pb = findViewById(R.id.pb);
        rv = findViewById(R.id.rv);
        pb.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AlamatActivity.this.getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this, 0));
        rv.setItemAnimator(new DefaultItemAnimator());
        alamatAdapter = new AlamatAdapter(alamat, AlamatActivity.this, AlamatActivity.this);
        rv.setAdapter(alamatAdapter);
    }

    private void getData() {
        alamatViewModel.liveGet().observe(this, new Observer<AlamatResponse>() {
            @Override
            public void onChanged(AlamatResponse alamatResponse) {
                alamat.clear();
                alamat.addAll(alamatResponse.getData());
                rv.setAdapter(alamatAdapter);
                alamatAdapter.notifyDataSetChanged();
                pb.setVisibility(View.GONE);
            }
        });
    }

}
