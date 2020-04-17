package com.saami.app.projects.form;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.saami.app.projects.form.model.badanusaha.DataItem;
import com.saami.app.projects.form.sqlite.DBDataSource;
import com.saami.app.projects.form.sqlite.FormData;

import net.ozaydin.serkan.easy_csv.EasyCsv;
import net.ozaydin.serkan.easy_csv.FileCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListView_BPJS_Draft extends AppCompatActivity
{

    ProviderFormList provform;
    ImageView addBpjs,download;
    DBDataSource dataSource;
    private adapterFormList adapter;
    private List<ProviderFormList> arraylistform = new ArrayList<>();
    private EasyCsv easyCsv;
    List<String> headerList,dataList;
    private TextInputEditText edtCarinama;
    private ImageView cari,filter;
    private ImageView refresh;
    final Calendar myCalendar = Calendar.getInstance();
    private List<DataItem> getBU = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_bpjs_draft);
        dataSource = new DBDataSource(this);
        easyCsv = new EasyCsv(ListView_BPJS_Draft.this);

        view();
        getformData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                String result = data.getStringExtra("refresh");
                if (result.equals("200")) {
                    getformData();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED)
            {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    private void view()
    {
        edtCarinama = findViewById(R.id.edt_cari_form);

        cari = findViewById(R.id.icocari);
        cari.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getformDataByNama();
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }

        };

        filter = findViewById(R.id.icofilter);
        filter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(ListView_BPJS_Draft.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getformData();
            }
        });

        download = findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new AlertDialog.Builder(ListView_BPJS_Draft.this)
                        .setMessage("Membuat CSV File ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                downloadCSV();
                                dialog.dismiss();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        addBpjs = findViewById(R.id.add_bpjs);
        addBpjs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(ListView_BPJS_Draft.this, InsertDataBPJS.class);
                startActivityForResult(i, 1);
            }
        });

        RecyclerView listParkir = findViewById(R.id.list_form);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ListView_BPJS_Draft.this.getApplicationContext());
        listParkir.setLayoutManager(mLayoutManager);
        listParkir.addItemDecoration(new DividerItemDecoration(this, 0));
        listParkir.setItemAnimator(new DefaultItemAnimator());
        adapter = new adapterFormList(arraylistform, ListView_BPJS_Draft.this, ListView_BPJS_Draft.this, getBU);
        listParkir.setAdapter(adapter);
    }

    void downloadCSV()
    {
        try
        {
            headerList = new ArrayList<>();
            headerList.add("PLAT NOMOR.JAM MASUK.JAM KELUAR.DURASI.HARGA.PERJAM.TOTAL BIAYA-");
            long total = 0;
            dataList = new ArrayList<>();
//                    final ArrayList<DataParkir> data = datasource.getAllDataParkirBySpecificDate(dates);
            if (arraylistform.size() > 0)
            {

                for (int im = 0; im < arraylistform.toArray().length; im++)
                {
                    final ProviderFormList cv = arraylistform.get(im);
//                    dataList.add(cv.getPlatno()+"."+cv.getJammasuk()+"."+cv.getJamkeluar()+"."+cv.getJamselisih().replaceAll(", ","/")+"."+cv.getHarga()+"."+perjams+"."+cv.getBiaya()+"-");
//                    total = total + Long.valueOf(cv.getBiaya());
                }
                dataList.add("......-");
//                dataList.add(".....Pendapatan  ."+total);
                createCSV();

            }
            else
            {
                Toast.makeText(ListView_BPJS_Draft.this, "Tidak ada data yang ditampilkan", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    void createCSV()
    {
        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "Canfaro");
        boolean success = true;
        if (!folder.exists())
        {
            success = folder.mkdirs();
        }
        if (success)
        {
            final String datestimes = new SimpleDateFormat("yyyyMMdd hhmmss").format(Calendar.getInstance().getTime());
            easyCsv.setSeparatorColumn(".");
            easyCsv.setSeperatorLine("-");
            easyCsv.createCsvFile("Canfaro/Laporan Data BPJS " + datestimes, headerList, dataList, 1, new FileCallback()
            {
                @Override
                public void onSuccess(File file)
                {
                    ToatsMessage();
                }
                @Override
                public void onFail(String err)
                {

                }
            });
        }
        else
        {
            // Do something else on failure
        }
    }

    private void ToatsMessage()
    {
        View parentLayout = this.findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Create CSV Laporan Data BPJS Berhasil, Silahkan Periksa Folder Canfaro di Directory anda", Snackbar.LENGTH_INDEFINITE)
                .show();
    }

    void getformData(){
        arraylistform.clear();
        ArrayList<FormData> forms = dataSource.getAllformdraft();
        if (forms.size() > 0)
        {
            for (int i = 0; i < forms.toArray().length; i++)
            {
                final FormData cv = forms.get(i);
                System.out.println("kode :"+cv.getF_KODE());
                provform = new ProviderFormList(cv.getF_KODE(),cv.getF_TGL_KUNJUNGAN(),cv.getF_TGL_PENYERAHAN_DATA(),cv.getF_TGL_PERINGATAN_PENDAFTARAN(),cv.getF_TGL_PENDAFTARAN_BU(),cv.getF_TGL_PENYERAHAN_DATA(),cv.getF_BDN_USH(),cv.getF_ALAMAT(),cv.getF_PHONE(),cv.getF_EMAIL(),cv.getF_BIDANG_USH(),cv.getF_JUMLAHKAR(),cv.getF_JUMLAHKEL(), cv.getF_MENGIKUTI_SOSIALISASI_BPJS_KES(),cv.getF_JKN_KIS(),cv.getF_JUMLAHTERDAFTARKAR(),cv.getF_JUMLAHTERDAFTARKEL(),cv.getF_ASURANSIKES(),cv.getF_TAMBAHAN(),cv.getF_KP_NAMA(),cv.getF_KP_JABATAN(),cv.getF_KP_UNIT_KERJA(),cv.getF_KP_PHONE(),cv.getF_HC_BERSEDIA_MENDAFTAR(),cv.getF_HC_ALASAN(),cv.getF_HC_TINDAK_LANJUT(),cv.getF_HC_KENDALA(),cv.getF_SAVE_DRAFT());
                arraylistform.add(provform);
            }
            adapter.notifyDataSetChanged();

        }
        else
        {
            Toast.makeText(ListView_BPJS_Draft.this, "Tidak ada data" ,Toast.LENGTH_LONG).show();
            arraylistform.clear();
            adapter.notifyDataSetChanged();
        }
    }
    void getformDataByNama(){
        arraylistform.clear();
        ArrayList<FormData> forms = dataSource.getAllformdraftbynama(edtCarinama.getText().toString());
        if (forms.size() > 0)
        {
            for (int i = 0; i < forms.toArray().length; i++)
            {
                final FormData cv = forms.get(i);
                System.out.println("kode :"+cv.getF_KODE());
                provform = new ProviderFormList(cv.getF_KODE(),cv.getF_TGL_KUNJUNGAN(),cv.getF_TGL_PENYERAHAN_DATA(),cv.getF_TGL_PERINGATAN_PENDAFTARAN(),cv.getF_TGL_PENDAFTARAN_BU(),cv.getF_TGL_PENYERAHAN_DATA(),cv.getF_BDN_USH(),cv.getF_ALAMAT(),cv.getF_PHONE(),cv.getF_EMAIL(),cv.getF_BIDANG_USH(),cv.getF_JUMLAHKAR(),cv.getF_JUMLAHKEL(), cv.getF_MENGIKUTI_SOSIALISASI_BPJS_KES(),cv.getF_JKN_KIS(),cv.getF_JUMLAHTERDAFTARKAR(),cv.getF_JUMLAHTERDAFTARKEL(),cv.getF_ASURANSIKES(),cv.getF_TAMBAHAN(),cv.getF_KP_NAMA(),cv.getF_KP_JABATAN(),cv.getF_KP_UNIT_KERJA(),cv.getF_KP_PHONE(),cv.getF_HC_BERSEDIA_MENDAFTAR(),cv.getF_HC_ALASAN(),cv.getF_HC_TINDAK_LANJUT(),cv.getF_HC_KENDALA(),cv.getF_SAVE_DRAFT());
                arraylistform.add(provform);
            }
            adapter.notifyDataSetChanged();

        }
        else
        {
            Toast.makeText(ListView_BPJS_Draft.this, edtCarinama.getText().toString()+" Tidak ditemukan" ,Toast.LENGTH_LONG).show();
            arraylistform.clear();
            adapter.notifyDataSetChanged();
        }
    }

}
