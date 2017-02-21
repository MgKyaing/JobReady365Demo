package com.trustinno.win.jobagtrustinno.znk.datastore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zarni on 2/6/17
 */

public class User implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("login_name")
    private String login_name;


    public User(String id, String login_name) {
        this.id = id;
        this.login_name = login_name;
    }
    public String getId(){
        return id;
    }
}
