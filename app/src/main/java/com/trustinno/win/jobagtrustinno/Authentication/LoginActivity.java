package com.trustinno.win.jobagtrustinno.Authentication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.trustinno.win.jobagtrustinno.Employer.Employer;
import com.trustinno.win.jobagtrustinno.R;
import com.trustinno.win.jobagtrustinno.Server.BusProvider;
import com.trustinno.win.jobagtrustinno.Server.ConnectionHub;
import com.trustinno.win.jobagtrustinno.Server.ServerEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {


    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button email_sign_in_button;
    private Button employer_sign_in_button;
    private TextView linkregister;
    public ConnectionHub communicator;
    private String login_name, passwords,result,id,email;
    private TextView extraInformation;
    public static String token;
    public int rdoType,rdotype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        employer_sign_in_button = (Button) findViewById(R.id.sign_in_employer_button);
        employer_sign_in_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Employer.class);
                startActivity(intent);
            }
        });



        linkregister = (TextView) findViewById(R.id.linkregister);
        linkregister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.login_email);


        mPasswordView = (EditText) findViewById(R.id.login_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        mPasswordView = (EditText) findViewById(R.id.login_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        extraInformation = (TextView) findViewById(R.id.information);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    private void attemptLogin() {


        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
        //else if (!isEmailValid(email)) {
           // mEmailView.setError(getString(R.string.error_invalid_email));
           // focusView = mEmailView;
          //  cancel = true;
     //   }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            communicator = new ConnectionHub();
            mEmailView = (AutoCompleteTextView) findViewById(R.id.login_email);
            mPasswordView = (EditText) findViewById(R.id.login_password);

            email_sign_in_button = (Button) findViewById(R.id.email_sign_in_button);
            email_sign_in_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login_name = mEmailView.getText().toString();
                    passwords = mPasswordView.getText().toString();
                    usePost(login_name, passwords);
                }
            });
        }
    }

    private void usePost(String login_name, String password) {
        communicator.loginPost(login_name, password);
    }


    @Subscribe
    public void onServerEvent(ServerEvent serverEvent) {


        if (!serverEvent.getServerResponse().equals(null)) {

           Toast.makeText(getApplicationContext(), "Success ServerEvent Respond" + serverEvent.getServerResponse().gettelephone(), Toast.LENGTH_LONG).show();
          //  extraInformation.setText("" + serverEvent.getServerResponse().getToken()+serverEvent.getServerResponse());
     //     token = serverEvent.getServerResponse().getToken();

       //    result=serverEvent.getServerResponse().getLogin_name();
         //   id=serverEvent.getServerResponse().getId();
           // email=serverEvent.getServerResponse().getemail();
            //rdoType=serverEvent.getServerResponse().getrdoType();
           //Id=serverEvent.getServerResponse().getId();
          //  if (rdoType == 1)
            //{
             //   Intent intent = new Intent(this, Employer.class);
               // intent.putExtra("token", token);
                //intent.putExtra("email",email);
                //intent.putExtra("id",id);
               // startActivity(intent);

            //}
           // else if (rdoType == 2)
            //{
              //  Intent intent = new Intent(this, Employer_profile.class);
                //intent.putExtra("token", token);
                //startActivity(intent);
            //}
           // else
           // {
             //   return;

            //}

            // Intent intent = new Intent(MainLoginActivity.this, JFirstMenuPage.class);
            //    startActivity(intent)
        }


        // if (serverEvent.getServerResponse().getToken() != null) {
        // information.setText("Username: " + serverEvent.getServerResponse().getToken() + " || Password: " + serverEvent.getServerResponse().getPassword());
        //     }

    }


    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }
    @Override
    public void onPause(){
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }
   // private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
       // return email.contains("@");
    //}

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */

    }

