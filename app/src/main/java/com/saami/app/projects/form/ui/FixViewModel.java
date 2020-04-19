package com.saami.app.projects.form.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.saami.app.projects.form.SharedPrefManager;
import com.saami.app.projects.form.connection.Client;
import com.saami.app.projects.form.connection.Service;
import com.saami.app.projects.form.model.badanusaha.BadanUsahaGetResponse;
import com.saami.app.projects.form.model.kunjungan.KunjunganGetResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FixViewModel extends AndroidViewModel {
    private MutableLiveData<BadanUsahaGetResponse> getBU;
    private MutableLiveData<KunjunganGetResponse> getKunjungan;
    private SharedPrefManager sharedPrefManager;

    public FixViewModel(@NonNull Application application) {
        super(application);
        sharedPrefManager = new SharedPrefManager(application);
    }

    void loadEvent() {
        String token = sharedPrefManager.getSpToken();
        String uid = sharedPrefManager.getSpUID();
        Service service = Client.getClient().create(Service.class);
        Call<KunjunganGetResponse> call = service.getKunjungan("Bearer " + token, uid, 1);
        call.enqueue(new Callback<KunjunganGetResponse>() {

            @Override
            public void onResponse(@NotNull Call<KunjunganGetResponse> call, @NotNull Response<KunjunganGetResponse> response) {
                getKunjungan.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<KunjunganGetResponse> call, @NotNull Throwable t) {
                Log.e("failure", t.toString());

            }
        });
    }
    public void loadBU(String path){
        String token = sharedPrefManager.getSpToken();
        Service service = Client.getClient().create(Service.class);
        Call<BadanUsahaGetResponse> call = service.getBu("Bearer " + token, path);
        call.enqueue(new Callback<BadanUsahaGetResponse>() {

            @Override
            public void onResponse(@NotNull Call<BadanUsahaGetResponse> call, @NotNull Response<BadanUsahaGetResponse> response) {
                getBU.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<BadanUsahaGetResponse> call, @NotNull Throwable t) {
                Log.e("failure", t.toString());

            }
        });
    }

    public LiveData<KunjunganGetResponse> liveGet() {
        if (getKunjungan == null) {
            getKunjungan = new MutableLiveData<>();
            loadEvent();
        }
        return getKunjungan;
    }
}
