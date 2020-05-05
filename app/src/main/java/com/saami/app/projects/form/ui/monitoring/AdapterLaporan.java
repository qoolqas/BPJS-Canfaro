package com.saami.app.projects.form.ui.monitoring;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saami.app.projects.form.R;
import com.saami.app.projects.form.model.monitoring.CurrentTarget;
import com.saami.app.projects.form.model.monitoring.KunjunganItem;

import java.util.List;


public class AdapterLaporan extends RecyclerView.Adapter<AdapterLaporan.ViewHolder>{
    private List<KunjunganItem> monitoring;
    private CurrentTarget data;
    private Activity activity;
    private Context context;
    LayoutInflater mInflater;

    public AdapterLaporan(List<KunjunganItem> monitoring, Activity activity, Context context, CurrentTarget data1) {
        this.monitoring = monitoring;
        this.activity = activity;
        this.context = context;
        this.data = data1;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdapterLaporan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_listview_laporan, parent, false);

        return new ViewHolder(itemLayoutView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterLaporan.ViewHolder holder, int position) {
        holder.nomor.setText(String.valueOf(position+1));
        holder.targetKunjungan.setText(String.valueOf(monitoring.get(position).getTargetKunjungan()));
        holder.targetRekruitmen.setText(String.valueOf(monitoring.get(position).getTargetRecruitment()));
        holder.realisasiKunjungan.setText(String.valueOf(monitoring.get(position).getTotalKunjungan()));
        holder.realisasiRekruitmen.setText(String.valueOf(monitoring.get(position).getTotalRecruitment()));
        //====================
        Double Tkunjungan = Double.parseDouble(String.valueOf(holder.targetKunjungan.getText()));
        Double Trekruitmen = Double.parseDouble(String.valueOf(holder.targetRekruitmen.getText()));
        Double Rkunjungan = Double.parseDouble(String.valueOf(holder.realisasiKunjungan.getText()));
        Double Rrekruitmen = Double.parseDouble(String.valueOf(holder.realisasiRekruitmen.getText()));
        double kunjunganD = Rkunjungan/Tkunjungan * 100;
        double rekruitmenD = Rrekruitmen/Trekruitmen * 100;
        holder.kunjungan.setText(kunjunganD + " %");
        holder.rekruitmen.setText(rekruitmenD + " %");

    }

    @Override
    public int getItemCount() {
        return monitoring.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomor,targetKunjungan,targetRekruitmen,realisasiKunjungan,realisasiRekruitmen,kunjungan,rekruitmen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomor = itemView.findViewById(R.id.txtNomor);
            targetKunjungan = itemView.findViewById(R.id.txtTargetKunjungan);
            targetRekruitmen = itemView.findViewById(R.id.txtTargetRekruitmen);
            realisasiKunjungan = itemView.findViewById(R.id.txtRealisasiKunjungan);
            realisasiRekruitmen = itemView.findViewById(R.id.txtRealisasiRekrutmen);
            kunjungan = itemView.findViewById(R.id.txtKunjungan);
            rekruitmen = itemView.findViewById(R.id.txtRekruitmen);

        }
    }
}
