package com.saami.app.projects.form.ui.alamat;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.saami.app.projects.form.InsertDataBPJS;
import com.saami.app.projects.form.ProviderFormList;
import com.saami.app.projects.form.R;
import com.saami.app.projects.form.SharedPrefManager;
import com.saami.app.projects.form.adapterFormList;
import com.saami.app.projects.form.connection.Client;
import com.saami.app.projects.form.connection.Service;
import com.saami.app.projects.form.model.alamat.AlamatResponse;
import com.saami.app.projects.form.model.alamat.DataItem;
import com.saami.app.projects.form.model.alamat.delete.AlamatDeleteResponse;
import com.saami.app.projects.form.model.kunjungan.KunjunganGetResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlamatAdapter extends RecyclerView.Adapter<AlamatAdapter.ViewHolder> {
    private List<DataItem> alamat;
    private Activity activity;
    private Context context;
    LayoutInflater mInflater;
    SharedPrefManager sharedPrefManager;

    public AlamatAdapter(List<DataItem> data, Activity act, Context ctx) {
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
    public void onBindViewHolder(@NonNull AlamatAdapter.ViewHolder holder, final int position) {
        holder.namaBu.setText(alamat.get(position).getNamaBadanUsaha());
        holder.provinsi.setText(alamat.get(position).getProvinsi());
        holder.kota.setText(alamat.get(position).getKota());
        holder.kecamatan.setText(alamat.get(position).getKecamatan());
        holder.alamat.setText(alamat.get(position).getAlamat());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("Do you want to edit this data?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(context, AlamatAddActivity.class);
                                i.putExtra("edit", "1");
                                i.putExtra("data", alamat.get(position));
                                context.startActivity(i);
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
     holder.show.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i = new Intent(activity, InsertDataBPJS.class);
             i.putExtra("view", "1");
             i.putExtra("data", alamat.get(position));
             activity.startActivity(i);
         }
     });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to delete this data?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                sharedPrefManager = new SharedPrefManager(v.getContext());
                                String token = sharedPrefManager.getSpToken();
                                Service service = Client.getClient().create(Service.class);
                                Call<AlamatDeleteResponse> delete = service.deleteAlamat("Bearer " + token, alamat.get(position).getId());
                                delete.enqueue(new Callback<AlamatDeleteResponse>() {
                                    @Override
                                    public void onResponse(Call<AlamatDeleteResponse> call, Response<AlamatDeleteResponse> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(activity.getApplicationContext(), v.getContext().getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                                            alamat.remove(alamat.get(position));
                                            notifyDataSetChanged();

                                        }else {
                                            Toast.makeText(activity.getApplicationContext(), v.getContext().getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<AlamatDeleteResponse> call, Throwable t) {
                                        Toast.makeText(activity.getApplicationContext(), v.getContext().getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return alamat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaBu, provinsi, kota, kecamatan, alamat;
        ImageView show, edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaBu = itemView.findViewById(R.id.nama_badan_usaha);
            provinsi = itemView.findViewById(R.id.provinsi);
            kota = itemView.findViewById(R.id.kota);
            kecamatan = itemView.findViewById(R.id.kecamatan);
            alamat = itemView.findViewById(R.id.alamat);

            show = itemView.findViewById(R.id.show_forms);
            edit = itemView.findViewById(R.id.edit_forms);
            delete = itemView.findViewById(R.id.delete_forms);
        }
    }
}
