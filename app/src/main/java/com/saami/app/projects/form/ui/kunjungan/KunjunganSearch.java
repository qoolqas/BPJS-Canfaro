package com.saami.app.projects.form.ui.kunjungan;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saami.app.projects.form.R;
import com.saami.app.projects.form.adapterFormList;
import com.saami.app.projects.form.model.badanusaha.BadanUsahaSearchResponse;
import com.saami.app.projects.form.model.badanusaha.DataItem;
import com.saami.app.projects.form.ui.FixViewModel;

import java.util.ArrayList;
import java.util.List;

public class KunjunganSearch extends AppCompatActivity {
    String nameQuery;
    private adapterFormList adapter;
    private List<DataItem> getBu = new ArrayList<>();
    private FixViewModel fixViewModel;
    RecyclerView listParkir;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunjungan_search);
        fixViewModel = ViewModelProviders.of(this).get(FixViewModel.class);
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);
        listParkir = findViewById(R.id.list_form);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(KunjunganSearch.this.getApplicationContext());
        listParkir.setLayoutManager(mLayoutManager);
        listParkir.addItemDecoration(new DividerItemDecoration(this, 0));
        listParkir.setItemAnimator(new DefaultItemAnimator());
        adapter = new adapterFormList(KunjunganSearch.this, KunjunganSearch.this, getBu);
        listParkir.setAdapter(adapter);
        try {
            nameQuery = getIntent().getStringExtra("query");
            getDataByNama();
        }catch (Exception e){

        }


    }

        private void getDataByNama() {
        fixViewModel.liveSearch(nameQuery).observe(this, new Observer<BadanUsahaSearchResponse>() {
            @Override
            public void onChanged(BadanUsahaSearchResponse kunjunganGetResponse) {
                getBu.clear();
                if (kunjunganGetResponse != null) {
                    getBu.addAll(kunjunganGetResponse.getData());
                    listParkir.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);


                } else {
                    Toast.makeText(KunjunganSearch.this, "Data Tidak ditemukan", Toast.LENGTH_LONG).show();
                    getBu.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });


    }
}
