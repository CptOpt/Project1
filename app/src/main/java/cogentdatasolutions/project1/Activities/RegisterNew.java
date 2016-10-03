package cogentdatasolutions.project1.Activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cogentdatasolutions.project1.Fragments.Register;
import cogentdatasolutions.project1.R;

public class RegisterNew extends AppCompatActivity {
    private static final String TAG = Register.class.getSimpleName();
    private Button register;
    private JSONObject jsonObject1,jsonObject2 = null;
    private HttpURLConnection connection = null;
    private BufferedReader bufferedReader = null;
    private InputStream inputStream = null;
    URL url = null;
    EditText efname,emname,elname,epasswrd,econfrmpasswrd,eemail;
    String fname,mname,lname,passwrd,cpasswrd,mail;
    String finaljson,err_msg,text,seekeritem,jobdescitem;
    TextInputLayout flayout,mlayout,llayout,passwrdlayout,cpasswrdflayout,emaillayout;
    Button registerbtn;
   Spinner jobseekerspinner,seekerdescspinner;
    String [] JOBSEEKER_DATA= new String[]{"I am","Fresher","Experienced"};
    String [] SEEKERDESC_DATA=new String[]{"--Select--",
            "Student,Seeking Internships",
            "Student,Seeking First Fulltime Job",
            "Unemployed,Seeking Fulltime Employment",
            "Employed,Seeking New Fulltime Opportunity",
            "Employed,Open to New Opportunities"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);
        Toolbar registertool=(Toolbar)findViewById(R.id.regToolbar);
        setSupportActionBar(registertool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        flayout=(TextInputLayout)findViewById(R.id.layout_fname);
        mlayout=(TextInputLayout)findViewById(R.id.layout_mname);
        llayout=(TextInputLayout)findViewById(R.id.layout_lname);
        emaillayout=(TextInputLayout)findViewById(R.id.layout_email);
        passwrdlayout=(TextInputLayout)findViewById(R.id.layout_passwrd);
        cpasswrdflayout=(TextInputLayout)findViewById(R.id.layout_confirmpasswrd);
        registerbtn=(Button)findViewById(R.id.registerbtn);
        eemail=(EditText)findViewById(R.id.input_email);
        efname=(EditText)findViewById(R.id.input_fname);
        elname=(EditText)findViewById(R.id.input_lname);
        emname=(EditText)findViewById(R.id.input_mname);
        epasswrd=(EditText)findViewById(R.id.input_passwrd);
        econfrmpasswrd=(EditText)findViewById(R.id.input_cpasswrd);


        String[] location = new String[]{
                "---Search---",
                "City",
                "State",

        };
        jobseekerspinner=(Spinner)findViewById(R.id.jobseekerspinner);
        seekerdescspinner=(Spinner)findViewById(R.id.seekerdescspinner);

        final List<String> jobseekedata = new ArrayList<>(Arrays.asList(JOBSEEKER_DATA));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spin,jobseekedata){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {

                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };


        spinnerArrayAdapter.setDropDownViewResource(R.layout.spin);
       jobseekerspinner.setAdapter(spinnerArrayAdapter);

        jobseekerspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               seekeritem
                        = (String) parent.getItemAtPosition(position);

                if(position > 0){

                    Toast.makeText
                            (getApplicationContext(), "Selected : " + seekeritem, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        final List<String> jobseekedescdata = new ArrayList<>(Arrays.asList(SEEKERDESC_DATA));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> jobdescArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spin,jobseekedescdata){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {

                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };


       jobdescArrayAdapter.setDropDownViewResource(R.layout.spin);
        seekerdescspinner.setAdapter(jobdescArrayAdapter);

       seekerdescspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jobdescitem = (String) parent.getItemAtPosition(position);

                if(position > 0){

                    Toast.makeText
                            (getApplicationContext(), "Selected : " + jobdescitem, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


   registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=efname.getText().toString();
                mname=emname.getText().toString();
                lname=elname.getText().toString();
                mail=eemail.getText().toString();
                passwrd=epasswrd.getText().toString();
                cpasswrd=econfrmpasswrd.getText().toString();
                if(efname.length()==0)
                {
                    flayout.setError("cannot be empty");
                }else if(emname.length()==0)
                {
                    mlayout.setError("Cannot be empty");
                }else if (elname.length()==0)
                {
                    llayout.setError("Cannot be Empty");
                }else if (epasswrd.length()==0)
                {
                    passwrdlayout.setError("Cannot be Empty");
                }else if (econfrmpasswrd.length()==0)
                {
                    cpasswrdflayout.setError("Cannot be Empty");
                }else if (!passwrd.equals(cpasswrd)){
                    passwrdlayout.setError("Passwords doesn't match");
                } else if (!isValidPassword(passwrd)){
                    passwrdlayout.setError("Password should contain one alphabet, one symbol, one number");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(eemail.getText().toString()).matches()) {
                    emaillayout.setError("Invalid Email Id");
                } else if (passwrd.length() < 8 || passwrd.length() > 15) {
                    passwrdlayout.setError("password should be 8-15 characters");
                    cpasswrdflayout.setError("password should be 8-15 characters");
                } else if (!passwrd.trim().equals(cpasswrd.trim())) {
                    passwrdlayout.setError("password and confirm password must be same");
                } else {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("EMAILID",mail);
                    editor.putString("USERNAME",fname+mname+lname);
                    String name=preferences.getString("USERNAME","");
                    Log.e(TAG, "Preferences string: "+ name);
                    editor.apply();
                    String str=preferences.getString("EMAILID","");
                    Log.e(TAG, "Preferences string: "+str );

                    new JSONTask().execute("http://10.80.15.119:8080/OptnCpt/rest/service/registration");

                }


            }
        });


    }
    public boolean isValidPassword(final String password){
        Pattern pattern;
        Matcher matcher;
//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

    private class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            mail = eemail.getText().toString();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                jsonObject1 = new JSONObject();
                jsonObject1.put("firstName", "" + fname);
                jsonObject1.put("lastName",""+lname);
                jsonObject1.put("mail", "" + mail);
                jsonObject1.put("password", "" + passwrd);
                jsonObject1.put("conPassword", "" + cpasswrd);
                jsonObject1.put("middleName", "" + mname);
                jsonObject1.put("jobType","" + seekeritem );
                jsonObject1.put("jobDesc","" + jobdescitem);

                String jsonObj = jsonObject1.toString();
                Log.e(TAG, "doInBackground: " + jsonObj);
                //Header
                connection.setRequestProperty("registerObject", "" + jsonObj);
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

            } catch (IOException | JSONException e) {
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
                   boolean str = jobj.getBoolean("status");
                    if (str == true) {
                        String msg=jobj.getString("msg");
                        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(RegisterNew.this,Otp.class);
                        startActivity(i);

                    } else
                    err_msg=jobj.getString("err_msg");
                        Toast.makeText(getApplicationContext(), err_msg, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            efname.setText("");
            elname.setText("");
            emname.setText("");
            eemail.setText("");
            epasswrd.setText("");
            econfrmpasswrd.setText("");

            super.onPostExecute(result);
        }
    }

}
