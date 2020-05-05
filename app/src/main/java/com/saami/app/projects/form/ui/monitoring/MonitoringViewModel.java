package com.saami.app.projects.form.ui.monitoring;

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
import com.saami.app.projects.form.model.monitoring.MonitoringResponse;
import com.saami.app.projects.form.model.monitoring.MonitoringResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonitoringViewModel extends AndroidViewModel {
    private SharedPrefManager sharedPrefManager;
    private MutableLiveData<MonitoringResponse> getAlamat;
    public MonitoringViewModel(@NonNull Application application) {
        super(application);
        sharedPrefManager = new SharedPrefManager(application);
    }
    void loadAlamat() {
        String token = sharedPrefManager.getSpToken();
        Service service = Client.getClient().create(Service.class);
        Call<MonitoringResponse> call = service.getMonitoring("Bearer " + token);
        call.enqueue(new Callback<MonitoringResponse>() {

            @Override
            public void onResponse(@NotNull Call<MonitoringResponse> call, @NotNull Response<MonitoringResponse> response) {
                getAlamat.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<MonitoringResponse> call, @NotNull Throwable t) {
                Log.e("failure", t.toString());

            }
        });
    }

    public LiveData<MonitoringResponse> liveGet() {
        if (getAlamat == null) {
            getAlamat = new MutableLiveData<>();
            loadAlamat();
        }
        return getAlamat;
    }
}
