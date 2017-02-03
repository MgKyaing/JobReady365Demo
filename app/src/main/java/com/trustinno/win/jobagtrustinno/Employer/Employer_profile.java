package com.trustinno.win.jobagtrustinno.Employer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.trustinno.win.jobagtrustinno.R;
import com.trustinno.win.jobagtrustinno.Server.ConnectionHub;

public class Employer_profile extends AppCompatActivity {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText Login_name;
    private View mProgressView;
    private View mLoginFormView;
    private int rdoType;
    private Button register_button;
    private Button emp_uploadjob;

    private    String name,company_name,moblie_no,contact_no,user_email,email,address,township,postal_code,website,description;
    private    EditText Name,Company_Name,Mobile_No,Contact_No,User_email,Email,Address,Township,Postal_Code,City,Country,Website,Description;
    private int city,country;
    ConnectionHub communicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_profile);
        communicator=new ConnectionHub();
        Name=(EditText)findViewById(R.id.name);
        Company_Name=(EditText)findViewById(R.id.employer_cpname);
        Mobile_No=(EditText)findViewById(R.id.moblie_no);
        Contact_No=(EditText)findViewById(R.id.contact_no);
        User_email=(EditText)findViewById(R.id.employer_email);
        Email=(EditText)findViewById(R.id.email);
        Address=(EditText)findViewById(R.id.address);
        Township=(EditText)findViewById(R.id.township);
        Postal_Code=(EditText)findViewById(R.id.postal_code);
        Website=(EditText)findViewById(R.id.website);
        Description=(EditText)findViewById(R.id.employer_description);
        city=1;
        country=2;

        Button emp_uploadjob=(Button)findViewById(R.id.employer_jobupload_button);
        emp_uploadjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=Name.getText().toString();
                company_name=Company_Name.getText().toString();
                moblie_no=Mobile_No.getText().toString();
                contact_no=Contact_No.getText().toString();
                user_email=User_email.getText().toString();
                email=Email.getText().toString();
                address=Address.getText().toString();
                township=Township.getText().toString();
                postal_code= Postal_Code.getText().toString();
                website=Website.getText().toString();
                description=Description.getText().toString();
                jobupload(name,company_name,moblie_no,contact_no,user_email,email,address,township,postal_code,city,country,website,description);
            }
        });


    }

    private void jobupload(String name,String company_name,String mobile_no,String contact_no,String user_email,String email,String address,
                           String township,String postal_code,int city,int country,String website,String description ){
        communicator.getemployerjob(name,company_name,mobile_no,contact_no,user_email,email,address,township,postal_code,city,country,website,description);
    }

}
