package com.saami.app.projects.form;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.saami.app.projects.form.connection.Client;
import com.saami.app.projects.form.connection.Service;
import com.saami.app.projects.form.model.kunjungan.DataItem;
import com.saami.app.projects.form.model.kunjungan.KunjunganGetResponse;
import com.saami.app.projects.form.model.kunjungan.delete.KunjunganDeleteResponse;
import com.saami.app.projects.form.sqlite.DBDataSource;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class adapterFormList extends RecyclerView.Adapter<adapterFormList.ViewHolder> {

    Context mContext;
    Activity mActivity;
    LayoutInflater mInflater;
    private List<DataItem> kunjungan;
    SharedPrefManager sharedPrefManager;

    public adapterFormList( Context ctx, Activity act, List<DataItem> kunjungan) {
        this.mContext = ctx;
        this.mActivity = act;
        mInflater = LayoutInflater.from(mContext);
        this.kunjungan = kunjungan;
    }

    @Override
    public adapterFormList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_listview_formdata, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

//        datasource = new DBDataSource(mActivity);
//
//        final ProviderFormList item = assList.get(position);
//        viewHolder.nama.setText(item.getF_KP_NAMA());
//        viewHolder.phone.setText("Phone Number : " + item.getF_KP_PHONE());
//        viewHolder.badanusaha.setText(item.getF_BDN_USH());
//        viewHolder.alamat.setText(item.getF_ALAMAT());
//        viewHolder.email.setText(item.getF_EMAIL());
//        viewHolder.tglkunjungan.setText(item.getF_TGL_KUNJUNGAN());
        viewHolder.tglkunjungan.setText(kunjungan.get(position).getCreatedAt());
        viewHolder.nama.setText(kunjungan.get(position).getBadanUsaha().getName());
        viewHolder.phone.setText(kunjungan.get(position).getBadanUsaha().getPhone());
        viewHolder.badanusaha.setText(kunjungan.get(position).getBadanUsaha().getBidangUsaha());
        viewHolder.alamat.setText(kunjungan.get(position).getBadanUsaha().getAddress());
        viewHolder.email.setText(kunjungan.get(position).getBadanUsaha().getEmail());

//        if (item.getF_SAVE_DRAFT().equals("1")) {
//            viewHolder.draft.setVisibility(View.VISIBLE);
//        }

        viewHolder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mActivity, InsertDataBPJS.class);
                i.putExtra("home", "0");
                i.putExtra("view", "2");
                i.putExtra("edit", "0");
                i.putExtra("data", kunjungan.get(position));
//                i.putExtra("kodeForm", item.getF_KODE());
                mActivity.startActivity(i);

            }
        });
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setMessage("Do you want to edit this data?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(mContext, InsertDataBPJS.class);
                                i.putExtra("home", "200");
                                i.putExtra("view", "0");
                                i.putExtra("edit", "2");
                                i.putExtra("data", kunjungan.get(position));
                                i.putExtra("id", kunjungan.get(position).getId());
                                mContext.startActivity(i);
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(mContext)
                        .setMessage("Are you sure you want to delete this data?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                sharedPrefManager = new SharedPrefManager(v.getContext());
                                String token = sharedPrefManager.getSpToken();
                                Service service = Client.getClient().create(Service.class);
                                Call<KunjunganDeleteResponse> delete = service.deleteKunjungan("Bearer " + token, String.valueOf(kunjungan.get(position).getId()));
                                delete.enqueue(new Callback<KunjunganDeleteResponse>() {
                                    @Override
                                    public void onResponse(Call<KunjunganDeleteResponse> call, Response<KunjunganDeleteResponse> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(mActivity.getApplicationContext(), v.getContext().getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                                            kunjungan.remove(kunjungan.get(position));
                                            notifyDataSetChanged();

                                        }else {
                                            Toast.makeText(mActivity.getApplicationContext(), v.getContext().getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<KunjunganDeleteResponse> call, Throwable t) {

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

        viewHolder.panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mActivity, item.getF_KODE() ,Toast.LENGTH_LONG).show();
            }
        });

    }

    // Return the size arraylist
    @Override
    public int getItemCount() {
        return kunjungan.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nama, phone, badanusaha, alamat, email, tglkunjungan, draft;
        public RelativeLayout panel;
        ImageView show, edit, delete;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            panel = itemLayoutView.findViewById(R.id.panel);
            nama = itemLayoutView.findViewById(R.id.edt_namakontak);
            phone = itemLayoutView.findViewById(R.id.edt_phonekontak);
            badanusaha = itemLayoutView.findViewById(R.id.edt_badanusaha);
            alamat = itemLayoutView.findViewById(R.id.edt_alamat);
            email = itemLayoutView.findViewById(R.id.edt_email);
            tglkunjungan = itemLayoutView.findViewById(R.id.edt_tgl_kunjungan);
            show = itemLayoutView.findViewById(R.id.show_forms);
            edit = itemLayoutView.findViewById(R.id.edit_forms);
            delete = itemLayoutView.findViewById(R.id.delete_forms);
            draft = itemLayoutView.findViewById(R.id.txt_draft);

        }
    }


}
