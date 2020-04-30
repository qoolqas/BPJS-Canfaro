package com.saami.app.projects.form.ui.alamat;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.saami.app.projects.form.SharedPrefManager;
import com.saami.app.projects.form.connection.Client;
import com.saami.app.projects.form.connection.Service;
import com.saami.app.projects.form.model.alamat.get.AlamatResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlamatViewModel extends AndroidViewModel {
    SharedPrefManager sharedPrefManager;
    MutableLiveData<AlamatResponse> getAlamat;
    public AlamatViewModel(@NonNull Application application) {
        super(application);
        sharedPrefManager = new SharedPrefManager(application);
    }
    void loadAlamat() {
        String token = sharedPrefManager.getSpToken();
        String uid = sharedPrefManager.getSpUID();
        Service service = Client.getClient().create(Service.class);
        Call<AlamatResponse> call = service.getAlamat("Bearer " + token, 1);
        call.enqueue(new Callback<AlamatResponse>() {

            @Override
            public void onResponse(@NotNull Call<AlamatResponse> call, @NotNull Response<AlamatResponse> response) {
                getAlamat.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<AlamatResponse> call, @NotNull Throwable t) {
                Log.e("failure", t.toString());

            }
        });
    }

    public LiveData<AlamatResponse> liveGet() {
        if (getAlamat == null) {
            getAlamat = new MutableLiveData<>();
            loadAlamat();
        }
        return getAlamat;
    }
    void loadAlamatSearch(String search) {
        String token = sharedPrefManager.getSpToken();
        Service service = Client.getClient().create(Service.class);
        Call<AlamatResponse> call = service.getAlamatSearch("Bearer " + token, 1, search);
        call.enqueue(new Callback<AlamatResponse>() {

            @Override
            public void onResponse(@NotNull Call<AlamatResponse> call, @NotNull Response<AlamatResponse> response) {
                getAlamat.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<AlamatResponse> call, @NotNull Throwable t) {
                Log.e("failure", t.toString());

            }
        });
    }

    public LiveData<AlamatResponse> liveSearch(String search2) {
        if (getAlamat == null) {
            getAlamat = new MutableLiveData<>();
            loadAlamatSearch(search2);
        }
        return getAlamat;
    }
}
