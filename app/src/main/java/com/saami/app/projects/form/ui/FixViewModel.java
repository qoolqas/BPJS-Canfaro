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
import com.saami.app.projects.form.model.badanusaha.BadanUsahaSearchResponse;
import com.saami.app.projects.form.model.kunjungan.KunjunganGetResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FixViewModel extends AndroidViewModel {
    private MutableLiveData<KunjunganGetResponse> getKunjungan;
    private MutableLiveData<BadanUsahaSearchResponse> getBu;
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

    public LiveData<KunjunganGetResponse> liveGet() {
        if (getKunjungan == null) {
            getKunjungan = new MutableLiveData<>();
            loadEvent();
        }
        return getKunjungan;
    }
    void loadSearch(String query) {
        String token = sharedPrefManager.getSpToken();
        String uid = sharedPrefManager.getSpUID();
        Service service = Client.getClient().create(Service.class);
        Call<BadanUsahaSearchResponse> call = service.getKunjunganSearch("Bearer " + token, uid, query, 1);
        call.enqueue(new Callback<BadanUsahaSearchResponse>() {

            @Override
            public void onResponse(@NotNull Call<BadanUsahaSearchResponse> call, @NotNull Response<BadanUsahaSearchResponse> response) {
                getBu.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<BadanUsahaSearchResponse> call, @NotNull Throwable t) {
                Log.e("failure", t.toString());

            }
        });
    }

    public LiveData<BadanUsahaSearchResponse> liveSearch(String query) {
        if (getBu == null) {
            getBu = new MutableLiveData<>();
            loadSearch(query);
        }
        return getBu;
    }
}
