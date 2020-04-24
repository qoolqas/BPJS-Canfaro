package com.saami.app.projects.form.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBDataSource
{

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    private String[] formdata = new String[]
            {
                    DBHelper.F_KODE,
                    DBHelper.F_TGL_KUNJUNGAN ,
                    DBHelper.F_TGL_KESEDIAAN_PENDAFTARAN ,
                    DBHelper.F_TGL_PERINGATAN_PENDAFTARAN ,
                    DBHelper. F_TGL_PENDAFTARAN_BU ,
                    DBHelper.F_TGL_PENYERAHAN_DATA,
                    DBHelper.F_BDN_USH ,
                    DBHelper.F_ALAMAT,
                    DBHelper.F_PHONE,
                    DBHelper.F_EMAIL,
                    DBHelper.F_BIDANG_USH,
                    DBHelper.F_JUMLAHKAR,
                    DBHelper.F_JUMLAHKEL,
                    DBHelper.F_MENGIKUTI_SOSIALISASI_BPJS_KES ,
                    DBHelper.F_JKN_KIS,
                    DBHelper.F_JUMLAHTERDAFTARKAR ,
                    DBHelper.F_JUMLAHTERDAFTARKEL ,
                    DBHelper.F_ASURANSIKES,
                    DBHelper.F_TAMBAHAN ,
                    DBHelper. F_KP_NAMA ,
                    DBHelper.F_KP_JABATAN ,
                    DBHelper.F_KP_UNIT_KERJA,
                    DBHelper.F_KP_PHONE,
                    DBHelper.F_HC_BERSEDIA_MENDAFTAR,
                    DBHelper.F_HC_ALASAN ,
                    DBHelper.F_HC_TINDAK_LANJUT,
                    DBHelper.F_HC_KENDALA,
                    DBHelper.F_TOTAL_REKRUITMEN,
                    DBHelper.F_CATATAN,
                    DBHelper.F_FLAG_SAVE_DRAFT

            };

    private String[] formfile = new String[]
            {
                    DBHelper.FILE_KODE,
                    DBHelper.FILE_IMAGE,
                    DBHelper.FILE_TTD,
                    DBHelper.FILE_TTD2
            };

    public DBDataSource(Context context) {
        dbHelper = new DBHelper(context, DBHelper.DB_NAME, null, DBHelper.DB_VERSION);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }



    public void close() {
        dbHelper.close();
    }


    public long createForm(FormData data) {
        open();
        ContentValues values = new ContentValues();
        values.put(DBHelper.F_KODE,data.getF_KODE());
        values.put(DBHelper.F_TGL_KUNJUNGAN ,data.getF_TGL_KUNJUNGAN());
        values.put(DBHelper.F_TGL_KESEDIAAN_PENDAFTARAN ,data.getF_TGL_KESEDIAAN_PENDAFTARAN());
        values.put(DBHelper.F_TGL_PERINGATAN_PENDAFTARAN ,data.getF_TGL_PERINGATAN_PENDAFTARAN());
        values.put(DBHelper.F_TGL_PENDAFTARAN_BU ,data.getF_TGL_PENDAFTARAN_BU());
        values.put(DBHelper.F_TGL_PENYERAHAN_DATA ,data.getF_TGL_PENYERAHAN_DATA());
        values.put(DBHelper.F_BDN_USH ,data.getF_BDN_USH());
        values.put(DBHelper.F_ALAMAT,data.getF_ALAMAT());
        values.put(DBHelper.F_PHONE,data.getF_PHONE());
        values.put(DBHelper.F_EMAIL,data.getF_EMAIL());
        values.put(DBHelper.F_BIDANG_USH,data.getF_BIDANG_USH());
        values.put(DBHelper.F_JUMLAHKAR,data.getF_JUMLAHKAR());
        values.put(DBHelper.F_JUMLAHKEL,data.getF_JUMLAHKEL());
        values.put(DBHelper.F_MENGIKUTI_SOSIALISASI_BPJS_KES ,data.getF_MENGIKUTI_SOSIALISASI_BPJS_KES());
        values.put(DBHelper.F_JKN_KIS,data.getF_JKN_KIS());
        values.put(DBHelper.F_JUMLAHTERDAFTARKAR ,data.getF_JUMLAHTERDAFTARKAR());
        values.put(DBHelper.F_JUMLAHTERDAFTARKEL ,data.getF_JUMLAHTERDAFTARKEL());
        values.put(DBHelper.F_ASURANSIKES,data.getF_ASURANSIKES());
        values.put(DBHelper.F_TAMBAHAN ,data.getF_TAMBAHAN());
        values.put(DBHelper. F_KP_NAMA ,data.getF_KP_NAMA());
        values.put(DBHelper.F_KP_JABATAN ,data.getF_KP_JABATAN());
        values.put(DBHelper.F_KP_UNIT_KERJA,data.getF_KP_UNIT_KERJA());
        values.put(DBHelper.F_KP_PHONE,data.getF_PHONE());
        values.put(DBHelper.F_HC_BERSEDIA_MENDAFTAR,data.getF_HC_BERSEDIA_MENDAFTAR());
        values.put( DBHelper.F_HC_ALASAN ,data.getF_HC_ALASAN());
        values.put(DBHelper.F_HC_TINDAK_LANJUT,data.getF_HC_TINDAK_LANJUT());
        values.put(DBHelper.F_HC_KENDALA,data.getF_HC_KENDALA());
        values.put(DBHelper.F_TOTAL_REKRUITMEN, data.getF_TOTALREKRUITMEN());
        values.put(DBHelper.F_CATATAN, data.getF_CATATAN());
        values.put(DBHelper.F_FLAG_SAVE_DRAFT, data.getF_SAVE_DRAFT());

        long insertId = database.insert(DBHelper.TABLE_FORM, null,values);
        close();
        return insertId;
    }

    private FormData cursorToForm(Cursor cursor) {
        open();
        FormData data = new FormData();
        data.setF_KODE(cursor.getString(0));
        data.setF_TGL_KUNJUNGAN(cursor.getString(1));
        data.setF_TGL_KESEDIAAN_PENDAFTARAN(cursor.getString(2));
        data.setF_TGL_PERINGATAN_PENDAFTARAN(cursor.getString(3));
        data.setF_TGL_PENDAFTARAN_BU(cursor.getString(4));
        data.setF_TGL_PENYERAHAN_DATA(cursor.getString(5));
        data.setF_BDN_USH(cursor.getString(6));
        data.setF_ALAMAT(cursor.getString(7));
        data.setF_PHONE(cursor.getString(8));
        data.setF_EMAIL(cursor.getString(9));
        data.setF_BIDANG_USH(cursor.getString(10));
        data.setF_JUMLAHKAR(cursor.getString(11));
        data.setF_JUMLAHKEL(cursor.getString(12));
        data.setF_MENGIKUTI_SOSIALISASI_BPJS_KES(cursor.getString(13));
        data.setF_JKN_KIS(cursor.getString(14));
        data.setF_JUMLAHTERDAFTARKAR(cursor.getString(15));
        data.setF_JUMLAHTERDAFTARKEL(cursor.getString(16));
        data.setF_ASURANSIKES(cursor.getString(17));
        data.setF_TAMBAHAN(cursor.getString(18));
        data.setF_KP_NAMA(cursor.getString(19));
        data.setF_KP_JABATAN(cursor.getString(20));
        data.setF_KP_UNIT_KERJA(cursor.getString(21));
        data.setF_KP_PHONE(cursor.getString(22));
        data.setF_HC_BERSEDIA_MENDAFTAR(cursor.getString(23));
        data.setF_HC_ALASAN(cursor.getString(24));
        data.setF_HC_TINDAK_LANJUT(cursor.getString(25));
        data.setF_HC_KENDALA(cursor.getString(26));
        data.setF_TOTALREKRUITMEN(cursor.getString(27));
        data.setF_CATATAN(cursor.getString(28));
        data.setF_SAVE_DRAFT(cursor.getString(29));

        close();
        return data;
    }

    public ArrayList<FormData> getAllformposted() {
        open();
        ArrayList<FormData> listdata = new ArrayList();
        Cursor cursor = this.database.query(DBHelper.TABLE_FORM, this.formdata, DBHelper.F_FLAG_SAVE_DRAFT+"=0", null, null, null, DBHelper.F_KODE +" ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listdata.add(cursorToForm(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return listdata;
    }


    public ArrayList<FormData> getAllformpostedbynama(String nama) {
        open();
        ArrayList<FormData> listdata = new ArrayList();
        Cursor cursor = this.database.query(DBHelper.TABLE_FORM, this.formdata, DBHelper.F_KP_NAMA+" LIKE '%"+nama+"%' AND "+DBHelper.F_FLAG_SAVE_DRAFT+"=0", null, null, null, DBHelper.F_KODE +" ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listdata.add(cursorToForm(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return listdata;
    }

    public ArrayList<FormData> getAllformbykode(String kode) {
        open();
        ArrayList<FormData> listdata = new ArrayList();
        Cursor cursor = this.database.query(DBHelper.TABLE_FORM, this.formdata, DBHelper.F_KODE+"='"+kode+"'", null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listdata.add(cursorToForm(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return listdata;
    }

    public ArrayList<FormData> getAllformdraft() {
        open();
        ArrayList<FormData> listdata = new ArrayList();
        Cursor cursor = this.database.query(DBHelper.TABLE_FORM, this.formdata, DBHelper.F_FLAG_SAVE_DRAFT+"=1", null, null, null, DBHelper.F_KODE +" ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listdata.add(cursorToForm(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return listdata;
    }


    public ArrayList<FormData> getAllformdraftbynama(String nama) {
        open();
        ArrayList<FormData> listdata = new ArrayList();
        Cursor cursor = this.database.query(DBHelper.TABLE_FORM, this.formdata, DBHelper.F_KP_NAMA+" LIKE '%"+nama+"%' AND "+DBHelper.F_FLAG_SAVE_DRAFT+"=1", null, null, null, DBHelper.F_KODE +" ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listdata.add(cursorToForm(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return listdata;
    }


    public long deleteformwherekode(String kode) {
        open();
        long a = this.database.delete(DBHelper.TABLE_FORM, DBHelper.F_KODE+"='"+kode+"'", null);
        close();
        return a;
    }

    public long updateform(FormData data){
        open();
        ContentValues values = new ContentValues();
        values.put(DBHelper.F_TGL_KUNJUNGAN ,data.getF_TGL_KUNJUNGAN());
        values.put(DBHelper.F_TGL_KESEDIAAN_PENDAFTARAN ,data.getF_TGL_KESEDIAAN_PENDAFTARAN());
        values.put(DBHelper.F_TGL_PERINGATAN_PENDAFTARAN ,data.getF_TGL_PERINGATAN_PENDAFTARAN());
        values.put(DBHelper.F_TGL_PENDAFTARAN_BU ,data.getF_TGL_PENDAFTARAN_BU());
        values.put(DBHelper.F_TGL_PENYERAHAN_DATA ,data.getF_TGL_PENYERAHAN_DATA());
        values.put(DBHelper.F_BDN_USH ,data.getF_BDN_USH());
        values.put(DBHelper.F_ALAMAT,data.getF_ALAMAT());
        values.put(DBHelper.F_PHONE,data.getF_PHONE());
        values.put(DBHelper.F_EMAIL,data.getF_EMAIL());
        values.put(DBHelper.F_BIDANG_USH,data.getF_BIDANG_USH());
        values.put(DBHelper.F_JUMLAHKAR,data.getF_JUMLAHKAR());
        values.put(DBHelper.F_JUMLAHKEL,data.getF_JUMLAHKEL());
        values.put(DBHelper.F_MENGIKUTI_SOSIALISASI_BPJS_KES ,data.getF_MENGIKUTI_SOSIALISASI_BPJS_KES());
        values.put(DBHelper.F_JKN_KIS,data.getF_JKN_KIS());
        values.put(DBHelper.F_JUMLAHTERDAFTARKAR ,data.getF_JUMLAHTERDAFTARKAR());
        values.put(DBHelper.F_JUMLAHTERDAFTARKEL ,data.getF_JUMLAHTERDAFTARKEL());
        values.put(DBHelper.F_ASURANSIKES,data.getF_ASURANSIKES());
        values.put(DBHelper.F_TAMBAHAN ,data.getF_TAMBAHAN());
        values.put(DBHelper. F_KP_NAMA ,data.getF_KP_NAMA());
        values.put(DBHelper.F_KP_JABATAN ,data.getF_KP_JABATAN());
        values.put(DBHelper.F_KP_UNIT_KERJA,data.getF_KP_UNIT_KERJA());
        values.put(DBHelper.F_KP_PHONE,data.getF_PHONE());
        values.put(DBHelper.F_HC_BERSEDIA_MENDAFTAR,data.getF_HC_BERSEDIA_MENDAFTAR());
        values.put( DBHelper.F_HC_ALASAN ,data.getF_HC_ALASAN());
        values.put(DBHelper.F_HC_TINDAK_LANJUT,data.getF_HC_TINDAK_LANJUT());
        values.put(DBHelper.F_HC_KENDALA,data.getF_HC_KENDALA());
        values.put(DBHelper.F_TOTAL_REKRUITMEN,data.getF_TOTALREKRUITMEN());
        values.put(DBHelper.F_CATATAN,data.getF_CATATAN());
        values.put(DBHelper.F_FLAG_SAVE_DRAFT,data.getF_SAVE_DRAFT());

        long updateId = database.update(DBHelper.TABLE_FORM, values, DBHelper.F_KODE+"='"+data.getF_KODE()+"'", null);
        close();

        return updateId;
    }
    //=========================================================================================== USer

    public long createFile(FormFile data) {
        open();
        ContentValues values = new ContentValues();
        values.put(DBHelper.FILE_KODE,data.getFile_kode());
        values.put(DBHelper.FILE_IMAGE ,data.getFile_image());
        values.put(DBHelper.FILE_TTD,data.getFile_ttd());
        values.put(DBHelper.FILE_TTD2,data.getFile_ttd2());
        long insertId = database.insert(DBHelper.TABLE_FILE, null,values);
        close();
        return insertId;
    }

    private FormFile cursorToFile(Cursor cursor) {
        open();
        FormFile data = new FormFile();
        data.setFile_kode(cursor.getString(0));
        data.setFile_image(cursor.getString(1));
        data.setFile_ttd(cursor.getString(2));
        data.setFile_ttd2(cursor.getString(3));
        close();
        return data;
    }

    public ArrayList<FormFile> getAllfilebykode(String kode) {
        open();
        ArrayList<FormFile> listdata = new ArrayList();
        Cursor cursor = this.database.query(DBHelper.TABLE_FILE, this.formfile, DBHelper.FILE_KODE+"='"+kode+"'", null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listdata.add(cursorToFile(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return listdata;
    }

    public ArrayList<FormFile> getAllfile() {
        open();
        ArrayList<FormFile> listdata = new ArrayList();
        Cursor cursor = this.database.query(DBHelper.TABLE_FILE, this.formfile, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listdata.add(cursorToFile(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return listdata;
    }


    public long deletefile() {
        open();
        long a = this.database.delete(DBHelper.TABLE_FILE, null, null);
        close();
        return a;
    }

    public long deletefilewherekode(String kode) {
        open();
        long a = this.database.delete(DBHelper.TABLE_FILE, DBHelper.FILE_KODE+"='"+kode+"'", null);
        close();
        return a;
    }

    public long updatefile(FormFile data){
        open();
        ContentValues values = new ContentValues();
        values.put(DBHelper.FILE_IMAGE ,data.getFile_image());
        values.put(DBHelper.FILE_TTD,data.getFile_ttd());
        values.put(DBHelper.FILE_TTD2,data.getFile_ttd2());
        long updateId = database.update(DBHelper.TABLE_FILE, values, DBHelper.FILE_KODE+"='"+data.getFile_kode()+"'", null);
        close();

        return updateId;
    }


}
