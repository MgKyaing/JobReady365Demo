package com.trustinno.win.jobagtrustinno.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.trustinno.win.jobagtrustinno.R;
import com.trustinno.win.jobagtrustinno.Server.ConnectionHub;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity {


    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText Login_name;
    private View mProgressView;
    private View mLoginFormView;
    private int rdoType;
    private String login_name, email, password;
    private Button register_button;
    ConnectionHub communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        communicator = new ConnectionHub();
        rdoType = 1;
        mPasswordView = (EditText) findViewById(R.id.password);

        Login_name = (EditText) findViewById(R.id.name);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_register);
        mPasswordView = (EditText) findViewById(R.id.password);
        register_button = (Button) findViewById(R.id.register_button);
        register_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                login_name = Login_name.getText().toString();
                email = mEmailView.getText().toString();
                password = mPasswordView.getText().toString();
                useRegister(login_name, email, password, rdoType);

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void useRegister(String login_name, String email, String password, int rdoType) {
        communicator.registerPost(login_name, email, password, rdoType);
    }


}
