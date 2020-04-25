package com.saami.app.projects.form.ui.alamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saami.app.projects.form.HomeActivity;
import com.saami.app.projects.form.InsertDataBPJS;
import com.saami.app.projects.form.ListView_BPJS;
import com.saami.app.projects.form.R;
import com.saami.app.projects.form.SharedPrefManager;
import com.saami.app.projects.form.connection.Client;
import com.saami.app.projects.form.connection.Service;
import com.saami.app.projects.form.model.alamat.AlamatResponse;
import com.saami.app.projects.form.model.alamat.post.AlamatPostResponse;
import com.saami.app.projects.form.model.post.PostResponse;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlamatAddActivity extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    SearchableSpinner spnKota;
    EditText namaBu, alamat;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat_add);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        view();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAlamat();
            }
        });

    }

    private void view(){

        ArrayList<String> kota = new ArrayList<>();
        kota.add("SIBOLGA");
        kota.add("TAPANULI TENGAH");
        kota.add("TAPANULI UTARA");
        kota.add("HUMBANG HASUNDUTAN");

        namaBu = findViewById(R.id.edtNamaBu);
        alamat = findViewById(R.id.edtAlamat);
        spnKota = findViewById(R.id.spnKota);
        submit = findViewById(R.id.buttonSubmit);
        ArrayAdapter adaptercab = new ArrayAdapter(AlamatAddActivity.this, android.R.layout.simple_spinner_item, kota);
        adaptercab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnKota.setAdapter(adaptercab);
        spnKota.setTitle("");
    }
    private void addAlamat(){
        String token = sharedPrefManager.getSpToken();
        String kota = String.valueOf(spnKota.getSelectedItem());
        String namaBU = namaBu.getText().toString();
        String alamatS = alamat.getText().toString();
        Service service = Client.getClient().create(Service.class);
        Call<AlamatPostResponse> call = service.addAlamat("Bearer " + token,"Sumatra Utara", kota,"Medan Kota",namaBU, alamatS );
        call.enqueue(new Callback<AlamatPostResponse>() {
            @Override
            public void onResponse(Call<AlamatPostResponse> call, Response<AlamatPostResponse> response) {
                assert response.body() != null;
                    Intent intent = new Intent(AlamatAddActivity.this, AlamatActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
            }

            @Override
            public void onFailure(Call<AlamatPostResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void editAlamat(){
        String token = sharedPrefManager.getSpToken();
        String kota = String.valueOf(spnKota.getSelectedItem());
        String namaBU = namaBu.getText().toString();
        String alamatS = alamat.getText().toString();
        Service service = Client.getClient().create(Service.class);
        Call<AlamatPostResponse> call = service.editAlamat("Bearer " + token,1,"Sumatra", kota,"Medan Kota",namaBU, alamatS );
        call.enqueue(new Callback<AlamatPostResponse>() {
            @Override
            public void onResponse(Call<AlamatPostResponse> call, Response<AlamatPostResponse> response) {
                assert response.body() != null;
                Intent intent = new Intent(AlamatAddActivity.this, AlamatActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<AlamatPostResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
