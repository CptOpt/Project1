package cogentdatasolutions.project1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cogentdatasolutions.project1.R;

public class Registration_SignIn extends Activity {
    EditText ejob,eskills,elocation;
    TextInputLayout joblayout,skillslayout,locationlayout;
    Button registerbtn,signinbtn,search;
    private JSONObject jsonObject1 = null;
    private HttpURLConnection connection = null;
    private BufferedReader bufferedReader = null;
    private InputStream inputStream = null;
    String finaljson;
    URL url = null;
    String jsonObj;
    private static final String TAG = Registration_SignIn.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__sign_in);
        ejob=(EditText)findViewById(R.id.input_jtitle);
        eskills=(EditText)findViewById(R.id.input_skills);
        elocation=(EditText)findViewById(R.id.input_location);
        joblayout=(TextInputLayout)findViewById(R.id.layout_jobtitle);
        skillslayout=(TextInputLayout)findViewById(R.id.layout_Skills);
       locationlayout=(TextInputLayout)findViewById(R.id.layout_location);
        registerbtn=(Button)findViewById(R.id.registerbtn);
        signinbtn=(Button)findViewById(R.id.signinbtn);
        search=(Button)findViewById(R.id.searchjobs);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(ejob.length()==0)
//                {
//                   joblayout.setError("Enter JobTitle");
//                }else if (eskills.length()==0)
//                {
//                    skillslayout.setError("Enter Skills");
//                }else if (elocation.length()==0)
//                {
//                    locationlayout.setError("Enter Location");
//                }else
//                {
           new JSONTask().execute("http://10.80.15.119:8080/OptnCpt/rest/service/jobseekerJobSearch");
              //  new JSONTask().execute("http://jsonparsing.parseapp.com/jsonData/moviesData.txt");
              //         }
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegisterNew.class);
                startActivity(i);
            }
        });
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),Signinnew .class);
                startActivity(i1);

            }
        });
    }
    private class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                jsonObject1 = new JSONObject();
                jsonObject1.put("jobTitle", "" + "");
                jsonObject1.put("jobSkills",""+"");
                jsonObject1.put("jobLocations", "" + "");

                jsonObj = jsonObject1.toString();
                Log.e(TAG, "doInBackground: " + jsonObj);
                //Header
                connection.setRequestProperty("JobSeekerSearchDetails", ""+jsonObj);
                connection.connect();
                inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder buffer = new StringBuilder();

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }

                finaljson = buffer.toString();
                Log.e(TAG, "JSON Object" + finaljson);

                return finaljson;

            } catch (IOException  e) {
                e.printStackTrace();

            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (finaljson != null) {
                try {
                    JSONObject jobj = new JSONObject(finaljson);
                    Log.e(TAG, "Response Json: " + jobj);
                    Intent i=new Intent(Registration_SignIn.this,JobsDisplay.class);
                    i.putExtra("JSON_DATA",finaljson);
                    startActivity(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(result);
        }
    }
}
