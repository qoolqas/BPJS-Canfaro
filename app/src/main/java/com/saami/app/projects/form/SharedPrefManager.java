package com.saami.app.projects.form;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SP_NAME_APP = "spNameApp";


    public static final String SP_SUDAH_LOGIN = "spSudahLogin";
    public static final String SP_TOKEN = "token";
    public static final String SP_UID = "uid";


    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    @SuppressLint("CommitPrefEdits")
    public SharedPrefManager(Context context) {
        sp = context.getSharedPreferences(SP_NAME_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }


    public void saveToken(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }
    public void saveUID(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public Boolean getSPSudahLogin() {
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
    public String getSpToken() {
        return sp.getString(SP_TOKEN, "token");
    }
    public String getSpUID() {
        return sp.getString(SP_UID, "uid");
    }
}
