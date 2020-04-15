package com.saami.app.projects.form.connection;


import com.saami.app.projects.form.model.login.LoginResponse;
import com.saami.app.projects.form.model.register.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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

}
