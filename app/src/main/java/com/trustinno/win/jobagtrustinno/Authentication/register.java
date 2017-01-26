package com.trustinno.win.jobagtrustinno.Authentication;

/**
 * Created by zarni on 1/25/17.
 */

public class register {

    final  String login_name;
    final String password;
    final String email;
    final  int rdoType;

    public register(String login_name, String email, String password, int rdoType){

        this.login_name=login_name;
        this.email=email;
        this.password=password;
        this.rdoType=rdoType;

    }

}
