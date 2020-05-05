package com.saami.app.projects.form.ui.alamat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.saami.app.projects.form.R;
import com.saami.app.projects.form.model.alamat.get.AlamatResponse;
import com.saami.app.projects.form.model.alamat.get.DataItem;

import java.util.ArrayList;
import java.util.List;

public class AlamatSearch extends AppCompatActivity {
    String tujuan;
    RecyclerView rv;
    AlamatAdapter alamatAdapter;
    AlamatViewModel alamatViewModel;
    List<DataItem> alamat = new ArrayList<>();
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat_search);
        alamatViewModel = ViewModelProviders.of(this).get(AlamatViewModel.class);
        pb = findViewById(R.id.pbS);
        rv = findViewById(R.id.rvS);
        pb.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AlamatSearch.this.getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this, 0));
        rv.setItemAnimator(new DefaultItemAnimator());
        alamatAdapter = new AlamatAdapter(alamat, AlamatSearch.this, AlamatSearch.this);
        rv.setAdapter(alamatAdapter);
        try {
            tujuan = getIntent().getStringExtra("tujuan");
            Log.d("tujua", tujuan);
            getDataSearch();
        }catch (Exception e){
            Log.d("catch", "tujuan");
        }

    }
    private void getDataSearch() {
        alamatViewModel.liveSearch(tujuan).observe(this, new Observer<AlamatResponse>() {
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
