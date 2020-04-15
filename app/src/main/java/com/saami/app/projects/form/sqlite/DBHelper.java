package com.saami.app.projects.form.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME = "form.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_FORM = "tableform";
    public static final String F_ID = "id";
    public static final String F_KODE = "kode";
    public static final String F_TGL_KUNJUNGAN = "tgl_kunjungan";
    public static final String F_TGL_KESEDIAAN_PENDAFTARAN = "tgl_kesediaan_pendafataran";
    public static final String F_TGL_PERINGATAN_PENDAFTARAN = "tgl_peringatan_pendaftaran";
    public static final String F_TGL_PENDAFTARAN_BU = "tgl_pendaftaran_bu";
    public static final String F_TGL_PENYERAHAN_DATA = "tgl_penyerahan_data";
    public static final String F_BDN_USH = "badan_usaha";
    public static final String F_ALAMAT = "alamat";
    public static final String F_PHONE = "phone_num";
    public static final String F_EMAIL = "email";
    public static final String F_BIDANG_USH = "bidang_usaha";
    public static final String F_JUMLAHKAR = "jumlahkaryawan";
    public static final String F_JUMLAHKEL = "jumlahkeluarga";
    public static final String F_MENGIKUTI_SOSIALISASI_BPJS_KES = "sosialisasibpjskes";
    public static final String F_JKN_KIS = "jknkis";
    public static final String F_JUMLAHTERDAFTARKAR = "jumlahterdaftarkaryawan";
    public static final String F_JUMLAHTERDAFTARKEL = "jumlahterdaftarkeluarga";
    public static final String F_ASURANSIKES = "asuransikesehatan";
    public static final String F_TAMBAHAN = "tambahan_lainlain";
    public static final String F_KP_NAMA = "kp_nama";
    public static final String F_KP_JABATAN = "kp_jabatan";
    public static final String F_KP_UNIT_KERJA = "kp_unitkerja";
    public static final String F_KP_PHONE = "kp_phone";
    public static final String F_HC_BERSEDIA_MENDAFTAR = "hc_bersedia_mendaftar";
    public static final String F_HC_ALASAN = "hc_alasan";
    public static final String F_HC_TINDAK_LANJUT = "tindaklanjut";
    public static final String F_HC_KENDALA = "hc_kendala";
    public static final String F_FLAG_SAVE_DRAFT = "save_draft";

    public static final String TABLE_FILE = "table_file";
    public static final String FILE_ID = "id";
    public static final String FILE_KODE = "kode";
    public static final String FILE_IMAGE = "imgbase64";
    public static final String FILE_TTD = "ttdbase64";

    // SQLITE

    private static final String db_form = "create table "
            +TABLE_FORM+"("
            +F_ID +" INTEGER primary key autoincrement not null,"
            +F_KODE+" text,"
            +F_TGL_KUNJUNGAN+" text,"
            +F_TGL_KESEDIAAN_PENDAFTARAN+ " text,"
            +F_TGL_PERINGATAN_PENDAFTARAN+ " text,"
            +F_TGL_PENDAFTARAN_BU+" text,"
            +F_TGL_PENYERAHAN_DATA+" text,"
            +F_BDN_USH+ " text,"
            +F_ALAMAT+ " text,"
            +F_PHONE+" text,"
            +F_EMAIL+ " text,"
            +F_BIDANG_USH+ " text,"
            +F_JUMLAHKAR+" text,"
            +F_JUMLAHKEL+ " text,"
            +F_MENGIKUTI_SOSIALISASI_BPJS_KES+ " text,"
            +F_JKN_KIS+" text,"
            +F_JUMLAHTERDAFTARKAR+ " text,"
            +F_JUMLAHTERDAFTARKEL+ " text,"
            +F_ASURANSIKES+" text,"
            +F_TAMBAHAN+ " text,"
            +F_KP_NAMA+ " text,"
            +F_KP_JABATAN+" text,"
            +F_KP_UNIT_KERJA+ " text,"
            +F_KP_PHONE+ " text,"
            +F_HC_BERSEDIA_MENDAFTAR+" text,"
            +F_HC_ALASAN+ " text,"
            +F_HC_TINDAK_LANJUT+" text,"
            +F_HC_KENDALA+ " text,"
            +F_FLAG_SAVE_DRAFT+" INTEGER default 0);";

    private static final String db_file = "create table "
            +TABLE_FILE+"("
            +FILE_ID +" INTEGER primary key autoincrement not null,"
            +FILE_KODE+" text,"
            +FILE_IMAGE+" text,"
            +FILE_TTD+" text);";

    public DBHelper(Context context, String nama , SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
        // Auto generated
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(db_form);
        db.execSQL(db_file);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
