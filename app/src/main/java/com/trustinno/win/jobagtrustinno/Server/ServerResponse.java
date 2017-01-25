package com.trustinno.win.jobagtrustinno.Server;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zarni on 1/25/17.
 */

public class ServerResponse  implements Serializable{

@SerializedName("email")
private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("message")
    private String message;
    @SerializedName("error_code")
    private int errorCode;
    private int status=1;
    private String error;

public ServerResponse(String email,String password,String message,int errorCode,int status,String error){

    this.email=email;
    this.password=password;
    this.message=message;
    this.errorCode=errorCode;
    this.status=status;
    this.error=error;



}

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
