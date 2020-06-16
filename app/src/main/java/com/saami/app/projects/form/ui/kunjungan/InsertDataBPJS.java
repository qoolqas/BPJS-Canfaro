package com.saami.app.projects.form.ui.kunjungan;

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
import android.provider.MediaStore;
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
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.mindorks.paracamera.Camera;
import com.saami.app.projects.form.R;
import com.saami.app.projects.form.SharedPrefManager;
import com.saami.app.projects.form.connection.Client;
import com.saami.app.projects.form.connection.Service;
import com.saami.app.projects.form.model.image.ImageResponse;
import com.saami.app.projects.form.model.kunjungan.DataItem;
import com.saami.app.projects.form.model.kunjungan.edit.KunjunganEditResponse;

import com.saami.app.projects.form.model.kunjungan.post.KunjunganPostResponse;
import com.saami.app.projects.form.model.post.BadanUsaha;
import com.saami.app.projects.form.model.post.ContactBadanUsaha;
import com.saami.app.projects.form.model.post.Data;
import com.saami.app.projects.form.model.post.Kunjungan;
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
    ImageView imageTtd, imageTtdBu;

    final Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;
    int tgl;

    String home = "0";
    String edit = "0";
    String view = "0";
    String dataPhoto = "";
    String dataTtd = "";
    String dataTtdBu = "";
    String kodeForm = "";
    String idKunjungan;
    private Camera camera;
    private Bitmap bitmapPhoto;
    private SignaturePad mSignaturePad, mSignaturePad2;
    private Button clearSignature, clearsignature2;
    String savedraft = "0";
    private Button save_media;
    SharedPrefManager sharedPrefManager;
    DataItem dataItem;
    com.saami.app.projects.form.model.badanusaha.DataItem databu;
    Uri fileUri;
    Uri ttdUri;
    Uri ttdUri2;
    String filePath = "";
    CardView cardTtd, cardTtdbu, cardSignature1, cardSignature2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataItem = getIntent().getParcelableExtra("data");
        databu = getIntent().getParcelableExtra("databu");
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

        cardTtd = findViewById(R.id.cardTtd);
        cardTtdbu = findViewById(R.id.cardTtdBu);
        imageTtd = findViewById(R.id.ttdImage);
        imageTtdBu = findViewById(R.id.ttdImageBu);
        cardSignature1 = findViewById(R.id.cardSignature);
        cardSignature2 = findViewById(R.id.cardSignature2);

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
                    //edtJumlahRekrutmen.setText("");
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
            cardSignature1.setVisibility(View.GONE);
            cardSignature2.setVisibility(View.GONE);
            draft_form.setVisibility(View.GONE);
            setEditApi();
        }
        if (edit.equals("3")){
            save_form.setText("Simpan Pembaruan");
            cardSignature1.setVisibility(View.GONE);
            cardSignature2.setVisibility(View.GONE);
            draft_form.setVisibility(View.GONE);
            setEditSearch();
        }
        save_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedraft = "0";
                if (mSignaturePad.isEmpty() | mSignaturePad2.isEmpty()) {
                    if (edit.equals("2")) {
                        new AlertDialog.Builder(InsertDataBPJS.this)
                                .setMessage("Update Data, Apakah data sudah benar ?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int arg1) {
                                        dialog.dismiss();
                                        uploadPhoto();
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
                    }else  if (edit.equals("3")){
                        new AlertDialog.Builder(InsertDataBPJS.this)
                                .setMessage("Update Data, Apakah data sudah benar ?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int arg1) {
                                        dialog.dismiss();
                                        uploadPhoto();
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
                    else {
                        Toast.makeText(InsertDataBPJS.this, "Silahkan Tanda Tangan Terlebih Dahulu", Toast.LENGTH_LONG).show();
                    }

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

                    } else {
                        new AlertDialog.Builder(InsertDataBPJS.this)
                                .setMessage("Simpan Data, Apakah data sudah benar ?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int arg1) {
                                        dialog.dismiss();
//                                        uploadPhoto();
                                        tempTtd();
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
                if (view.equals("2")) {
                    BitmapDrawable fotoD = (BitmapDrawable) photo.getDrawable();
                    Bitmap foto = fotoD.getBitmap();
                    BitmapDrawable ttdD = (BitmapDrawable) imageTtd.getDrawable();
                    Bitmap ttd = ttdD.getBitmap();
                    BitmapDrawable ttdBuD = (BitmapDrawable) imageTtdBu.getDrawable();
                    Bitmap ttdBu = ttdBuD.getBitmap();
                    saveMediaPhotoTtd(foto, ttd, ttdBu);
                } else {


                    photo.setDrawingCacheEnabled(true);
                    Bitmap bitmapPhotos = photo.getDrawingCache();
                    saveMediaPhotoTtd(bitmapPhotos, mSignaturePad.getSignatureBitmap(), mSignaturePad2.getSignatureBitmap());
                }
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

                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                if (tgl == 1) {
                    String myFormats = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"; //In which you need put here
                    SimpleDateFormat sdfc = new SimpleDateFormat(myFormats, Locale.US);
                    tgl_wkt_knj.setText(sdfc.format(myCalendar.getTime()));
                } else if (tgl == 2) {
                    String myFormats = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"; //In which you need put here
                    SimpleDateFormat sdfc = new SimpleDateFormat(myFormats, Locale.US);
                    tgl_pnd.setText(sdfc.format(myCalendar.getTime()));
                } else if (tgl == 3) {
                    String myFormats = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"; //In which you need put here
                    SimpleDateFormat sdfc = new SimpleDateFormat(myFormats, Locale.US);
                    tgl_peringatan_daftar.setText(sdfc.format(myCalendar.getTime()));
                } else if (tgl == 4) {
                    String myFormats = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"; //In which you need put here
                    SimpleDateFormat sdfc = new SimpleDateFormat(myFormats, Locale.US);
                    tgl_max_bu.setText(sdfc.format(myCalendar.getTime()));
                } else if (tgl == 5) {
                    String myFormats = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"; //In which you need put here
                    SimpleDateFormat sdfc = new SimpleDateFormat(myFormats, Locale.US);
                    tgl_serah_data.setText(sdfc.format(myCalendar.getTime()));
                }

            }

        };

        if (view.equals("1")) {
            cardSignature1.setVisibility(View.GONE);
            cardSignature2.setVisibility(View.GONE);
            setviewOnly();
        }
        if (view.equals("2")) {
            cardSignature1.setVisibility(View.GONE);
            cardSignature2.setVisibility(View.GONE);
            setViewApi();
        }
        if (view.equals("3")){
            cardSignature1.setVisibility(View.GONE);
            cardSignature2.setVisibility(View.GONE);
            setViewSearch();
        }

    }


    private void tempTtd() {
        BitmapDrawable fotoD = (BitmapDrawable) photo.getDrawable();
        Bitmap foto = fotoD.getBitmap();
        saveMediaPhotoTtdTemp(foto, mSignaturePad.getSignatureBitmap(), mSignaturePad2.getSignatureBitmap());
        uploadPhoto();
    }

    private void saveMediaPhotoTtdTemp(Bitmap photo, Bitmap ttdBitmap, Bitmap ttd2Bitmap) {
        File myDirPhotos = new File(Environment.getExternalStorageDirectory() + File.separator + "Canfaro/Temp");
        myDirPhotos.mkdirs();
        String photoname = edtPsNama.getText().toString() + "photo" + "_" + "_.png";
        File file = new File(myDirPhotos, photoname);
        String pathPhoto = file.toString();
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            photo.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        File myDirTtd = new File(Environment.getExternalStorageDirectory() + File.separator + "Canfaro/Temp");
        myDirTtd.mkdirs();
        String ttdname = edtPsNama.getText().toString() + "ttd" + "_" + "_.png";
        File files = new File(myDirTtd, ttdname);
        String pathttd = files.toString();
        if (files.exists()) files.delete();
        try {
            FileOutputStream out = new FileOutputStream(files);
            ttdBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        File myDirTtd2 = new File(Environment.getExternalStorageDirectory() + File.separator + "Canfaro/Temp");

        myDirTtd2.mkdirs();
        String ttdname2 = edtPsNama.getText().toString() + "ttdbu" + "_" + "_.png";
        File files2 = new File(myDirTtd2, ttdname2);
        String pathttd2 = files2.toString();
        if (files2.exists()) files2.delete();
        try {
            FileOutputStream out = new FileOutputStream(files2);
            ttd2Bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        fileUri = Uri.parse(pathPhoto);
        ttdUri = Uri.parse(pathttd);
        ttdUri2 = Uri.parse(pathttd2);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        RequestOptions myOptions = new RequestOptions()
                .centerCrop()
                .override(300, 300);
        Log.d("dataPhoto", dataPhoto);
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
                    Log.d("selected", String.valueOf(selectedImage));

                    Glide.with(InsertDataBPJS.this)
                            .asBitmap()
                            .apply(myOptions)
                            .load(selectedImage)
                            .into(photo);

                }
                break;
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
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
            edtJumlahRekrutmen.setText(data.getF_TOTALREKRUITMEN());
            edtNotes.setText(data.getF_CATATAN());
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
                mSignaturePad2.setSignatureBitmap(StringToBitMap(files.getFile_ttd2()));
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
        edtNotes.setText(dataItem.getNote());
        if (dataItem.isReminder()) {
            rGroupNotifikasi.check(R.id.rd_notifikasi_ya);
        } else {
            rGroupNotifikasi.check(R.id.rd_notifikasi_tidak);
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

        edtPsNama.setText(dataItem.getContactBadanUsaha().getName());
        edtPsJabatan.setText(dataItem.getContactBadanUsaha().getJabatan());
        edtPsUnitKerja.setText(dataItem.getContactBadanUsaha().getUnitKerja());
        edtPsPhone.setText(dataItem.getContactBadanUsaha().getPhone());


        mSignaturePad.setVisibility(View.GONE);
        mSignaturePad2.setVisibility(View.GONE);

        cardTtd.setVisibility(View.VISIBLE);
        cardTtdbu.setVisibility(View.VISIBLE);

        Glide.with(InsertDataBPJS.this)
                .asBitmap()
                .load(dataItem.getImage().getUrl())
                .into(photo);
        Glide.with(InsertDataBPJS.this)
                .asBitmap()
                .load(dataItem.getTtdImage().getUrl())
                .into(imageTtd);
        Glide.with(InsertDataBPJS.this)
                .asBitmap()
                .load(dataItem.getBadanUsaha().getTtdImage().getUrl())
                .into(imageTtdBu);

        clearSignature.setVisibility(View.GONE);
        clearsignature2.setVisibility(View.GONE);

    }
    void setEditSearch() {
        draft_form.setVisibility(View.GONE);

        tgl_wkt_knj.setText(databu.getCreatedAt());
        tgl_pnd.setText(databu.getKunjungan().getTPSKP());
        tgl_peringatan_daftar.setText(databu.getKunjungan().getTPP());
        tgl_max_bu.setText(databu.getKunjungan().getTMPBU());
        tgl_serah_data.setText(databu.getKunjungan().getTPD());
//            mSignaturePad2.setSignatureBitmap(StringToBitMap(databu.getTtdImage().getUrl()));
        edtNotes.setText(databu.getKunjungan().getNote());
        if (databu.getKunjungan().isReminder()) {
            rGroupNotifikasi.check(R.id.rd_notifikasi_ya);
        } else {
            rGroupNotifikasi.check(R.id.rd_notifikasi_tidak);
        }
        edtalasan.setText(databu.getKunjungan().getAlasan());
        edttindaklanjut.setText(databu.getKunjungan().getTindakLanjut());
        edtkendala.setText(databu.getKunjungan().getKendala());
        edtJumlahRekrutmen.setText(String.valueOf(databu.getKunjungan().getTotalRecruitment()));

//        if (databu.getKunjungan().isStatus()) {
//            rGroupBersediaMendaftar.check(R.id.rd_bersediadaftar_ya);
//        } else {
//            rGroupBersediaMendaftar.check(R.id.rd_bersediadaftar_tidak);
//        }
        edtNamaBadanUsaha.setText(databu.getName());
        edtAlamat.setText(databu.getAddress());
        edtTelp.setText(databu.getPhone());
        edtEmail.setText(databu.getEmail());
        edtBidangUsaha.setText(databu.getBidangUsaha());
        edtJumlahKaryawan.setText(String.valueOf(databu.getJumlahKaryawan()));
        edtJumlahKeluarga.setText(String.valueOf(databu.getJumlahKeluarga()));
        if (databu.isSosialisasiBPJS()) {
            rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_sudah);
        } else {
            rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_belum);
        }
        if (databu.isPesertaJKNOrKIS()) {
            rGroupJknKis.check(R.id.rd_jknkis_sudah);
        } else {
            rGroupJknKis.check(R.id.rd_jknkis_belum);
        }

        edtJumlahKaryawanTerdaftar.setText(String.valueOf(databu.getJumlahKaryawanTerdaftar()));
        edtJumlahKeluargaTerdaftar.setText(String.valueOf(databu.getJumlahKeluargaTerdaftar()));
        if (databu.isAsuransiKesehatan()) {
            rGroupAskes.check(R.id.rd_asurankes_sudah);
        } else {
            rGroupAskes.check(R.id.rd_asurankes_belum);
        }

        edtTambahan.setText(databu.getKeterangan());
//            mSignaturePad.setSignatureBitmap(StringToBitMap(databu.getBadanUsaha().getTtdImage().getUrl()));

        edtPsNama.setText(databu.getContactBadanUsaha().getName());
        edtPsJabatan.setText(databu.getContactBadanUsaha().getJabatan());
        edtPsUnitKerja.setText(databu.getContactBadanUsaha().getUnitKerja());
        edtPsPhone.setText(databu.getContactBadanUsaha().getPhone());


        mSignaturePad.setVisibility(View.GONE);
        mSignaturePad2.setVisibility(View.GONE);

        cardTtd.setVisibility(View.VISIBLE);
        cardTtdbu.setVisibility(View.VISIBLE);

        Glide.with(InsertDataBPJS.this)
                .asBitmap()
                .load(databu.getKunjungan().getImage().getUrl())
                .into(photo);
        Glide.with(InsertDataBPJS.this)
                .asBitmap()
                .load(databu.getKunjungan().getTtdImage().getUrl())
                .into(imageTtd);
        Glide.with(InsertDataBPJS.this)
                .asBitmap()
                .load(databu.getTtdImage().getUrl())
                .into(imageTtdBu);

        clearSignature.setVisibility(View.GONE);
        clearsignature2.setVisibility(View.GONE);

    }
    void setViewSearch() {

        Glide.with(InsertDataBPJS.this)
                .asBitmap()
                .load(databu.getKunjungan().getImage().getUrl())
                .into(photo);
        Glide.with(InsertDataBPJS.this)
                .asBitmap()
                .load(databu.getKunjungan().getTtdImage().getUrl())
                .into(imageTtd);
        Glide.with(InsertDataBPJS.this)
                .asBitmap()
                .load(databu.getTtdImage().getUrl())
                .into(imageTtdBu);
        tgl_wkt_knj.setText(databu.getCreatedAt());
        tgl_pnd.setText(databu.getKunjungan().getTPSKP());
        tgl_peringatan_daftar.setText(databu.getKunjungan().getTPP());
        tgl_max_bu.setText(databu.getKunjungan().getTMPBU());
        tgl_serah_data.setText(databu.getKunjungan().getTPD());
        edtNotes.setText(databu.getKunjungan().getNote());
        if (databu.getKunjungan().isReminder()) {
            rGroupNotifikasi.check(R.id.rd_notifikasi_ya);
        } else {
            rGroupNotifikasi.check(R.id.rd_notifikasi_tidak);
        }
        edtalasan.setText(databu.getKunjungan().getAlasan());
        edttindaklanjut.setText(databu.getKunjungan().getTindakLanjut());
        edtkendala.setText(databu.getKunjungan().getKendala());
        edtJumlahRekrutmen.setText(String.valueOf(databu.getKunjungan().getTotalRecruitment()));

//        if (databu.getKunjungan.isStatus()) {
//            rGroupBersediaMendaftar.check(R.id.rd_bersediadaftar_ya);
//        } else {
//            rGroupBersediaMendaftar.check(R.id.rd_bersediadaftar_tidak);
//        }

        edtNamaBadanUsaha.setText(databu.getName());
        edtAlamat.setText(databu.getAddress());
        edtTelp.setText(databu.getPhone());
        edtEmail.setText(databu.getEmail());
        edtBidangUsaha.setText(databu.getBidangUsaha());
        edtJumlahKaryawan.setText(String.valueOf(databu.getJumlahKaryawan()));
        edtJumlahKeluarga.setText(String.valueOf(databu.getJumlahKeluarga()));

        if (databu.isSosialisasiBPJS()) {
            rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_sudah);
        } else {
            rGroupSosialisasiBpjs.check(R.id.rd_sosialisasi_bpjs_belum);
        }
        if (databu.isPesertaJKNOrKIS()) {
            rGroupJknKis.check(R.id.rd_jknkis_sudah);
        } else {
            rGroupJknKis.check(R.id.rd_jknkis_belum);
        }

        edtJumlahKaryawanTerdaftar.setText(String.valueOf(databu.getJumlahKaryawanTerdaftar()));
        edtJumlahKeluargaTerdaftar.setText(String.valueOf(databu.getJumlahKeluargaTerdaftar()));
        if (databu.isAsuransiKesehatan()) {
            rGroupAskes.check(R.id.rd_asurankes_sudah);
        } else {
            rGroupAskes.check(R.id.rd_asurankes_belum);
        }

        edtTambahan.setText(databu.getKeterangan());

        edtPsNama.setText(databu.getContactBadanUsaha().getName());
        edtPsJabatan.setText(databu.getContactBadanUsaha().getJabatan());
        edtPsUnitKerja.setText(databu.getContactBadanUsaha().getUnitKerja());
        edtPsPhone.setText(databu.getContactBadanUsaha().getPhone());


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
        edtJumlahRekrutmen.setEnabled(false);

        rGroupSosialisasiBpjs.setEnabled(false);
        rGroupBersediaMendaftar.setEnabled(false);
        rGroupAskes.setEnabled(false);
        rGroupJknKis.setEnabled(false);
        edtNotes.setEnabled(false);
        rGroupNotifikasi.setEnabled(false);
        edtJumlahRekrutmen.setEnabled(false);

        mSignaturePad.setVisibility(View.GONE);
        clearSignature.setVisibility(View.GONE);

        mSignaturePad2.setVisibility(View.GONE);
        clearsignature2.setVisibility(View.GONE);

        cardTtd.setVisibility(View.VISIBLE);
        cardTtdbu.setVisibility(View.VISIBLE);

        save_form.setVisibility(View.GONE);
        draft_form.setVisibility(View.GONE);

        save_media.setVisibility(View.VISIBLE);
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
            edtJumlahRekrutmen.setText(data.getF_TOTALREKRUITMEN());
            edtNotes.setText(data.getF_CATATAN());
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
                mSignaturePad2.setSignatureBitmap(StringToBitMap(files.getFile_ttd2()));
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

        mSignaturePad2.setEnabled(false);
        clearsignature2.setVisibility(View.GONE);

        save_form.setVisibility(View.GONE);
        draft_form.setVisibility(View.GONE);

        save_media.setVisibility(View.VISIBLE);

    }

    void setViewApi() {

        Log.d("note", String.valueOf(dataItem));
        Glide.with(InsertDataBPJS.this)
                .asBitmap()
                .load(dataItem.getImage().getUrl())
                .into(photo);
        Glide.with(InsertDataBPJS.this)
                .asBitmap()
                .load(dataItem.getTtdImage().getUrl())
                .into(imageTtd);
        Glide.with(InsertDataBPJS.this)
                .asBitmap()
                .load(dataItem.getBadanUsaha().getTtdImage().getUrl())
                .into(imageTtdBu);
        tgl_wkt_knj.setText(dataItem.getCreatedAt());
        tgl_pnd.setText(dataItem.getTPSKP());
        tgl_peringatan_daftar.setText(dataItem.getTPP());
        tgl_max_bu.setText(dataItem.getTMPBU());
        tgl_serah_data.setText(dataItem.getTPD());
        //mSignaturePad2.setSignatureBitmap(StringToBitMap(dataItem.getTtdImage().getUrl()));
        edtNotes.setText(dataItem.getNote());
        if (dataItem.isReminder()) {
            rGroupNotifikasi.check(R.id.rd_notifikasi_ya);
        } else {
            rGroupNotifikasi.check(R.id.rd_notifikasi_tidak);
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
        //mSignaturePad.setSignatureBitmap(StringToBitMap(dataItem.getBadanUsaha().getTtdImage().getUrl()));

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
        edtJumlahRekrutmen.setEnabled(false);

        rGroupSosialisasiBpjs.setEnabled(false);
        rGroupBersediaMendaftar.setEnabled(false);
        rGroupAskes.setEnabled(false);
        rGroupJknKis.setEnabled(false);
        edtNotes.setEnabled(false);
        rGroupNotifikasi.setEnabled(false);
        edtJumlahRekrutmen.setEnabled(false);

        mSignaturePad.setVisibility(View.GONE);
        clearSignature.setVisibility(View.GONE);

        mSignaturePad2.setVisibility(View.GONE);
        clearsignature2.setVisibility(View.GONE);

        cardTtd.setVisibility(View.VISIBLE);
        cardTtdbu.setVisibility(View.VISIBLE);

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
        kunjungan.setTtdImage(dataTtd);
        kunjungan.setImage(dataPhoto);
        kunjungan.setNote(edtNotes.getText().toString());
        int selectedIdNotifikasi = rGroupNotifikasi.getCheckedRadioButtonId();
        rButtonNotifikasi = findViewById(selectedIdNotifikasi);
        String valueNotif = rButtonNotifikasi.getText().toString();
        if (valueNotif.equals("Surat Konfirmasi")) {
            kunjungan.setReminder(1);
        } else {
            kunjungan.setReminder(1);
        }
        kunjungan.setAlasan(edtalasan.getText().toString());
        kunjungan.setTindakLanjut(edttindaklanjut.getText().toString());
        kunjungan.setKendala(edtkendala.getText().toString());
        if (edtJumlahRekrutmen.getText().toString().equals("")) {
            kunjungan.setTotalRecruitment("0");
        } else {
            kunjungan.setTotalRecruitment(edtJumlahRekrutmen.getText().toString());
        }

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
        if (valueSosial.equals("Sudah")) {
            badanUsaha.setSosialisasiBPJS("1");
        } else {
            badanUsaha.setSosialisasiBPJS("0");
        }

        int selectedIdJknKis = rGroupJknKis.getCheckedRadioButtonId();
        rButtonJknKis = findViewById(selectedIdJknKis);
        String valuePesertaJKN = rButtonJknKis.getText().toString();
        if (valuePesertaJKN.equals("Sudah")) {
            badanUsaha.setPesertaJKNOrKIS("1");
        } else {
            badanUsaha.setPesertaJKNOrKIS("0");
        }

        badanUsaha.setJumlahKaryawanTerdaftar(edtJumlahKaryawanTerdaftar.getText().toString());
        badanUsaha.setJumlahKeluargaTerdaftar(edtJumlahKeluargaTerdaftar.getText().toString());

        int selectedIdAsKes = rGroupAskes.getCheckedRadioButtonId();
        rButtonAsKes = findViewById(selectedIdAsKes);
        String valueAsuransi = rButtonAsKes.getText().toString();
        if (valueAsuransi.equals("Sudah")) {
            badanUsaha.setAsuransiKesehatan("1");
        } else {
            badanUsaha.setAsuransiKesehatan("0");
        }
        badanUsaha.setKeterangan(edtTambahan.getText().toString());
        badanUsaha.setTtdImage(dataTtdBu);

        contactBadanUsaha.setName(edtPsNama.getText().toString());
        contactBadanUsaha.setJabatan(edtPsJabatan.getText().toString());
        contactBadanUsaha.setUnitKerja(edtPsUnitKerja.getText().toString());
        contactBadanUsaha.setPhone(edtPsPhone.getText().toString());
        return data;
    }

    Data constructDataEdit() {

        RadioButton rButtonSosialisasiBpjs, rButtonJknKis, rButtonAsKes, rButtonBersediaMendaftar, rButtonNotifikasi;
        Kunjungan kunjungan = new Kunjungan();
        BadanUsaha badanUsaha = new BadanUsaha();
        ContactBadanUsaha contactBadanUsaha = new ContactBadanUsaha();
        Data data = new Data();
        data.setKunjungan(kunjungan);
        data.setBadanUsaha(badanUsaha);
        data.setContactBadanUsaha(contactBadanUsaha);
        if (edit.equals("2")){
            kunjungan.setTtdImage(dataItem.getTtdImage().getRaw());
            badanUsaha.setTtdImage(dataItem.getBadanUsaha().getTtdImage().getRaw());
            kunjungan.setImage(dataItem.getImage().getRaw());
        }else {
            kunjungan.setTtdImage(databu.getKunjungan().getTtdImage().getRaw());
            badanUsaha.setTtdImage(databu.getTtdImage().getRaw());
            kunjungan.setImage(databu.getKunjungan().getImage().getRaw());
        }

        kunjungan.setTPSKP(tgl_pnd.getText().toString());
        kunjungan.setTPP(tgl_peringatan_daftar.getText().toString());
        kunjungan.setTMPBU(tgl_max_bu.getText().toString());
        kunjungan.setTPD(tgl_serah_data.getText().toString());
        kunjungan.setNote(edtNotes.getText().toString());
        int selectedIdNotifikasi = rGroupNotifikasi.getCheckedRadioButtonId();
        rButtonNotifikasi = findViewById(selectedIdNotifikasi);
        String valueNotif = rButtonNotifikasi.getText().toString();
        if (valueNotif.equals("Surat Konfirmasi")) {
            kunjungan.setReminder(1);
        } else {
            kunjungan.setReminder(0);
        }
        kunjungan.setAlasan(edtalasan.getText().toString());
        kunjungan.setTindakLanjut(edttindaklanjut.getText().toString());
        kunjungan.setKendala(edtkendala.getText().toString());
        if (edtJumlahRekrutmen.getText().toString().equals("")) {
            kunjungan.setTotalRecruitment("0");
        } else {
            kunjungan.setTotalRecruitment(edtJumlahRekrutmen.getText().toString());
        }


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
        if (valueSosial.equals("Sudah")) {
            badanUsaha.setSosialisasiBPJS("1");
        } else {
            badanUsaha.setSosialisasiBPJS("0");
        }

        int selectedIdJknKis = rGroupJknKis.getCheckedRadioButtonId();
        rButtonJknKis = findViewById(selectedIdJknKis);
        String valuePesertaJKN = rButtonJknKis.getText().toString();
        if (valuePesertaJKN.equals("Sudah")) {
            badanUsaha.setPesertaJKNOrKIS("1");
        } else {
            badanUsaha.setPesertaJKNOrKIS("0");
        }

        badanUsaha.setJumlahKaryawanTerdaftar(edtJumlahKaryawanTerdaftar.getText().toString());
        badanUsaha.setJumlahKeluargaTerdaftar(edtJumlahKeluargaTerdaftar.getText().toString());

        int selectedIdAsKes = rGroupAskes.getCheckedRadioButtonId();
        rButtonAsKes = findViewById(selectedIdAsKes);
        String valueAsuransi = rButtonAsKes.getText().toString();
        if (valueAsuransi.equals("Sudah")) {
            badanUsaha.setAsuransiKesehatan("1");
        } else {
            badanUsaha.setAsuransiKesehatan("0");
        }
        badanUsaha.setKeterangan(edtTambahan.getText().toString());

        contactBadanUsaha.setName(edtPsNama.getText().toString());
        contactBadanUsaha.setJabatan(edtPsJabatan.getText().toString());
        contactBadanUsaha.setUnitKerja(edtPsUnitKerja.getText().toString());
        contactBadanUsaha.setPhone(edtPsPhone.getText().toString());
        return data;
    }

    Data constructDataEditImgChange() {

        RadioButton rButtonSosialisasiBpjs, rButtonJknKis, rButtonAsKes, rButtonBersediaMendaftar, rButtonNotifikasi;
        Kunjungan kunjungan = new Kunjungan();
        BadanUsaha badanUsaha = new BadanUsaha();
        ContactBadanUsaha contactBadanUsaha = new ContactBadanUsaha();
        Data data = new Data();
        data.setKunjungan(kunjungan);
        data.setBadanUsaha(badanUsaha);
        data.setContactBadanUsaha(contactBadanUsaha);

        if (edit.equals("2")){
            kunjungan.setTtdImage(dataItem.getTtdImage().getRaw());
            badanUsaha.setTtdImage(dataItem.getBadanUsaha().getTtdImage().getRaw());
        }else {
            kunjungan.setTtdImage(databu.getKunjungan().getTtdImage().getRaw());
            badanUsaha.setTtdImage(databu.getTtdImage().getRaw());
        }
        kunjungan.setImage(dataPhoto);
        kunjungan.setTPSKP(tgl_pnd.getText().toString());
        kunjungan.setTPP(tgl_peringatan_daftar.getText().toString());
        kunjungan.setTMPBU(tgl_max_bu.getText().toString());
        kunjungan.setTPD(tgl_serah_data.getText().toString());
        kunjungan.setImage(dataPhoto);
        kunjungan.setNote(edtNotes.getText().toString());
        int selectedIdNotifikasi = rGroupNotifikasi.getCheckedRadioButtonId();
        if (edtJumlahRekrutmen.getText().toString().equals("")) {
            kunjungan.setTotalRecruitment("0");
        } else {
            kunjungan.setTotalRecruitment(edtJumlahRekrutmen.getText().toString());
        }
        rButtonNotifikasi = findViewById(selectedIdNotifikasi);
        String valueNotif = rButtonNotifikasi.getText().toString();
        if (valueNotif.equals("Surat Konfirmasi")) {
            kunjungan.setReminder(1);
        } else {
            kunjungan.setReminder(0);
        }
        kunjungan.setAlasan(edtalasan.getText().toString());
        kunjungan.setTindakLanjut(edttindaklanjut.getText().toString());
        kunjungan.setKendala(edtkendala.getText().toString());

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
        if (valueSosial.equals("Sudah")) {
            badanUsaha.setSosialisasiBPJS("1");
        } else {
            badanUsaha.setSosialisasiBPJS("0");
        }

        int selectedIdJknKis = rGroupJknKis.getCheckedRadioButtonId();
        rButtonJknKis = findViewById(selectedIdJknKis);
        String valuePesertaJKN = rButtonJknKis.getText().toString();
        if (valuePesertaJKN.equals("Sudah")) {
            badanUsaha.setPesertaJKNOrKIS("1");
        } else {
            badanUsaha.setPesertaJKNOrKIS("0");
        }

        badanUsaha.setJumlahKaryawanTerdaftar(edtJumlahKaryawanTerdaftar.getText().toString());
        badanUsaha.setJumlahKeluargaTerdaftar(edtJumlahKeluargaTerdaftar.getText().toString());

        int selectedIdAsKes = rGroupAskes.getCheckedRadioButtonId();
        rButtonAsKes = findViewById(selectedIdAsKes);
        String valueAsuransi = rButtonAsKes.getText().toString();
        if (valueAsuransi.equals("Sudah")) {
            badanUsaha.setAsuransiKesehatan("1");
        } else {
            badanUsaha.setAsuransiKesehatan("0");
        }
        badanUsaha.setKeterangan(edtTambahan.getText().toString());

        contactBadanUsaha.setName(edtPsNama.getText().toString());
        contactBadanUsaha.setJabatan(edtPsJabatan.getText().toString());
        contactBadanUsaha.setUnitKerja(edtPsUnitKerja.getText().toString());
        contactBadanUsaha.setPhone(edtPsPhone.getText().toString());
        return data;
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
        data.setF_TOTALREKRUITMEN(edtJumlahRekrutmen.getText().toString());
        data.setF_CATATAN(edtNotes.getText().toString());
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
        data.setF_CATATAN(edtNotes.getText().toString());
        data.setF_TOTALREKRUITMEN(edtJumlahRekrutmen.getText().toString());
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
        file.setFile_ttd2(BitMapToString(mSignaturePad2.getSignatureBitmap()));
        Log.d("ttd", String.valueOf(mSignaturePad.getSignatureBitmap()));
        dataSource.createFile(file);
    }


    private void editFile() {
        FormFile file = new FormFile();
        file.setFile_kode(kodeForm);
        file.setFile_image(BitMapToString(bitmapPhoto));
        file.setFile_ttd(BitMapToString(mSignaturePad.getSignatureBitmap()));
        file.setFile_ttd2(BitMapToString(mSignaturePad2.getSignatureBitmap()));
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
        String photoname = edtPsNama.getText().toString() + "_" + "_.png";
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
        String ttdname = edtPsNama.getText().toString() + "_" + "_.png";
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
        String ttdname2 = edtPsNama.getText().toString() + "_" + "_.png";
        File files2 = new File(myDirTtd2, ttdname2);
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
        if (fileUri == null || fileUri.equals("")) {
            saveEditPost(constructDataEdit());
        } else {
            String token = sharedPrefManager.getSpToken();
            File file = new File(String.valueOf(fileUri));
            Map<String, RequestBody> map = new HashMap<>();
            Log.d("tesfile2", String.valueOf(file));
            final RequestBody requestBody = RequestBody.create(MediaType.parse(" "), file);
            map.put("file\"; filename=\"" + file + "\"", requestBody);
            Call<ImageResponse> imageCall = Client.getClient().create(Service.class).uploadImage("Bearer " + token, "user", map);
            imageCall.enqueue(new Callback<ImageResponse>() {
                @Override
                public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                    assert response.body() != null;
                    Log.d("berhasil", new Gson().toJson(response.body().getData().getFilename()));
                    dataPhoto = response.body().getData().getFilename();
                    if (edit.equals("2" )) {
                        saveEditPost(constructDataEditImgChange());
                    } else if (edit.equals("3")){
                        saveEditPost(constructDataEditImgChange());

                    }else{
                        uploadTtd();
                    }
                }

                @Override
                public void onFailure(Call<ImageResponse> call, Throwable t) {

                }
            });
        }

    }

    private void uploadTtd() {
        String token = sharedPrefManager.getSpToken();
        File file = new File(String.valueOf(ttdUri));
        Log.d("tesfile", String.valueOf(file));
        Map<String, RequestBody> map = new HashMap<>();
        final RequestBody requestBody = RequestBody.create(MediaType.parse(" "), file);
        map.put("file\"; filename=\"" + file + "\"", requestBody);

        Call<ImageResponse> imageCall = Client.getClient().create(Service.class).uploadImage("Bearer " + token, "ttd", map);
        imageCall.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                assert response.body() != null;
                Log.d("berhasil", new Gson().toJson(response.body().getData().getFilename()));
                dataTtd = response.body().getData().getFilename();
                uploadTtdBu();


            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
    }

    private void uploadTtdBu() {

        String token = sharedPrefManager.getSpToken();
        File file = new File(String.valueOf(ttdUri2));
        Map<String, RequestBody> map = new HashMap<>();
        final RequestBody requestBody = RequestBody.create(MediaType.parse(" "), file);
        map.put("file\"; filename=\"" + file + "\"", requestBody);
        Call<ImageResponse> imageCall = Client.getClient().create(Service.class).uploadImage("Bearer " + token, "badan_usaha", map);
        imageCall.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                assert response.body() != null;
                Log.d("berhasil", new Gson().toJson(response.body().getData().getFilename()));
                dataTtdBu = response.body().getData().getFilename();
                savePost(constructData());


            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });


    }

    void savePost(final Data data) {
        String token = sharedPrefManager.getSpToken();
        Service service = Client.getClient().create(Service.class);
        Call<KunjunganPostResponse> call = service.saveKunjungan("Bearer " + token, data);
        call.enqueue(new Callback<KunjunganPostResponse>() {
            @Override
            public void onResponse(Call<KunjunganPostResponse> call, Response<KunjunganPostResponse> response) {
                assert response.body() != null;
                if (response.code() == 200) {
                    Intent intent = new Intent(InsertDataBPJS.this, ListView_BPJS.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else if (response.code() == 422) {
                    Toast.makeText(getApplicationContext(), getString(R.string.msg_422), Toast.LENGTH_SHORT).show();
                    Log.d("422", "422");
                }


            }

            @Override
            public void onFailure(Call<KunjunganPostResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();
                Log.d("failure", "fail");
            }
        });
    }

    void saveEditPost(Data data) {
        String token = sharedPrefManager.getSpToken();
        Service service = Client.getClient().create(Service.class);
        int id;
        if (edit.equals("2")){
            id = dataItem.getId();
        }else {
            id = databu.getKunjungan().getId();
        }
        Call<KunjunganEditResponse> call = service.saveEditKunjungan("Bearer " + token, id, data);
        call.enqueue(new Callback<KunjunganEditResponse>() {
            @Override
            public void onResponse(Call<KunjunganEditResponse> call, Response<KunjunganEditResponse> response) {
                if (response.code() == 200){
                    Intent intent = new Intent(InsertDataBPJS.this, ListView_BPJS.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else if (response.code() == 422) {
                    Toast.makeText(getApplicationContext(), getString(R.string.msg_422), Toast.LENGTH_SHORT).show();
                    Log.d("422", "422");
                }

            }

            @Override
            public void onFailure(Call<KunjunganEditResponse> call, Throwable t) {
                Log.d("failure", "fail");
            }
        });
    }

}
