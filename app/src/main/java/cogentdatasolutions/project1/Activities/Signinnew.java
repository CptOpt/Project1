package cogentdatasolutions.project1.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cogentdatasolutions.project1.Fragments.Login;
import cogentdatasolutions.project1.R;

public class Signinnew extends AppCompatActivity {
TextView passwrd;

    EditText etlogin,etpasswrd;
    TextInputLayout loginlayout,passwrdlayout;
    Button signinbtn;
    final Context context = this;
    private static final String TAG = Login.class.getSimpleName();
    String empid,errmsg,mailtxt,loginmail,loginpaaswrd,errmsg1;
    String finalJson;
    JSONObject jsonObject1,jsonObject2 = null;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinnew);
        etlogin=(EditText)findViewById(R.id.input_logimmail);
        etpasswrd=(EditText)findViewById(R.id.input_loginpasswrd);
        loginlayout=(TextInputLayout)findViewById(R.id.layout_loginmail);
        passwrdlayout=(TextInputLayout)findViewById(R.id.layout_loginpasswrd);
        passwrd = (TextView) findViewById(R.id.forgotpasswrd);
        signinbtn=(Button)findViewById(R.id.signinbtn);
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginmail=etlogin.getText().toString();
                loginpaaswrd=etpasswrd.getText().toString();

                if (etlogin.length() == 0) {
                    loginlayout.setError("Email cannot be blank");
                } else if (etpasswrd.length() == 0) {
                    passwrdlayout.setError("Password cannot be blank");
                } else if (loginpaaswrd.length() < 8 || loginpaaswrd.length() > 70) {
                    Toast.makeText(getApplicationContext(), "Password should contain minimum 8 characters and max 70 characters", Toast.LENGTH_LONG).show();
                } else if (!isValidPassword(loginpaaswrd)) {
                    Toast.makeText(getApplicationContext(), "Password Should contain Special Characters and numbers", Toast.LENGTH_LONG).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(etlogin.getText().toString()).matches()) {
                  loginlayout.setError("Invalid Email Address");
                } else {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    editor = preferences.edit();
                    editor.putString("EMAILID", loginmail);
                    editor.putString("PWD", loginpaaswrd);
                    editor.commit();
                    String str = preferences.getString("EMAILID", "");
                    Log.e(TAG, "Preferences string: " + str);
                    new LoginServerTask().execute("http://10.80.15.119:8080/OptnCpt/rest/service/login");

                }
            }
        });
        Toolbar signtool = (Toolbar) findViewById(R.id.signtoolbar);
        setSupportActionBar(signtool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        passwrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.dialog_input);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Submit",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        mailtxt=userInput.getText().toString();
                                        new ForgotPassTask().execute("http://10.80.15.119:8080/OptnCpt/rest/service/recoverPassword");
                                        // get user input and set it to result
                                        // edit text
                                       // result.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

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
    public class LoginServerTask extends AsyncTask<String, String, String> {

        HttpURLConnection connection = null;
        BufferedReader bufferedReader;
        URL url;
        InputStream inputStream;

        @Override
        protected String doInBackground(String... params) {

            try {

                url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                jsonObject1 = new JSONObject();
                jsonObject1.put("loginId", "" + loginmail);
                jsonObject1.put("loginPassword", "" + loginpaaswrd);

                String jsonObj = jsonObject1.toString();
                Log.e(TAG, "doInBackground: " + jsonObj);
                //Header
                //connection.setRequestProperty("jsonobject",""+jsonObject1.toString());
                connection.setRequestProperty("loginObject", "" + jsonObj);
                connection.connect();
                inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder buffer = new StringBuilder();

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }


                finalJson = buffer.toString();
                Log.e(TAG, "JSON Object" + finalJson);
                // JSONObject parentObject=new JSONObject(finalJson);
                // JSONArray parentArray=parentObject.getJSONArray("worldpopulation");
                // JSONObject lastObject=parentArray.getJSONObject(1);
//                JSONArray parentArr=new JSONArray(finalJson);
//                JSONObject lastObject=parentArr.getJSONObject(1);
//                String result=lastObject.getString("status");


                return finalJson;

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
            if (finalJson != null) {
                try {
                    JSONObject jobj = new JSONObject(finalJson);
                    Log.e(TAG, "Response Json: " + jobj);
                  boolean str = jobj.getBoolean("status");
                    if (str==true) {
                        String msg = jobj.getString("msg");
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        empid = (String) jobj.get("jobseekerId");
                        Log.e(TAG, "EmployeeId: " + empid);
                        editor.putString("EMPID", empid);
                        editor.commit();
                        Intent i = new Intent(getApplicationContext(), MainResume.class);
                        startActivity(i);
//                        startActivity(new Intent(getActivity(), MainActivity.class));
//                        Fragment frag = new Login();
//                        FragmentTransaction ft = getFragmentManager().beginTransaction();
//                        ft.replace(R.id.register_frag,frag);
//
                    } else if (str==false) {
                        errmsg = (String) jobj.get("err_msg");
                        Log.e(TAG, "ErrorMsg: " + errmsg);
                        Toast.makeText(getApplicationContext(), errmsg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            etlogin.setText("");
            etpasswrd.setText("");
            super.onPostExecute(result);
        }
    }
    public class ForgotPassTask extends AsyncTask<String,String,String>{

        HttpURLConnection connection = null;
        BufferedReader bufferedReader;
        URL url;
        InputStream inputStream;


        @Override
        protected String doInBackground(String... params) {

            try {
                url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                jsonObject2 = new JSONObject();
                jsonObject2.put("mail", ""+mailtxt);

                String jsonObj2 = jsonObject2.toString();

                connection.setRequestProperty("forgotPasswordDetails", ""+jsonObj2);
                connection.connect();
                inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }
                finalJson = buffer.toString();
                Log.e(TAG, "RESPONSE FROM SERVER IS: "+finalJson);
                return finalJson;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection!=null){
                    connection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            if (finalJson!=null){
                try {
                    JSONObject jsonobj=new JSONObject(finalJson);
                    Log.e(TAG, "JSON OBJECT FROM SERVER: "+jsonobj);
                    Boolean str=jsonobj.getBoolean("status");
                    if (str==true)
                    {
                        String msg =jsonobj.getString("msg");
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getApplicationContext(),Forgototp.class);
                        startActivity(i);

                    }else {
                        errmsg1=jsonobj.getString("err_msg");
                        Log.e(TAG, "ErrorMsg: "+errmsg );
                        Toast.makeText(getApplicationContext(), errmsg, Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            super.onPostExecute(result);
        }
    }

}
