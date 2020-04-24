package com.saami.app.projects.form.ui.alamat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saami.app.projects.form.ProviderFormList;
import com.saami.app.projects.form.R;
import com.saami.app.projects.form.adapterFormList;
import com.saami.app.projects.form.model.alamat.DataItem;

import java.util.List;

public class AlamatAdapter extends RecyclerView.Adapter<AlamatAdapter.ViewHolder>  {
    private List<DataItem> alamat;
    private Activity activity;
    private Context context;
    LayoutInflater mInflater;

    public AlamatAdapter( List<DataItem> data,Activity act ,Context ctx) {
        this.alamat = data;
        this.activity = act;
        this.context = ctx;
        mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public AlamatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_listview_alamat, parent, false);

        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlamatAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaBu, provinsi, kota,kecamatan, alamat;
        ImageView show, edit, delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaBu = itemView.findViewById(R.id.nama_badan_usaha);
            provinsi = itemView.findViewById(R.id.provinsi);

        }
    }
}
