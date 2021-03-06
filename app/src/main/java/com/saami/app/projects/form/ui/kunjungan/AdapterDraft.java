package com.saami.app.projects.form.ui.kunjungan;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.saami.app.projects.form.R;
import com.saami.app.projects.form.sqlite.DBDataSource;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterDraft extends RecyclerView.Adapter<AdapterDraft.ViewHolder> {
    private List<ProviderFormList> assList;
    Context mContext;
    Activity mActivity;
    LayoutInflater mInflater;
    private DecimalFormat kursIndonesia;
    private DBDataSource datasource;
    private String header, harga, check;
    public AdapterDraft(List<ProviderFormList> incom, Context ctx, Activity act) {
        this.assList = incom;
        this.mContext = ctx;
        this.mActivity = act;
        mInflater = LayoutInflater.from(mContext);
    }
    @NonNull
    @Override
    public AdapterDraft.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_listview_formdata, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        datasource = new DBDataSource(mActivity);

        final ProviderFormList item = assList.get(position);
        viewHolder.nama.setText(item.getF_KP_NAMA());
        viewHolder.phone.setText("Phone Number : " + item.getF_KP_PHONE());
        viewHolder.badanusaha.setText(item.getF_BDN_USH());
        viewHolder.alamat.setText(item.getF_ALAMAT());
        viewHolder.email.setText(item.getF_EMAIL());
        viewHolder.tglkunjungan.setText(item.getF_TGL_KUNJUNGAN());

        if (item.getF_SAVE_DRAFT().equals("1")) {
            viewHolder.draft.setVisibility(View.VISIBLE);
        }

        viewHolder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mActivity, InsertDataBPJS.class);
                i.putExtra("home", "0");
                i.putExtra("view", "1");
                i.putExtra("edit", "0");
                i.putExtra("kodeForm", item.getF_KODE());
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
                                i.putExtra("edit", "1");
                                i.putExtra("kodeForm", item.getF_KODE());
                                mContext.startActivity(i);
                                if (item.getF_SAVE_DRAFT().equals("0")) {
                                    ((ListView_BPJS) mContext).finish();
                                } else {
                                    ((ListView_BPJS_Draft) mContext).finish();
                                }

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
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setMessage("Are you sure you want to delete this data?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                // Continue with delete operation

                                long a = datasource.deleteformwherekode(item.getF_KODE());
                                if (a > 0) {

                                    dialog.dismiss();
                                    if (item.getF_SAVE_DRAFT().equals("0")) {
                                        ((ListView_BPJS_Draft) mContext).getformData();
                                    } else {
                                        ((ListView_BPJS_Draft) mContext).getformData();
                                    }
                                } else {
                                    Toast.makeText(mContext, "Gagal hapus data, silahkan coba lagi", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
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


    @Override
    public int getItemCount() {
        return assList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
