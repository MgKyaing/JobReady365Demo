package com.trustinno.win.jobagtrustinno.Server;

import android.util.Log;

import com.squareup.otto.Produce;
import com.trustinno.win.jobagtrustinno.Authentication.login;
import com.trustinno.win.jobagtrustinno.Interface.Interface;
import com.trustinno.win.jobagtrustinno.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import okhttp3.OkHttpClient;
import  retrofit2.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zarni on 1/25/17.
 */

public class Connection {
    private  static final String TAG="Connection";
    private static final String SERVER_URL="http://myanmar-online.com/";


    public void loginPost  (String username,String password){

        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient=new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit=new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL).build();

        Interface service =retrofit.create(Interface.class);

        Call<ServerResponse> call=service.post(new login(username,password));
        call.enqueue(new Callback<ServerResponse>()
        {

            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                Log.e(TAG, "Success"+response.code());
                Log.e(TAG, "Success"+response.body());
                Log.e(TAG, "Success"+response.message());
                BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {


                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));

            }
        });









    }


    @Produce
    public ServerEvent produceServerEvent(ServerResponse serverResponse) {
        return new ServerEvent(serverResponse);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }
}
