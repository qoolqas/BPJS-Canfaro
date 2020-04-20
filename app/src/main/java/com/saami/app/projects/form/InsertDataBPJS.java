package com.saami.app.projects.form;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.mindorks.paracamera.Camera;
import com.saami.app.projects.form.connection.Client;
import com.saami.app.projects.form.connection.Service;
import com.saami.app.projects.form.model.image.ImageResponse;
import com.saami.app.projects.form.model.kunjungan.DataItem;
import com.saami.app.projects.form.model.post.BadanUsaha;
import com.saami.app.projects.form.model.post.ContactBadanUsaha;
import com.saami.app.projects.form.model.post.Data;
import com.saami.app.projects.form.model.post.Kunjungan;
import com.saami.app.projects.form.model.post.PostResponse;
import com.saami.app.projects.form.sqlite.DBDataSource;
import com.saami.app.projects.form.sqlite.FormData;
import com.saami.app.projects.form.sqlite.FormFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertDataBPJS extends AppCompatActivity {
    DBDataSource dataSource;
    ImageView photo;
    Button btnCamera, btnGallery, tgl_wkt_knj, tgl_pnd, tgl_peringatan_daftar, tgl_max_bu, tgl_serah_data, save_form, draft_form;
    TextInputEditText edtNamaBadanUsaha, edtAlamat, edtTelp, edtEmail, edtBidangUsaha, edtJumlahKaryawan, edtJumlahKeluarga, edtJumlahKaryawanTerdaftar, edtJumlahKeluargaTerdaftar,
            edtTambahan, edtPsNama, edtPsJabatan, edtPsUnitKerja, edtPsPhone, edtalasan, edttindaklanjut, edtkendala, edtNotes,
            edtJumlahRekrutmen;
    RadioGroup rGroupSosialisasiBpjs, rGroupJknKis, rGroupAskes, rGroupBersediaMendaftar, rGroupNotifikasi;
    LinearLayout linearRekrutmen;

    final Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;
    int tgl;

    String home = "0";
    String edit = "0";
    String view = "0";
    String dataPhoto = "";
    String kodeForm = "";
    String idKunjungan = "";
    private Camera camera;
    private Bitmap bitmapPhoto;
    private SignaturePad mSignaturePad, mSignaturePad2;
    private Button clearSignature, clearsignature2;
    String savedraft = "0";
    private Button save_media;
    SharedPrefManager sharedPrefManager;
    DataItem dataItem;
    Uri fileUri;
    String filePath = "";


//    BitMapToString(bitmapImage1)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataItem = getIntent().getParcelableExtra("data");
        Log.d("data", String.valueOf(dataItem));
        dataSource = new DBDataSource(this);
        sharedPrefManager = new SharedPrefManager(this);
        try {
            home = getIntent().getExtras().getString("home");
        } catch (Exception e) {
            home = "0";
        }

        try {
            view = getIntent().getExtras().getString("view");
            kodeForm = getIntent().getExtras().getString("kodeForm");
        } catch (Exception e) {
            view = "0";
        }
        try {
            view = getIntent().getExtras().getString("view");
        } catch (Exception e) {
            view = "0";
        }
        try {
            edit = getIntent().getExtras().getString("edit");
            idKunjungan = getIntent().getExtras().getString("id");
        } catch (Exception e) {
            edit = "0";
        }

        try {
            edit = getIntent().getExtras().getString("edit");
            kodeForm = getIntent().getExtras().getString("kodeForm");
        } catch (Exception e) {
            edit = "0";
        }

        view();
    }

    private void view() {
        photo = findViewById(R.id.iv_Photo);

        btnCamera = findViewById(R.id.bt_camera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera = new Camera.Builder()
                        .resetToCorrectOrientation(true)// it will rotate the camera bitmap to the correct orientation from meta data
                        .setTakePhotoRequestCode(1)
                        .setDirectory("pics")
                        .setName("canfaro_" + System.currentTimeMillis())
                        .setImageFormat(Camera.IMAGE_JPEG)
                        .setCompression(50)// it will try to achieve this height as close as possible maintaining the aspect ratio;
                        .build(InsertDataBPJS.this);

                try {
                    camera.takePicture();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnGallery = findViewById(R.id.bt_gallery);
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 2);//one can be replaced with any action code
            }
        });

        tgl_wkt_knj = findViewById(R.id.bt_wkt_knj);
        String myFormats = "dd/MM/yyyy hh:mm:ss"; //In which you need put here
        SimpleDateFormat sdfc = new SimpleDateFormat(myFormats, Locale.US);
        tgl_wkt_knj.setText(sdfc.format(myCalendar.getTime()));
        tgl_wkt_knj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tgl = 1;
                datePicker();
            }
        });
        tgl_pnd = findViewById(R.id.bt_tgl_pnd);
        tgl_pnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tgl = 2;
                datePicker();
            }
        });
        tgl_peringatan_daftar = findViewById(R.id.bt_tgl_peringatan_daftar);
        tgl_peringatan_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tgl = 3;
                datePicker();
            }
        });
        tgl_max_bu = findViewById(R.id.bt_tgl_max_bu);
        tgl_max_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tgl = 4;
                datePicker();
            }
        });
        tgl_serah_data = findViewById(R.id.bt_tgl_serah_data);
        tgl_serah_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tgl = 5;
                datePicker();
            }
        });

        edtNamaBadanUsaha = findViewById(R.id.edt_nama_badan_usaha);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtTelp = findViewById(R.id.edt_telp);
        edtEmail = findViewById(R.id.edt_email);
        edtBidangUsaha = findViewById(R.id.edt_bid_usaha);
        edtJumlahKaryawan = findViewById(R.id.edt_jumlah_karyawan);
        edtJumlahKeluarga = findViewById(R.id.edt_jumlah_keluarga);
        rGroupSosialisasiBpjs = findViewById(R.id.rg_sosialisasibpjs);
        rGroupJknKis = findViewById(R.id.rg_kepersertaan_jkn);
        edtJumlahKaryawanTerdaftar = findViewById(R.id.edt_jumlah_karyawan_terdaftar);
        edtJumlahKeluargaTerdaftar = findViewById(R.id.edt_jumlah_keluarga_terdaftar);
        rGroupAskes = findViewById(R.id.rg_asuransikes);
        edtTambahan = findViewById(R.id.edt_tambahan);
        edtPsNama = findViewById(R.id.edt_ps_nama);
        edtPsJabatan = findViewById(R.id.edt_ps_jabatan);
        edtPsUnitKerja = findViewById(R.id.edt_ps_unitkerja);
        edtPsPhone = findViewById(R.id.edt_ps_nophone);
        rGroupBersediaMendaftar = findViewById(R.id.rg_bersediamendaftar);
        edtalasan = findViewById(R.id.edt_alasan);
        edttindaklanjut = findViewById(R.id.edt_tindak_lanjut);
        edtkendala = findViewById(R.id.edt_kendala);
        edtNotes = findViewById(R.id.edt_notes);
        edtJumlahRekrutmen = findViewById(R.id.edt_jumlah_rekrutmen);
        linearRekrutmen = findViewById(R.id.linearRekrutmen);
        rGroupNotifikasi = findViewById(R.id.rg_notifikasi);

        rGroupBersediaMendaftar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                RadioButton rButtonBersediaMendaftar;
                int selectedIdBersedia = rGroupBersediaMendaftar.getCheckedRadioButtonId();
                rButtonBersediaMendaftar = findViewById(selectedIdBersedia);
//                badanUsaha.setPesertaJKNOrKIS(rButtonBersediaMendaftar.getText().toString());
                Log.d("bersedia", rButtonBersediaMendaftar.getText().toString());
                if (rButtonBersediaMendaftar.getText().toString().equals("Ya")) {
                    linearRekrutmen.setVisibility(View.VISIBLE);
                } else {
                    linearRekrutmen.setVisibility(View.GONE);
                    edtJumlahRekrutmen.setText("");
                }
            }
        });

        mSignaturePad = findViewById(R.id.signature_pad);
        clearSignature = findViewById(R.id.bt_signature_clear);
        clearSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.clear();
            }
        });
        mSignaturePad2 = findViewById(R.id.signature_pad2);
        clearsignature2 = findViewById(R.id.bt_signature_clear2);
        clearsignature2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad2.clear();
            }
        });


        save_form = findViewById(R.id.bt_save_form);
        draft_form = findViewById(R.id.bt_draft_form);

        if (edit.equals("1")) {
            save_form.setText("Simpan Pembaruan");
            setEditData();
        }
        if (edit.equals("2")) {
            save_form.setText("Simpan Pembaruan");
            setEditApi();
        }
        save_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedraft = "0";
                if (mSignaturePad.isEmpty() | mSignaturePad2.isEmpty()) {
                    Toast.makeText(InsertDataBPJS.this, "Silahkan Tanda Tangan Terlebih Dahulu", Toast.LENGTH_LONG).show();
                    uploadPhoto();
                    Log.d("dataPhoto", dataPhoto);
                } else {
                    if (edit.equals("1")) {
                        new AlertDialog.Builder(InsertDataBPJS.this)
                                .setMessage("Update Data, Apakah data sudah benar ?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int arg1) {
                                        dialog.dismiss();
                                        editForm();
                                        editFile();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })

                                .create()
                                .show();

                    } else if (edit.equals("2")) {

                    } else {
                        new AlertDialog.Builder(InsertDataBPJS.this)
                                .setMessage("Simpan Data, Apakah data sudah benar ?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int arg1) {
                                        dialog.dismiss();
                                        savePost(constructData());
//                                        saveNewData();
//                                        saveFile();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })

                                .create()
                                .show();

                    }
                }
            }
        });
        draft_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedraft = "1";
                if (edit.equals("1")) {
                    new AlertDialog.Builder(InsertDataBPJS.this)
                            .setMessage("Update Draft ? Kamu bisa lengkapi kembali data ini nanti")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int arg1) {
                                    dialog.dismiss();
                                    editForm();
                                    editFile();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })

                            .create()
                            .show();

                } else {
                    new AlertDialog.Builder(InsertDataBPJS.this)
                            .setMessage("Simpan Draft ? Kamu bisa lengkapi kembali data ini nanti")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int arg1) {
                                    dialog.dismiss();
                                    saveNewData();
                                    saveFile();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })

                            .create()
                            .show();
                }
            }
        });

        save_media = findViewById(R.id.bt_save_media);
        save_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo.setDrawingCacheEnabled(true);
                Bitmap bitmapPhotos = photo.getDrawingCache();
                saveMediaPhotoTtd(bitmapPhotos, mSignaturePad.getSignatureBitmap(), mSignaturePad2.getSignatureBitmap());
            }
        });


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                if (tgl == 1) {
                    String myFormats = "dd/MM/yyyy hh:mm:ss"; //In which you need put here
                    SimpleDateFormat sdfc = new SimpleDateFormat(myFormats, Locale.US);
                    tgl_wkt_knj.setText(sdfc.format(myCalendar.getTime()));
                } else if (tgl == 2) {
                    tgl_pnd.setText(sdf.format(myCalendar.getTime()));
                } else if (tgl == 3) {
                    tgl_peringatan_daftar.setText(sdf.format(myCalendar.getTime()));
                } else if (tgl == 4) {
                    tgl_max_bu.setText(sdf.format(myCalendar.getTime()));
                } else if (tgl == 5) {
                    tgl_serah_data.setText(sdf.format(myCalendar.getTime()));
                }

            }

        };

        if (view.equals("1")) {
            setviewOnly();
        }
        if (view.equals("2")) {
            setViewApi();
        }

    }
    private static File createTemporaryFile(Context context) {
        try {
            File folder = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "prof_pic");
            if (!folder.exists()) {
                folder.mkdirs();
                Log.d("anji", String.valueOf(folder.mkdirs()));
            }
            return File.createTempFile("" + System.currentTimeMillis(), ".jpg", folder);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        RequestOptions myOptions = new RequestOptions()
                .centerCrop()
                .override(300, 300);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = camera.getCameraBitmap();
                    if (bitmap != null) {
                        Glide.with(InsertDataBPJS.this)
                                .asBitmap()
                                .apply(myOptions)
                                .load(bitmap)
                                .into(photo);
                    } else {
                        Toast.makeText(this.getApplicationContext(), "Picture not taken!", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = imageReturnedIntent.getData();

                    Glide.with(InsertDataBPJS.this)
                            .asBitmap()
                            .apply(myOptions)
                            .load(selectedImage)
                            .into(photo);
                    String[] filePathColumn = {android.provider.MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String mediaPath = cursor.getString(columnIndex);
                    // Set the Image in ImageView for Previewing the Media
                    cursor.close();
                    fileUri = Uri.parse(mediaPath);

                }
                break;
        }
    }


    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.NO_WRAP);
        return temp;
    }

    void setEditData() {
        ArrayList<FormData> forms = dataSource.getAllformbykode(kodeForm);
        if (forms.size() == 1) {
            final FormData data = forms.get(0);
//                Toast.makeText(InsertDataBPJS.this, data.getF_ASURANSIKES()+","+data.getF_HC_BERSEDIA_MENDAFTAR() ,Toast.LENGTH_LONG).show();
            tgl_wkt_knj.setText(data.getF_TGL_KUNJUNGAN());
            tgl_pnd.setText(data.getF_TGL_KESEDIAAN_PENDAFTARAN());
            tgl_peringatan_daftar.setText(data.getF_TGL_PERINGATAN_PENDAFTARAN());
            tgl_max_bu.setText(data.getF_TGL_PENDAFTARAN_BU());
            tgl_serah_data.setText(data.getF_TGL_PENDAFTARAN_BU());
            edtNamaBadanUsaha.setText(data.getF_BDN_USH());
            edtAlamat.setText(data.getF_ALAMAT());
            edtTelp.setText(data.getF_PHONE());
            edtEmail.setText(data.getF_EMAIL());
            edtBidangUsaha.setText(data.getF_BIDANG_USH());
            edtJumlahKaryawan.setText(data.getF_JUMLAHKAR());
            edtJumlahKeluarga.setText(data.getF_JUMLAHKEL());
            if (data.getF_MENGIKUTI_SOSIALISASI_BPJS_KES().toLowerCase().equals("sudah")) {
                rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_sudah);
            } else {
                rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_belum);
            }
            if (data.getF_JKN_KIS().toLowerCase().equals("sudah")) {
                rGroupJknKis.check(R.id.rd_jknkis_sudah);
            } else {
                rGroupJknKis.check(R.id.rd_jknkis_belum);
            }
            edtJumlahKaryawanTerdaftar.setText(data.getF_JUMLAHTERDAFTARKAR());
            edtJumlahKeluargaTerdaftar.setText(data.getF_JUMLAHTERDAFTARKEL());

            if (data.getF_ASURANSIKES().toLowerCase().equals("sudah")) {
                rGroupAskes.check(R.id.rd_asurankes_sudah);
            } else {
                rGroupAskes.check(R.id.rd_asurankes_belum);
            }
            edtTambahan.setText(data.getF_TAMBAHAN());
            edtPsNama.setText(data.getF_KP_NAMA());
            edtPsJabatan.setText(data.getF_KP_JABATAN());
            edtPsUnitKerja.setText(data.getF_KP_UNIT_KERJA());
            edtPsPhone.setText(data.getF_KP_PHONE());
            if (data.getF_HC_BERSEDIA_MENDAFTAR().toLowerCase().equals("ya")) {
                rGroupBersediaMendaftar.check(R.id.rd_bersediadaftar_ya);
            } else {
                rGroupBersediaMendaftar.check(R.id.rd_bersediadaftar_tidak);
            }
            edtalasan.setText(data.getF_HC_ALASAN());
            edttindaklanjut.setText(data.getF_HC_TINDAK_LANJUT());
            edtkendala.setText(data.getF_HC_KENDALA());
            if (data.getF_SAVE_DRAFT().equals("1")) {
                savedraft = "1";
            } else {
                savedraft = "0";
            }

            ArrayList<FormFile> file = dataSource.getAllfilebykode(kodeForm);
            if (file.size() == 1) {
                final FormFile files = file.get(0);
                Log.d("imgs", files.getFile_image());
                photo.setImageBitmap(StringToBitMap(files.getFile_image()));
                mSignaturePad.setSignatureBitmap(StringToBitMap(files.getFile_ttd()));
            }

        } else {
            Toast.makeText(InsertDataBPJS.this, "Data tidak ditemukan", Toast.LENGTH_LONG).show();
        }

    }

    void setEditApi() {
        draft_form.setVisibility(View.GONE);

        tgl_wkt_knj.setText(dataItem.getCreatedAt());
        tgl_pnd.setText(dataItem.getTPSKP());
        tgl_peringatan_daftar.setText(dataItem.getTPP());
        tgl_max_bu.setText(dataItem.getTMPBU());
        tgl_serah_data.setText(dataItem.getTPD());
//            mSignaturePad2.setSignatureBitmap(StringToBitMap(dataItem.getTtdImage().getUrl()));
        edtNotes.setText(dataItem.getNote());
        if (dataItem.isReminder()) {
            rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_sudah);
        } else {
            rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_belum);
        }
        edtalasan.setText(dataItem.getAlasan());
        edttindaklanjut.setText(dataItem.getTindakLanjut());
        edtkendala.setText(dataItem.getKendala());
        edtJumlahRekrutmen.setText(String.valueOf(dataItem.getTotalRecruitment()));

        if (dataItem.isStatus()) {
            rGroupBersediaMendaftar.check(R.id.rd_bersediadaftar_ya);
        } else {
            rGroupBersediaMendaftar.check(R.id.rd_bersediadaftar_tidak);
        }

        Log.d("namab", dataItem.getBadanUsaha().getName());
        Log.d("namabd", dataItem.getBadanUsaha().getName());
        edtNamaBadanUsaha.setText(dataItem.getBadanUsaha().getName());
        edtAlamat.setText(dataItem.getBadanUsaha().getAddress());
        edtTelp.setText(dataItem.getBadanUsaha().getPhone());
        edtEmail.setText(dataItem.getBadanUsaha().getEmail());
        edtBidangUsaha.setText(dataItem.getBadanUsaha().getBidangUsaha());
        edtJumlahKaryawan.setText(String.valueOf(dataItem.getBadanUsaha().getJumlahKaryawan()));
        edtJumlahKeluarga.setText(String.valueOf(dataItem.getBadanUsaha().getJumlahKeluarga()));
        if (dataItem.getBadanUsaha().isSosialisasiBPJS()) {
            rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_sudah);
        } else {
            rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_belum);
        }
        if (dataItem.getBadanUsaha().isPesertaJKNOrKIS()) {
            rGroupJknKis.check(R.id.rd_jknkis_sudah);
        } else {
            rGroupJknKis.check(R.id.rd_jknkis_belum);
        }

        edtJumlahKaryawanTerdaftar.setText(String.valueOf(dataItem.getBadanUsaha().getJumlahKaryawanTerdaftar()));
        edtJumlahKeluargaTerdaftar.setText(String.valueOf(dataItem.getBadanUsaha().getJumlahKeluargaTerdaftar()));
        if (dataItem.getBadanUsaha().isAsuransiKesehatan()) {
            rGroupAskes.check(R.id.rd_asurankes_sudah);
        } else {
            rGroupAskes.check(R.id.rd_asurankes_belum);
        }

        edtTambahan.setText(dataItem.getBadanUsaha().getKeterangan());
//            mSignaturePad.setSignatureBitmap(StringToBitMap(dataItem.getBadanUsaha().getTtdImage().getUrl()));

        edtPsNama.setText(dataItem.getContactBadanUsaha().getName());
        edtPsJabatan.setText(dataItem.getContactBadanUsaha().getJabatan());
        edtPsUnitKerja.setText(dataItem.getContactBadanUsaha().getUnitKerja());
        edtPsPhone.setText(dataItem.getContactBadanUsaha().getPhone());

    }

    void setviewOnly() {
        ArrayList<FormData> forms = dataSource.getAllformbykode(kodeForm);
        if (forms.size() == 1) {
            final FormData data = forms.get(0);
//                Toast.makeText(InsertDataBPJS.this, data.getF_ASURANSIKES()+","+data.getF_HC_BERSEDIA_MENDAFTAR() ,Toast.LENGTH_LONG).show();
            tgl_wkt_knj.setText(data.getF_TGL_KUNJUNGAN());
            tgl_pnd.setText(data.getF_TGL_KESEDIAAN_PENDAFTARAN());
            tgl_peringatan_daftar.setText(data.getF_TGL_PERINGATAN_PENDAFTARAN());
            tgl_max_bu.setText(data.getF_TGL_PENDAFTARAN_BU());
            tgl_serah_data.setText(data.getF_TGL_PENDAFTARAN_BU());
            edtNamaBadanUsaha.setText(data.getF_BDN_USH());
            edtAlamat.setText(data.getF_ALAMAT());
            edtTelp.setText(data.getF_PHONE());
            edtEmail.setText(data.getF_EMAIL());
            edtBidangUsaha.setText(data.getF_BIDANG_USH());
            edtJumlahKaryawan.setText(data.getF_JUMLAHKAR());
            edtJumlahKeluarga.setText(data.getF_JUMLAHKEL());
            if (data.getF_MENGIKUTI_SOSIALISASI_BPJS_KES().toLowerCase().equals("sudah")) {
                rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_sudah);
            } else {
                rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_belum);
            }
            if (data.getF_JKN_KIS().toLowerCase().equals("sudah")) {
                rGroupJknKis.check(R.id.rd_jknkis_sudah);
            } else {
                rGroupJknKis.check(R.id.rd_jknkis_belum);
            }
            edtJumlahKaryawanTerdaftar.setText(data.getF_JUMLAHTERDAFTARKAR());
            edtJumlahKeluargaTerdaftar.setText(data.getF_JUMLAHTERDAFTARKEL());

            if (data.getF_ASURANSIKES().toLowerCase().equals("sudah")) {
                rGroupAskes.check(R.id.rd_asurankes_sudah);
            } else {
                rGroupAskes.check(R.id.rd_asurankes_belum);
            }
            edtTambahan.setText(data.getF_TAMBAHAN());
            edtPsNama.setText(data.getF_KP_NAMA());
            edtPsJabatan.setText(data.getF_KP_JABATAN());
            edtPsUnitKerja.setText(data.getF_KP_UNIT_KERJA());
            edtPsPhone.setText(data.getF_KP_PHONE());
            if (data.getF_HC_BERSEDIA_MENDAFTAR().toLowerCase().equals("ya")) {
                rGroupBersediaMendaftar.check(R.id.rd_bersediadaftar_ya);
            } else {
                rGroupBersediaMendaftar.check(R.id.rd_bersediadaftar_tidak);
            }
            edtalasan.setText(data.getF_HC_ALASAN());
            edttindaklanjut.setText(data.getF_HC_TINDAK_LANJUT());
            edtkendala.setText(data.getF_HC_KENDALA());

            ArrayList<FormFile> file = dataSource.getAllfilebykode(kodeForm);
            if (file.size() == 1) {
                final FormFile files = file.get(0);
                photo.setImageBitmap(StringToBitMap(files.getFile_image()));
                mSignaturePad.setSignatureBitmap(StringToBitMap(files.getFile_ttd()));
            }

        } else {
            Toast.makeText(InsertDataBPJS.this, "Data tidak ditemukan", Toast.LENGTH_LONG).show();
        }

        btnCamera.setVisibility(View.GONE);
        btnGallery.setVisibility(View.GONE);

        tgl_wkt_knj.setEnabled(false);
        tgl_pnd.setEnabled(false);
        tgl_peringatan_daftar.setEnabled(false);
        tgl_max_bu.setEnabled(false);
        tgl_serah_data.setEnabled(false);

        edtNamaBadanUsaha.setEnabled(false);
        edtAlamat.setEnabled(false);
        edtTelp.setEnabled(false);
        edtEmail.setEnabled(false);
        edtBidangUsaha.setEnabled(false);
        edtJumlahKaryawan.setEnabled(false);
        edtJumlahKeluarga.setEnabled(false);
        rGroupSosialisasiBpjs.setEnabled(false);
        rGroupJknKis.setEnabled(false);
        edtJumlahKaryawanTerdaftar.setEnabled(false);
        edtJumlahKeluargaTerdaftar.setEnabled(false);
        rGroupAskes.setEnabled(false);
        edtTambahan.setEnabled(false);
        edtPsNama.setEnabled(false);
        edtPsJabatan.setEnabled(false);
        edtPsUnitKerja.setEnabled(false);
        edtPsPhone.setEnabled(false);
        rGroupBersediaMendaftar.setEnabled(false);
        edtalasan.setEnabled(false);
        edttindaklanjut.setEnabled(false);
        edtkendala.setEnabled(false);

        rGroupSosialisasiBpjs.setEnabled(false);
        rGroupBersediaMendaftar.setEnabled(false);
        rGroupAskes.setEnabled(false);
        rGroupJknKis.setEnabled(false);

        mSignaturePad.setEnabled(false);
        clearSignature.setVisibility(View.GONE);

        save_form.setVisibility(View.GONE);
        draft_form.setVisibility(View.GONE);

        save_media.setVisibility(View.VISIBLE);

    }

    void setViewApi() {

        Log.d("note", String.valueOf(dataItem));
        tgl_wkt_knj.setText(dataItem.getCreatedAt());
        tgl_pnd.setText(dataItem.getTPSKP());
        tgl_peringatan_daftar.setText(dataItem.getTPP());
        tgl_max_bu.setText(dataItem.getTMPBU());
        tgl_serah_data.setText(dataItem.getTPD());
        mSignaturePad2.setSignatureBitmap(StringToBitMap(dataItem.getTtdImage().getUrl()));
        edtNotes.setText(dataItem.getNote());
        if (dataItem.isReminder()) {
            rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_sudah);
        } else {
            rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_belum);
        }
        edtalasan.setText(dataItem.getAlasan());
        edttindaklanjut.setText(dataItem.getTindakLanjut());
        edtkendala.setText(dataItem.getKendala());
        edtJumlahRekrutmen.setText(String.valueOf(dataItem.getTotalRecruitment()));

        if (dataItem.isStatus()) {
            rGroupBersediaMendaftar.check(R.id.rd_bersediadaftar_ya);
        } else {
            rGroupBersediaMendaftar.check(R.id.rd_bersediadaftar_tidak);
        }

        edtNamaBadanUsaha.setText(dataItem.getBadanUsaha().getName());
        edtAlamat.setText(dataItem.getBadanUsaha().getAddress());
        edtTelp.setText(dataItem.getBadanUsaha().getPhone());
        edtEmail.setText(dataItem.getBadanUsaha().getEmail());
        edtBidangUsaha.setText(dataItem.getBadanUsaha().getBidangUsaha());
        edtJumlahKaryawan.setText(String.valueOf(dataItem.getBadanUsaha().getJumlahKaryawan()));
        edtJumlahKeluarga.setText(String.valueOf(dataItem.getBadanUsaha().getJumlahKeluarga()));
        if (dataItem.getBadanUsaha().isSosialisasiBPJS()) {
            rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_sudah);
        } else {
            rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_belum);
        }
        if (dataItem.getBadanUsaha().isPesertaJKNOrKIS()) {
            rGroupJknKis.check(R.id.rd_jknkis_sudah);
        } else {
            rGroupJknKis.check(R.id.rd_jknkis_belum);
        }

        edtJumlahKaryawanTerdaftar.setText(String.valueOf(dataItem.getBadanUsaha().getJumlahKaryawanTerdaftar()));
        edtJumlahKeluargaTerdaftar.setText(String.valueOf(dataItem.getBadanUsaha().getJumlahKeluargaTerdaftar()));
        if (dataItem.getBadanUsaha().isAsuransiKesehatan()) {
            rGroupAskes.check(R.id.rd_asurankes_sudah);
        } else {
            rGroupAskes.check(R.id.rd_asurankes_belum);
        }

        edtTambahan.setText(dataItem.getBadanUsaha().getKeterangan());
        mSignaturePad.setSignatureBitmap(StringToBitMap(dataItem.getBadanUsaha().getTtdImage().getUrl()));

        edtPsNama.setText(dataItem.getContactBadanUsaha().getName());
        edtPsJabatan.setText(dataItem.getContactBadanUsaha().getJabatan());
        edtPsUnitKerja.setText(dataItem.getContactBadanUsaha().getUnitKerja());
        edtPsPhone.setText(dataItem.getContactBadanUsaha().getPhone());


        btnCamera.setVisibility(View.GONE);
        btnGallery.setVisibility(View.GONE);

        tgl_wkt_knj.setEnabled(false);
        tgl_pnd.setEnabled(false);
        tgl_peringatan_daftar.setEnabled(false);
        tgl_max_bu.setEnabled(false);
        tgl_serah_data.setEnabled(false);

        edtNamaBadanUsaha.setEnabled(false);
        edtAlamat.setEnabled(false);
        edtTelp.setEnabled(false);
        edtEmail.setEnabled(false);
        edtBidangUsaha.setEnabled(false);
        edtJumlahKaryawan.setEnabled(false);
        edtJumlahKeluarga.setEnabled(false);
        rGroupSosialisasiBpjs.setEnabled(false);
        rGroupJknKis.setEnabled(false);
        edtJumlahKaryawanTerdaftar.setEnabled(false);
        edtJumlahKeluargaTerdaftar.setEnabled(false);
        rGroupAskes.setEnabled(false);
        edtTambahan.setEnabled(false);
        edtPsNama.setEnabled(false);
        edtPsJabatan.setEnabled(false);
        edtPsUnitKerja.setEnabled(false);
        edtPsPhone.setEnabled(false);
        rGroupBersediaMendaftar.setEnabled(false);
        edtalasan.setEnabled(false);
        edttindaklanjut.setEnabled(false);
        edtkendala.setEnabled(false);

        rGroupSosialisasiBpjs.setEnabled(false);
        rGroupBersediaMendaftar.setEnabled(false);
        rGroupAskes.setEnabled(false);
        rGroupJknKis.setEnabled(false);
        edtNotes.setEnabled(false);
        rGroupNotifikasi.setEnabled(false);
        edtJumlahRekrutmen.setEnabled(false);

        mSignaturePad.setEnabled(false);
        clearSignature.setVisibility(View.GONE);

        mSignaturePad2.setEnabled(false);
        clearsignature2.setVisibility(View.GONE);

        save_form.setVisibility(View.GONE);
        draft_form.setVisibility(View.GONE);

        save_media.setVisibility(View.VISIBLE);
    }

    Data constructData() {

        RadioButton rButtonSosialisasiBpjs, rButtonJknKis, rButtonAsKes, rButtonBersediaMendaftar, rButtonNotifikasi;
        Kunjungan kunjungan = new Kunjungan();
        BadanUsaha badanUsaha = new BadanUsaha();
        ContactBadanUsaha contactBadanUsaha = new ContactBadanUsaha();
        Data data = new Data();
        data.setKunjungan(kunjungan);
        data.setBadanUsaha(badanUsaha);
        data.setContactBadanUsaha(contactBadanUsaha);

        kunjungan.setTPSKP(tgl_pnd.getText().toString());
        kunjungan.setTPP(tgl_peringatan_daftar.getText().toString());
        kunjungan.setTMPBU(tgl_max_bu.getText().toString());
        kunjungan.setTPD(tgl_serah_data.getText().toString());
        kunjungan.setTtdImage(BitMapToString(mSignaturePad2.getSignatureBitmap()));
        kunjungan.setNote(edtNotes.getText().toString());
        int selectedIdNotifikasi = rGroupNotifikasi.getCheckedRadioButtonId();
        rButtonNotifikasi = findViewById(selectedIdNotifikasi);
        String valueNotif = rButtonNotifikasi.getText().toString();
        if (valueNotif.equals("Ya")) {
            kunjungan.setReminder(1);
        } else {
            kunjungan.setReminder(0);
        }
        kunjungan.setAlasan(edtalasan.getText().toString());
        kunjungan.setTindakLanjut(edttindaklanjut.getText().toString());
        kunjungan.setKendala(edtkendala.getText().toString());
        kunjungan.setTotalRecruitment(edtJumlahRekrutmen.getText().toString());

        int selectedIdStatus = rGroupBersediaMendaftar.getCheckedRadioButtonId();
        rButtonBersediaMendaftar = findViewById(selectedIdStatus);
        String valueStatus = rButtonBersediaMendaftar.getText().toString();
        if (valueStatus.equals("Ya")) {
            kunjungan.setStatus("1");
        } else {
            kunjungan.setStatus("0");
        }


        badanUsaha.setName(edtNamaBadanUsaha.getText().toString());
        badanUsaha.setAddress(edtAlamat.getText().toString());
        badanUsaha.setPhone(edtTelp.getText().toString());
        badanUsaha.setEmail(edtEmail.getText().toString());
        badanUsaha.setBidangUsaha(edtBidangUsaha.getText().toString());
        badanUsaha.setJumlahKaryawan(edtJumlahKaryawan.getText().toString());
        badanUsaha.setJumlahKeluarga(edtJumlahKeluarga.getText().toString());

        int selectedIdSosialisasiBpjs = rGroupSosialisasiBpjs.getCheckedRadioButtonId();
        rButtonSosialisasiBpjs = findViewById(selectedIdSosialisasiBpjs);
        String valueSosial = rButtonSosialisasiBpjs.getText().toString();
        if (valueSosial.equals("Ya")) {
            badanUsaha.setSosialisasiBPJS("1");
        } else {
            badanUsaha.setSosialisasiBPJS("0");
        }

        int selectedIdJknKis = rGroupJknKis.getCheckedRadioButtonId();
        rButtonJknKis = findViewById(selectedIdJknKis);
        String valuePesertaJKN = rButtonJknKis.getText().toString();
        if (valuePesertaJKN.equals("Ya")) {
            badanUsaha.setPesertaJKNOrKIS("1");
        } else {
            badanUsaha.setPesertaJKNOrKIS("0");
        }

        badanUsaha.setJumlahKaryawanTerdaftar(edtJumlahKaryawanTerdaftar.getText().toString());
        badanUsaha.setJumlahKeluargaTerdaftar(edtJumlahKeluargaTerdaftar.getText().toString());

        int selectedIdAsKes = rGroupAskes.getCheckedRadioButtonId();
        rButtonAsKes = findViewById(selectedIdAsKes);
        String valueAsuransi = rButtonAsKes.getText().toString();
        if (valueAsuransi.equals("Ya")) {
            badanUsaha.setAsuransiKesehatan("1");
        } else {
            badanUsaha.setAsuransiKesehatan("0");
        }
        badanUsaha.setKeterangan(edtTambahan.getText().toString());
        badanUsaha.setTtdImage(BitMapToString(mSignaturePad.getSignatureBitmap()));

        contactBadanUsaha.setName(edtPsNama.getText().toString());
        contactBadanUsaha.setJabatan(edtPsJabatan.getText().toString());
        contactBadanUsaha.setUnitKerja(edtPsUnitKerja.getText().toString());
        contactBadanUsaha.setPhone(edtPsPhone.getText().toString());
        return data;
    }

    void savePost(final Data data) {
        String token = sharedPrefManager.getSpToken();
        Service service = Client.getClient().create(Service.class);
        Call<PostResponse> call = service.saveKunjungan("Bearer " + token, data);
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                Log.d("test", data.toString());
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.d("test2", data.toString());
            }
        });
    }


    void saveNewData() {
        BitmapDrawable drawable1 = (BitmapDrawable) photo.getDrawable();
        bitmapPhoto = drawable1.getBitmap();
        RadioButton rButtonSosialisasiBpjs, rButtonJknKis, rButtonAsKes, rButtonBersediaMendaftar;
        FormData data = new FormData();
        data.setF_KODE(tgl_wkt_knj.getText().toString().replaceAll("/", "").replace(" ", "").replaceAll(":", ""));
        data.setF_TGL_KUNJUNGAN(tgl_wkt_knj.getText().toString());
        data.setF_TGL_KESEDIAAN_PENDAFTARAN(tgl_pnd.getText().toString());
        data.setF_TGL_PERINGATAN_PENDAFTARAN(tgl_peringatan_daftar.getText().toString());
        data.setF_TGL_PENDAFTARAN_BU(tgl_max_bu.getText().toString());
        data.setF_TGL_PENYERAHAN_DATA(tgl_serah_data.getText().toString());
        data.setF_BDN_USH(edtNamaBadanUsaha.getText().toString());
        data.setF_ALAMAT(edtAlamat.getText().toString());
        data.setF_PHONE(edtTelp.getText().toString());
        data.setF_EMAIL(edtEmail.getText().toString());
        data.setF_BIDANG_USH(edtBidangUsaha.getText().toString());
        data.setF_JUMLAHKAR(edtJumlahKaryawan.getText().toString());
        data.setF_JUMLAHKEL(edtJumlahKeluarga.getText().toString());
        int selectedIdSosialisasiBpjs = rGroupSosialisasiBpjs.getCheckedRadioButtonId();
        rButtonSosialisasiBpjs = findViewById(selectedIdSosialisasiBpjs);
        data.setF_MENGIKUTI_SOSIALISASI_BPJS_KES(rButtonSosialisasiBpjs.getText().toString());
        int selectedIdJknKis = rGroupJknKis.getCheckedRadioButtonId();
        rButtonJknKis = findViewById(selectedIdJknKis);
        data.setF_JKN_KIS(rButtonJknKis.getText().toString());
        data.setF_JUMLAHTERDAFTARKAR(edtJumlahKaryawanTerdaftar.getText().toString());
        data.setF_JUMLAHTERDAFTARKEL(edtJumlahKeluargaTerdaftar.getText().toString());
        int selectedIdAsKes = rGroupAskes.getCheckedRadioButtonId();
        rButtonAsKes = findViewById(selectedIdAsKes);
        data.setF_ASURANSIKES(rButtonAsKes.getText().toString());
        data.setF_TAMBAHAN(edtTambahan.getText().toString());
        data.setF_KP_NAMA(edtPsNama.getText().toString());
        data.setF_KP_JABATAN(edtPsJabatan.getText().toString());
        data.setF_KP_UNIT_KERJA(edtPsUnitKerja.getText().toString());
        data.setF_KP_PHONE(edtPsPhone.getText().toString());
        int selectedIdBersediaMendaftar = rGroupBersediaMendaftar.getCheckedRadioButtonId();
        rButtonBersediaMendaftar = findViewById(selectedIdBersediaMendaftar);
        data.setF_HC_BERSEDIA_MENDAFTAR(rButtonBersediaMendaftar.getText().toString());
        data.setF_HC_ALASAN(edtalasan.getText().toString());
        data.setF_HC_TINDAK_LANJUT(edttindaklanjut.getText().toString());
        data.setF_HC_KENDALA(edtkendala.getText().toString());
        data.setF_SAVE_DRAFT(savedraft);
        long result = dataSource.createForm(data);
//                Toast.makeText(MainActivity.this,String.valueOf(result),Toast.LENGTH_LONG).show();
        if (result > 0) {
            if (home.equals("200")) {
                Intent is;
//                if(savedraft.equals("1")){
                is = new Intent(InsertDataBPJS.this, ListView_BPJS_Draft.class);
//                }else
//                {
//                    is = new Intent(InsertDataBPJS.this, ListView_BPJS.class);
//                }
                startActivity(is);
                finish();
            }
        } else {
            Toast.makeText(InsertDataBPJS.this, "Simpan gagal, Silahkan coba lagi", Toast.LENGTH_LONG).show();
        }
    }

    void editForm() {
        BitmapDrawable drawable1 = (BitmapDrawable) photo.getDrawable();
        bitmapPhoto = drawable1.getBitmap();

        RadioButton rButtonSosialisasiBpjs, rButtonJknKis, rButtonAsKes, rButtonBersediaMendaftar;
        FormData data = new FormData();
        data.setF_KODE(kodeForm);
        data.setF_TGL_KUNJUNGAN(tgl_wkt_knj.getText().toString());
        data.setF_TGL_KESEDIAAN_PENDAFTARAN(tgl_pnd.getText().toString());
        data.setF_TGL_PERINGATAN_PENDAFTARAN(tgl_peringatan_daftar.getText().toString());
        data.setF_TGL_PENDAFTARAN_BU(tgl_max_bu.getText().toString());
        data.setF_TGL_PENYERAHAN_DATA(tgl_serah_data.getText().toString());
        data.setF_BDN_USH(edtNamaBadanUsaha.getText().toString());
        data.setF_ALAMAT(edtAlamat.getText().toString());
        data.setF_PHONE(edtTelp.getText().toString());
        data.setF_EMAIL(edtEmail.getText().toString());
        data.setF_BIDANG_USH(edtBidangUsaha.getText().toString());
        data.setF_JUMLAHKAR(edtJumlahKaryawan.getText().toString());
        data.setF_JUMLAHKEL(edtJumlahKeluarga.getText().toString());
        int selectedIdSosialisasiBpjs = rGroupSosialisasiBpjs.getCheckedRadioButtonId();
        rButtonSosialisasiBpjs = findViewById(selectedIdSosialisasiBpjs);
        data.setF_MENGIKUTI_SOSIALISASI_BPJS_KES(rButtonSosialisasiBpjs.getText().toString());
        int selectedIdJknKis = rGroupJknKis.getCheckedRadioButtonId();
        rButtonJknKis = findViewById(selectedIdJknKis);
        data.setF_JKN_KIS(rButtonJknKis.getText().toString());
        data.setF_JUMLAHTERDAFTARKAR(edtJumlahKaryawanTerdaftar.getText().toString());
        data.setF_JUMLAHTERDAFTARKEL(edtJumlahKeluargaTerdaftar.getText().toString());
        int selectedIdAsKes = rGroupAskes.getCheckedRadioButtonId();
        rButtonAsKes = findViewById(selectedIdAsKes);
        data.setF_ASURANSIKES(rButtonAsKes.getText().toString());
        data.setF_TAMBAHAN(edtTambahan.getText().toString());
        data.setF_KP_NAMA(edtPsNama.getText().toString());
        data.setF_KP_JABATAN(edtPsJabatan.getText().toString());
        data.setF_KP_UNIT_KERJA(edtPsUnitKerja.getText().toString());
        data.setF_KP_PHONE(edtPsPhone.getText().toString());
        int selectedIdBersediaMendaftar = rGroupBersediaMendaftar.getCheckedRadioButtonId();
        rButtonBersediaMendaftar = findViewById(selectedIdBersediaMendaftar);
        data.setF_HC_BERSEDIA_MENDAFTAR(rButtonBersediaMendaftar.getText().toString());
        data.setF_HC_ALASAN(edtalasan.getText().toString());
        data.setF_HC_TINDAK_LANJUT(edttindaklanjut.getText().toString());
        data.setF_HC_KENDALA(edtkendala.getText().toString());
        data.setF_SAVE_DRAFT(savedraft);
        long result = dataSource.updateform(data);
//                Toast.makeText(MainActivity.this,String.valueOf(result),Toast.LENGTH_LONG).show();
        if (result > 0) {
            if (home.equals("200")) {
                Intent is;
                if (savedraft.equals("1")) {
                    is = new Intent(InsertDataBPJS.this, ListView_BPJS_Draft.class);
                } else {
                    is = new Intent(InsertDataBPJS.this, ListView_BPJS.class);
                }
                startActivity(is);
                finish();
            }
        } else {
            Toast.makeText(InsertDataBPJS.this, "Simpan gagal, Silahkan coba lagi", Toast.LENGTH_LONG).show();
        }
    }

    private void saveFile() {
        FormFile file = new FormFile();
        file.setFile_kode(tgl_wkt_knj.getText().toString().replaceAll("/", "").replace(" ", "").replaceAll(":", ""));
        file.setFile_image(BitMapToString(bitmapPhoto));
        file.setFile_ttd(BitMapToString(mSignaturePad.getSignatureBitmap()));
        Log.d("ttd", String.valueOf(mSignaturePad.getSignatureBitmap()));
        dataSource.createFile(file);
    }


    private void editFile() {
        FormFile file = new FormFile();
        file.setFile_kode(kodeForm);
        file.setFile_image(BitMapToString(bitmapPhoto));
        file.setFile_ttd(BitMapToString(mSignaturePad.getSignatureBitmap()));
        dataSource.updatefile(file);
    }

    void datePicker() {
        new DatePickerDialog(InsertDataBPJS.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onBackPressed() {
        if (edit.equals("1")) {
            new AlertDialog.Builder(this)
                    .setMessage("Dou you want cancel edit ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent is;
                            if (savedraft.equals("1")) {
                                is = new Intent(InsertDataBPJS.this, ListView_BPJS_Draft.class);
                            } else {
                                is = new Intent(InsertDataBPJS.this, ListView_BPJS.class);
                            }
                            startActivity(is);
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })

                    .create()
                    .show();
        } else {
            InsertDataBPJS.this.finish();
        }
    }

    private void saveMediaPhotoTtd(Bitmap bitmapPhoto, Bitmap ttdBitmap, Bitmap ttd2Bitmap) {
        File myDirPhotos = new File(Environment.getExternalStorageDirectory() + File.separator + "Canfaro/Photo");
        myDirPhotos.mkdirs();
        String photoname = edtPsNama.getText().toString() + "_" + kodeForm + "_.png";
        File file = new File(myDirPhotos, photoname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmapPhoto.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        File myDirTtd = new File(Environment.getExternalStorageDirectory() + File.separator + "Canfaro/Tanda Tangan");
        myDirTtd.mkdirs();
        String ttdname = edtPsNama.getText().toString() + "_" + kodeForm + "_.png";
        File files = new File(myDirTtd, ttdname);
        if (files.exists()) files.delete();
        try {
            FileOutputStream out = new FileOutputStream(files);
            ttdBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        File myDirTtd2 = new File(Environment.getExternalStorageDirectory() + File.separator + "Canfaro/Tanda Tangan BadanUsaha");
        myDirTtd2.mkdirs();
        String ttdname2 = edtPsNama.getText().toString() + "_" + kodeForm + "_.png";
        File files2 = new File(myDirTtd, ttdname2);
        if (files2.exists()) files2.delete();
        try {
            FileOutputStream out = new FileOutputStream(files2);
            ttd2Bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ToatsMessage();
    }

    private void ToatsMessage() {
        View parentLayout = this.findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Photo dan Tanda Tangan Berhasil Disimpan, Silahkan Periksa Folder Canfaro di Directory anda", Snackbar.LENGTH_INDEFINITE)
                .setAction("Tutup", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }

    private void uploadPhoto() {
        String token = sharedPrefManager.getSpToken();
        File file = new File(String.valueOf(fileUri));
        Map<String, RequestBody> map = new HashMap<>();
        RequestBody requestBody = RequestBody.create(MediaType.parse(" "), file);
        map.put("file\"; filename=\"" + file + "\"", requestBody);
        Call<ImageResponse> imageCall = Client.getClient().create(Service.class).uploadImage("Bearer " + token, "user", map);
        imageCall.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                assert response.body() != null;
                Log.d("berhasil" , String.valueOf(response.body().getData()));
                dataPhoto = String.valueOf(response.body().getData());

            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
    }

}
