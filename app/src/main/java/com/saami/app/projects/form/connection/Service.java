package com.saami.app.projects.form.connection;


import com.saami.app.projects.form.model.badanusaha.BadanUsahaGetResponse;
import com.saami.app.projects.form.model.login.LoginResponse;
import com.saami.app.projects.form.model.register.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Service {

    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResponse> loginRequest(@Field("email") String email,
                                     @Field("password") String password
    );
    @FormUrlEncoded
    @POST("auth/register")
    Call<RegisterResponse> registerRequest(
            @Field("NPP") String npp,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("gender") String gender,
            @Field("birth") String birth,
            @Field("email") String email,
            @Field("password") String password
    );
    @GET("badan-usaha")
    Call<BadanUsahaGetResponse> getLaptop(@Header("Authorization") String authorization,
                                          @Query("q") String uid);

}
