package com.saami.app.projects.form.ui.kunjungan;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.saami.app.projects.form.HomeActivity;
import com.saami.app.projects.form.R;
import com.saami.app.projects.form.model.kunjungan.DataItem;
import com.saami.app.projects.form.model.kunjungan.KunjunganGetResponse;
import com.saami.app.projects.form.sqlite.DBDataSource;
import com.saami.app.projects.form.ui.FixViewModel;

import net.ozaydin.serkan.easy_csv.EasyCsv;
import net.ozaydin.serkan.easy_csv.FileCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListView_BPJS extends AppCompatActivity {

    ProviderFormList provform;
    ImageView addBpjs, download;
    DBDataSource dataSource;
    private adapterFormList adapter;
    private List<ProviderFormList> arraylistform = new ArrayList<>();
    private EasyCsv easyCsv;
    List<String> headerList, dataList;
    private TextInputEditText edtCarinama;
    private ImageView cari, filter;
    private ImageView refresh;
    final Calendar myCalendar = Calendar.getInstance();
    private List<com.saami.app.projects.form.model.kunjungan.DataItem> getKunjungan = new ArrayList<>();
    private FixViewModel fixViewModel;
    RecyclerView listParkir;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_bpjs);
        dataSource = new DBDataSource(this);
        easyCsv = new EasyCsv(ListView_BPJS.this);
        easyCsv.setSeparatorColumn("|");
        easyCsv.setSeperatorLine("`");
        fixViewModel = ViewModelProviders.of(this).get(FixViewModel.class);
        pb = findViewById(R.id.pb);
        view();
        getData();

        pb.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("refresh");
                if (result.equals("200")) {
                    getData();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    private void view() {
        edtCarinama = findViewById(R.id.edt_cari_form);


        cari = findViewById(R.id.icocari);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListView_BPJS.this, KunjunganSearch.class);
                intent.putExtra("query", edtCarinama.getText().toString());
                startActivity(intent);
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

        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pb.setVisibility(View.VISIBLE);
//                getData();
                Intent intent = new Intent(ListView_BPJS.this, HomeActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("state", "6");
                startActivity(intent);
            }
        });

        download = findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ListView_BPJS.this)
                        .setMessage("Membuat CSV File ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                downloadCSV();
                                dialog.dismiss();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        addBpjs = findViewById(R.id.add_bpjs);
        addBpjs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListView_BPJS.this, InsertDataBPJS.class);
                startActivityForResult(i, 1);
            }
        });

        listParkir = findViewById(R.id.list_form);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ListView_BPJS.this.getApplicationContext());
        listParkir.setLayoutManager(mLayoutManager);
        listParkir.addItemDecoration(new DividerItemDecoration(this, 0));
        listParkir.setItemAnimator(new DefaultItemAnimator());
        adapter = new adapterFormList(ListView_BPJS.this, ListView_BPJS.this, getKunjungan);
        listParkir.setAdapter(adapter);
    }

    void downloadCSV() {
        try {
            headerList = new ArrayList<>();
            headerList.add("WAKTU KUNJUNGAN|TANGGAL PENYERAHAN SURAT KESEDIAAN PENDAFTARAN|TANGGAL PERINGATAN PENDAFTARAN|TANGGAL MAX PENDAFTARAN BU|TANGGAL PENYERAHAN DATA|NAMA BADAN USAHA|ALAMAT|TELP|EMAIL|BIDANG USAHA|JUMLAH KARYAWAN|JUMLAH KELUARGA|MENGIKUTI SOSIALISASI BPJS KESEHATAN|KEPESERTAAN JKS KIS|JUMLAH KARYAWAN TERDAFTAR|JUMLAH KELUARGA TERDAFTAR|ASURANSI KESEHATAN|TAMBAHAN/LAINLAIN|NAMA|JABATAN|UNIT KERJA|TELP|BERSEDIA MENDAFTAR|ALASAN|TINDAK LANJUT|KENDALA`");
            long total = 0;
            dataList = new ArrayList<>();
//                    final ArrayList<DataParkir> data = datasource.getAllDataParkirBySpecificDate(dates);
            if (getKunjungan.size() > 0) {

                for (int im = 0; im < getKunjungan.toArray().length; im++) {
                    final DataItem items = getKunjungan.get(im);
                    String ikutsosialisasi, jknkis, askes, sediadaftar;
                    if (items.getBadanUsaha().isSosialisasiBPJS()) {
                        ikutsosialisasi = "Sudah";
                    } else {
                        ikutsosialisasi = "Belum";
                    }
                    if (items.getBadanUsaha().isPesertaJKNOrKIS()) {
                        jknkis = "Sudah";
                    } else {
                        jknkis = "Belum";
                    }
                    if (items.getBadanUsaha().isAsuransiKesehatan()) {
                        askes = "Sudah";
                    } else {
                        askes = "Belum";
                    }
                    if (items.isStatus()) {
                        sediadaftar = "Ya";
                    } else {
                        sediadaftar = "Tidak";
                    }
                    dataList.add(items.getCreatedAt() + "|" + items.getTPSKP() + "|" + items.getTPP() + "|" + items.getTMPBU() + "|" + items.getTPD() + "|" + items.getBadanUsaha().getName() + "|" + items.getBadanUsaha().getAddress() + "|" + items.getBadanUsaha().getPhone() + "|" + items.getBadanUsaha().getEmail() + "|" + items.getBadanUsaha().getBidangUsaha() + "|" + items.getBadanUsaha().getJumlahKaryawan() + "|" + items.getBadanUsaha().getJumlahKeluarga() + "|" + ikutsosialisasi + "|" + jknkis + "|" + items.getBadanUsaha().getJumlahKaryawanTerdaftar() + "|" + items.getBadanUsaha().getJumlahKeluargaTerdaftar() + "|" + askes + "|" + items.getBadanUsaha().getKeterangan() + "|" + items.getContactBadanUsaha().getName() + "|" + items.getContactBadanUsaha().getJabatan() + "|" + items.getContactBadanUsaha().getUnitKerja() + "|" + items.getContactBadanUsaha().getPhone() + "|" + sediadaftar + "|" + items.getAlasan() + "|" + items.getTindakLanjut() + "|" + items.getKendala() + "`");
                }
                createCSV();

            } else {
                Toast.makeText(ListView_BPJS.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    void createCSV() {
        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "Canfaro");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            final String datestimes = new SimpleDateFormat("yyyyMMdd hhmmss").format(Calendar.getInstance().getTime());
            easyCsv.createCsvFile("Canfaro/Laporan Data BPJS " + datestimes, headerList, dataList, 1, new FileCallback() {
                @Override
                public void onSuccess(File file) {
                    ToatsMessage();
                }

                @Override
                public void onFail(String err) {

                }
            });
        } else {
            // Do something else on failure
        }
    }

    private void ToatsMessage() {
        View parentLayout = this.findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Create CSV Laporan Data BPJS Berhasil, Silahkan Periksa Folder Canfaro di Directory anda", Snackbar.LENGTH_INDEFINITE)
                .setAction("Tutup", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }


    public void getData() {


        fixViewModel.liveGet().observe(this, new Observer<KunjunganGetResponse>() {
            @Override
            public void onChanged(KunjunganGetResponse kunjunganGetResponse) {

                getKunjungan.clear();
                getKunjungan.addAll(kunjunganGetResponse.getData());
                listParkir.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                pb.setVisibility(View.GONE);


            }
        });
    }



}
