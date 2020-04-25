package com.saami.app.projects.form.ui.alamat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.saami.app.projects.form.HomeActivity;
import com.saami.app.projects.form.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

public class AlamatAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat_add);

        view();
    }

    private void view(){

        ArrayList<String> kota = new ArrayList<String>();
        kota.add("SIBOLGA");
        kota.add("TAPANULI TENGAH");
        kota.add("TAPANULI UTARA");
        kota.add("HUMBANG HASUNDUTAN");

        SearchableSpinner spnKota = findViewById(R.id.spnKota);
        ArrayAdapter adaptercab = new ArrayAdapter(AlamatAddActivity.this, android.R.layout.simple_spinner_item, kota);
        adaptercab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnKota.setAdapter(adaptercab);
        spnKota.setTitle("");
    }
}
