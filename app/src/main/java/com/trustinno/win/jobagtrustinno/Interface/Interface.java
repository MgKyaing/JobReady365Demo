package com.trustinno.win.jobagtrustinno.Interface;

import com.trustinno.win.jobagtrustinno.Authentication.login;
import com.trustinno.win.jobagtrustinno.Server.ServerResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;



/**
 * Created by Dori on 12/28/2016.
 */

public interface Interface {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            //  "Authorization: Basic ZWlwaHl1cGh5dWhsYWluZ0BnbWFpbC5jb206MTIzNDU2Nzg="
    })
    //@FormUrlEncoded
    @POST("/api/login")
        //Call<ServerResponse> post(@Body @Root("email") String email,@Root("password") String password);
    Call<ServerResponse> post( @Body login body);
    //Call<ServerResponse> post(@Body login login);
   /* Call<ServerResponse> post(
            //@Field("method") String method,
            @Field("email") String username,
            @Field("password") String password
    );*/


    @GET("/api/login")
    Call<ServerResponse> get(
            // @Query("method") String method,
            @Query("email") String username,
            @Query("password") String password
    );

}