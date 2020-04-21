package com.saami.app.projects.form.connection;


import com.saami.app.projects.form.model.badanusaha.BadanUsahaGetResponse;
import com.saami.app.projects.form.model.image.ImageResponse;
import com.saami.app.projects.form.model.kunjungan.KunjunganGetResponse;
import com.saami.app.projects.form.model.login.LoginResponse;
import com.saami.app.projects.form.model.post.Data;
import com.saami.app.projects.form.model.post.PostResponse;
import com.saami.app.projects.form.model.register.RegisterResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
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
    Call<BadanUsahaGetResponse> getBu(@Header("Authorization") String authorization,
                                      @Query("q") String uid);

    @GET("badan-usaha/{id}")
    Call<BadanUsahaGetResponse> getBuId(@Header("Authorization") String authorization,
                                        @Path("id") String id);

    @GET("kunjungan")
    Call<KunjunganGetResponse> getKunjungan(@Header("Authorization") String authorization,
                                            @Query("userId") String uid,
                                            @Query("relationship") int relation);

    @GET("kunjungan/{id}")
    Call<KunjunganGetResponse> getKunjunganId(@Header("Authorization") String authorization,
                                              @Path("id") int id);

    @GET("kunjungan")
    Call<KunjunganGetResponse> getKunjunganSearch(@Header("Authorization") String authorization,
                                                  @Query("userId") String uid,
                                                  @Query("field") String search,
                                                  @Query("relationship") int relation);

    @DELETE("kunjungan/{id}")
    Call<KunjunganGetResponse> deleteKunjungan(@Header("Authorization") String authorization,
                                               @Path("id") String id);


    @POST("kunjungan")
    Call<PostResponse> saveKunjungan(@Header("Authorization") String authorization,
                                     @Body Data data);

    @POST("kunjungan/{id}")
    Call<PostResponse> saveEditKunjungan(@Header("Authorization") String authorization,
                                         @Path("id") int id,
                                         @Body Data data);

    @Multipart
    @POST("image/upload/{type}")
    Call<ImageResponse> uploadImage(@Header("Authorization") String authorization,
                                    @Path("type") String type,
                                    @PartMap Map<String, RequestBody> map);


}
