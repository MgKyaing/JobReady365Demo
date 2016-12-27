package com.trustinno.win.jobagtrustinno;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Win on 12/23/2016.
 */

public class JobsView extends ListActivity {
    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating Json Parser object
    JSonparser jParser = new JSonparser();

    ArrayList<HashMap<String, String>> jobsList;

    //url to get all  Jobs list
    private static String url_all_jobs = " http://api.androidhive.info/android_connect/get all jobs.php";

    //JSon Node names

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_JOBS = "jobs";
    private static final String TAG_JOBID = "jobs_id";
    private static final String TAG_NAME = "jobs_name";

    //products JSonArray
    JSONArray jobs = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //Hashmap for ListView
        jobsList = new ArrayList<HashMap<String, String>>();

        // loading product in background Thread
        new LoadAllJobs().execute();

        //Get listview
        ListView listView = getListView();

        //on selecting single produc
        //launching Edit product Screen
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //getting values from selected ListItem
                String job_id = ((TextView) view.findViewById(R.id.job_id_tv)).getText().toString();

                //starting new Intent
                Intent intent = new Intent(getApplication(), JobsView.class);

                //Starting new Activitity and expection some response back
                startActivityForResult(intent, 100);
            }
        });
    }

    //Response from View Jobs Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // if result code 100

        if (resultCode == 100) {
            // if result code 100 is received
            //means user edited/deleted product
            //reload this screen again

            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    /*
     Background Async Task to load all Jobs by making HTTP Reqest
 */
    class LoadAllJobs extends AsyncTask<String, String, String> {
        /*(
        Before starting background thread show Progess Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(JobsView.this);
            pDialog.setMessage("Loading Jobs. Please Wait ..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting all jobs from url
         */

        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting Json String from URl
            JSONObject json = jParser.makeHttpRequest(url_all_jobs, "GET", params);

            //Check your log cat for JSon response
            Log.d("All Jobs: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // Jobs found
                    jobs = json.getJSONArray(TAG_JOBS);
                    // getting Arrray of Jobs
                    for (int i = 0; i < jobs.length(); i++) {
                        JSONObject c = jobs.getJSONObject(i);

                        //Storing each json item in variable
                        String id = c.getString(TAG_JOBID);
                        String name = c.getString(TAG_NAME);

                        //creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        //adding each child node to HashMap key => value
                        map.put(TAG_JOBID, id);
                        map.put(TAG_NAME, name);

                        // adding HashList to Arrraylist
                        jobsList.add(map);

                    }
                }
                /**
               else {
                    //no jobs found
                    // launch Add New product Activity
                    Intent i = new Intent(getApplicationContext(), N ? ???);
                    //Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }

                **/
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            JobsView.this, jobsList,
                            R.layout.jobs_list_view, new String[] { TAG_JOBID,
                            TAG_NAME},
                            new int[] { R.id.job_id_tv, R.id.job_name_tv });
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }
    }
}
