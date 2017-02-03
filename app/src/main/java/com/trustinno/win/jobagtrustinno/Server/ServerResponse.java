package com.trustinno.win.jobagtrustinno.Server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zarni on 1/30/17.
 */
public class ServerResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("id")
    private String id;

    @SerializedName("login_name")
    private String login_name;


    @SerializedName("email")
    private String email;

    @SerializedName("telephone_no")
    private String  telephone_no;

    @SerializedName("is_admin")
    private String is_admin;

    @SerializedName("is_active")
    private String  is_active;

    @SerializedName("user_type")
    private int  user_type;

    @SerializedName("remember_token")
    private  String remember_token;

    @SerializedName("created_at")
    private  int created_at;

    @SerializedName("updated_at")
    private int updated_at;

    @SerializedName("api_token")
    private String api_token;

    @SerializedName("error")
    private Boolean error;

    @SerializedName("result")
    private String result;

    @SerializedName("status_code")
    private int statusCode;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


public String gettelephone(){
    return telephone_no;
}
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }



//Boolean error, String result, int statusCode, String token,


//    public ServerResponse(String token) {
  //      this.token = token;
   // }

    public ServerResponse( String id, String login_name,
                          String email,String telephone_no,String  is_admin,String is_active,int user_type,String remember_token){
       // this.statusCode = statusCode;
        //this.error = error;
        //this.result = result;
        //this.token=token;
        this.id=id;
        this.login_name=login_name;
        this.email = email;
        this.telephone_no=telephone_no;
        this.is_admin=is_admin;
        this.is_active=is_active;
        this.user_type=user_type;
        this.remember_token=remember_token;
    }



    public String getToken() {
        return token;
    }

    public int getrdoType(){return user_type;}

    public void setToken(String token) {
        this.token = token;
    }

    public void setRdoType(int rdoType){
        this.user_type = rdoType;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getId() {

        return id;
    }

    public String getLogin_name(){
        return login_name;
    }

    public String getemail() {
    return email;}
}
