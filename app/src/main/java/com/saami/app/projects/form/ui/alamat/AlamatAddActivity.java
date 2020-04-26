package com.saami.app.projects.form.ui.alamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saami.app.projects.form.R;
import com.saami.app.projects.form.SharedPrefManager;
import com.saami.app.projects.form.connection.Client;
import com.saami.app.projects.form.connection.Service;
import com.saami.app.projects.form.model.alamat.delete.AlamatDeleteResponse;
import com.saami.app.projects.form.model.alamat.edit.AlamatEditResponse;
import com.saami.app.projects.form.model.alamat.get.DataItem;
import com.saami.app.projects.form.model.alamat.post.AlamatPostResponse;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlamatAddActivity extends AppCompatActivity {
    String edit = "0";
    String view = "0";
    SharedPrefManager sharedPrefManager;
    SearchableSpinner spnKota;
    EditText namaBu, alamat;
    Button submit;
    DataItem getAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat_add);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        try {
            edit = getIntent().getExtras().getString("edit");
        } catch (Exception e) {
            edit = "0";
        }
        try {
            view = getIntent().getExtras().getString("view");
        } catch (Exception e) {
            view = "0";
        }
        getAlamat = getIntent().getParcelableExtra("data");
        view();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit.equals("1")){
                    editAlamat();
                }else {
                    addAlamat();
                }
            }
        });

    }

    private void view() {

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

        if (edit.equals("1")) {
            int selection = adaptercab.getPosition(getAlamat.getKota());
            spnKota.setSelection(selection);
            alamat.setText(getAlamat.getAlamat());
            namaBu.setText(getAlamat.getNamaBadanUsaha());
        }
        if (view.equals("1")) {
            int selection = adaptercab.getPosition(getAlamat.getKota());
            spnKota.setSelection(selection);
            alamat.setText(getAlamat.getAlamat());
            namaBu.setText(getAlamat.getNamaBadanUsaha());

            spnKota.setEnabled(false);
            alamat.setEnabled(false);
            namaBu.setEnabled(false);
            submit.setVisibility(View.GONE);
        }
    }



    private void addAlamat() {
        String token = sharedPrefManager.getSpToken();
        String kota = String.valueOf(spnKota.getSelectedItem());
        String namaBU = namaBu.getText().toString();
        String alamatS = alamat.getText().toString();
        Service service = Client.getClient().create(Service.class);
        Call<AlamatPostResponse> call = service.addAlamat("Bearer " + token, "Sumatra Utara", kota, "Medan Kota", namaBU, alamatS);
        call.enqueue(new Callback<AlamatPostResponse>() {
            @Override
            public void onResponse(Call<AlamatPostResponse> call, Response<AlamatPostResponse> response) {
                assert response.body() != null;
                finish();
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

    private void editAlamat() {
        String token = sharedPrefManager.getSpToken();
        String kota = String.valueOf(spnKota.getSelectedItem());
        String namaBU = namaBu.getText().toString();
        String alamatS = alamat.getText().toString();
        Service service = Client.getClient().create(Service.class);
        Call<AlamatEditResponse> call = service.editAlamat("Bearer " + token, getAlamat.getId(), "Sumatra", kota, "Medan Kota", namaBU, alamatS);
        call.enqueue(new Callback<AlamatEditResponse>() {
            @Override
            public void onResponse(Call<AlamatEditResponse> call, Response<AlamatEditResponse> response) {
                assert response.body() != null;
                Intent intent = new Intent(AlamatAddActivity.this, AlamatActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<AlamatEditResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
