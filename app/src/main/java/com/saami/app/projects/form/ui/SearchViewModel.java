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
import com.saami.app.projects.form.model.kunjungan.KunjunganGetResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends AndroidViewModel {
    private MutableLiveData<KunjunganGetResponse> getBU;
    private SharedPrefManager sharedPrefManager;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        sharedPrefManager = new SharedPrefManager(application);
    }

    void loadSearch(String query) {
        String token = sharedPrefManager.getSpToken();
        String uid = sharedPrefManager.getSpUID();
        Service service = Client.getClient().create(Service.class);
        Call<KunjunganGetResponse> call = service.getKunjunganSearch("Bearer " + token, uid, query, 1);
        call.enqueue(new Callback<KunjunganGetResponse>() {

            @Override
            public void onResponse(@NotNull Call<KunjunganGetResponse> call, @NotNull Response<KunjunganGetResponse> response) {
                getBU.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<KunjunganGetResponse> call, @NotNull Throwable t) {
                Log.e("failure", t.toString());

            }
        });
    }

    public LiveData<KunjunganGetResponse> liveGet(String query) {
        if (getBU == null) {
            getBU = new MutableLiveData<>();
            loadSearch(query);
        }
        return getBU;
    }
}
