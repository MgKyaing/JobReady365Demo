package com.trustinno.win.jobagtrustinno.Server;

import android.util.Log;

import com.squareup.otto.Produce;
import com.trustinno.win.jobagtrustinno.Authentication.LoginActivity;
import com.trustinno.win.jobagtrustinno.Authentication.login;
import com.trustinno.win.jobagtrustinno.Authentication.register;
import com.trustinno.win.jobagtrustinno.Interface.Interface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zarni on 1/25/17.
 */

public class ConnectionHub {
    private static final String TAG = "ConnectionHub";
    private static final String SERVER_URL = "http://goldenictsolutions.com/";
    private static final String token= LoginActivity.token;


    public void loginPost(String login_name, String password) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL).build();


        Interface service = retrofit.create(Interface.class);

        Call<ServerResponse> call = service.post(new login(login_name, password));
        call.enqueue(new Callback<ServerResponse>() {

            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                Log.e(TAG, "Success" + response.code());
                Log.e(TAG, "Success" + response.body());
                Log.e(TAG, "Success" + response.message());
                BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.e(TAG, "Failure " + t.getMessage());
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));

            }
        });

    }

    public void registerPost(String login_name, String email,String telephone_no, String password, String user_type,String category_id) {

        final HttpLoggingInterceptor register = new HttpLoggingInterceptor();
        register.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(register);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL).build();

        Interface service = retrofit.create(Interface.class);

        Call<ServerResponse> call = service.register(new register(login_name, email,telephone_no, password, user_type,category_id));
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                // response.isSuccessful() is true if the response code is 2xx
                Log.e(TAG, "Success" + response.code());
                Log.e(TAG, "Success" + response.body());
                BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG, "Success" + response.message());
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                Log.e(TAG, "Failure " + t.getMessage());
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }
//TODO to fixed login server response app crash error
//
//    public void getemployerjob(String name, String company_name, String moblie_no, String contact_no, String user_email, String normal_email, String address, String township, String postal_code, int city, int country, String website, String description) {
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(logging);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(httpClient.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(SERVER_URL)
//                .build();
//
//        Interface service = retrofit.create(Interface.class);
//        Call<ServerResponse> call = service.employerjob_upload(new employerjob_upload(name,company_name,moblie_no,contact_no,user_email,normal_email,address,township,postal_code,city,country,website,description));
//
//        call.enqueue(new Callback<ServerResponse>() {
//            @Override
//            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
//                // response.isSuccessful() is true if the response code is 2xx
//                Log.e(TAG, "Success" + response.code());
//                Log.e(TAG, "Success" + response.body());
//                BusProvider.getInstance().post(new ServerEvent(response.body()));
//                Log.e(TAG, "Success" + response.message());
//                Log.e(TAG, "Success");
//            }
//            @Override
//            public void onFailure(Call<ServerResponse> call, Throwable t) {
//                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
//            }
//        });
//    }

    @Produce
    public ServerEvent produceServerEvent(ServerResponse serverResponse) {
        return new ServerEvent(serverResponse);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }
}
