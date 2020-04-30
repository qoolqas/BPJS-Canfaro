package com.saami.app.projects.form.connection;

import com.saami.app.projects.form.model.alamat.edit.AlamatEditResponse;
import com.saami.app.projects.form.model.alamat.get.AlamatResponse;
import com.saami.app.projects.form.model.alamat.delete.AlamatDeleteResponse;
import com.saami.app.projects.form.model.alamat.post.AlamatPostResponse;
import com.saami.app.projects.form.model.badanusaha.BadanUsahaSearchResponse;
import com.saami.app.projects.form.model.image.ImageResponse;
import com.saami.app.projects.form.model.kunjungan.KunjunganGetResponse;
import com.saami.app.projects.form.model.kunjungan.delete.KunjunganDeleteResponse;
import com.saami.app.projects.form.model.kunjungan.edit.KunjunganEditResponse;
import com.saami.app.projects.form.model.kunjungan.post.KunjunganPostResponse;
import com.saami.app.projects.form.model.login.LoginResponse;
import com.saami.app.projects.form.model.post.Data;
import com.saami.app.projects.form.model.post.PostResponse;
import com.saami.app.projects.form.model.register.RegisterResponse;

import java.util.Map;

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
import retrofit2.http.PUT;
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


    @GET("kunjungan")
    Call<KunjunganGetResponse> getKunjungan(@Header("Authorization") String authorization,
                                            @Query("userId") String uid,
                                            @Query("relationship") int relation);

    @GET("kunjungan/{id}")
    Call<KunjunganGetResponse> getKunjunganId(@Header("Authorization") String authorization,
                                              @Path("id") int id
    );

    @GET("kunjungan")
    Call<BadanUsahaSearchResponse> getKunjunganSearch(@Header("Authorization") String authorization,
                                                      @Query("userId") String uid,
                                                      @Query("q") String search,
                                                      @Query("relationship") int relation);

    @DELETE("kunjungan/{id}")
    Call<KunjunganDeleteResponse> deleteKunjungan(@Header("Authorization") String authorization,
                                                  @Path("id") String id);


    @POST("kunjungan")
    Call<KunjunganPostResponse> saveKunjungan(@Header("Authorization") String authorization,
                                              @Body Data data);

    @PUT("kunjungan/{id}")
    Call<KunjunganEditResponse> saveEditKunjungan(@Header("Authorization") String authorization,
                                                  @Path("id") int id,
                                                  @Body Data data);

    @Multipart
    @POST("image/upload/{type}")
    Call<ImageResponse> uploadImage(@Header("Authorization") String authorization,
                                    @Path("type") String type,
                                    @PartMap Map<String, RequestBody> map);

    @GET("alamat")
    Call<AlamatResponse> getAlamat(@Header("Authorization") String authorization,
                                   @Query("relationship") int relation);

    @GET("alamat")
    Call<AlamatResponse> getAlamatSearch(@Header("Authorization") String authorization,
                                         @Query("relationship") int relation,
                                         @Query("q") String search);

    @FormUrlEncoded
    @POST("alamat")
    Call<AlamatPostResponse> addAlamat(@Header("Authorization") String authorization,
                                       @Field("provinsi") String prov,
                                       @Field("kota") String kota,
                                       @Field("kecamatan") String kecamatan,
                                       @Field("namaBadanUsaha") String namaBU,
                                       @Field("alamat") String alamat);

    @FormUrlEncoded
    @PUT("alamat/{id}")
    Call<AlamatEditResponse> editAlamat(@Header("Authorization") String authorization,
                                        @Path("id") int id,
                                        @Field("provinsi") String prov,
                                        @Field("kota") String kota,
                                        @Field("kecamatan") String kecamatan,
                                        @Field("namaBadanUsaha") String namaBU,
                                        @Field("alamat") String alamat);

    @DELETE("alamat/{id}")
    Call<AlamatDeleteResponse> deleteAlamat(@Header("Authorization") String authorization,
                                            @Path("id") int id);


}
