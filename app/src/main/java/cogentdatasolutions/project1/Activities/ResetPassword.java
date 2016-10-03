package cogentdatasolutions.project1.Activities;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cogentdatasolutions.project1.R;

public class ResetPassword extends AppCompatActivity {
    EditText et1,et2;
    private static final String TAG = Forgototp.class.getSimpleName();

    String newpwd,reenterpwd,mail,errmsg;
    Button submit;
    String finalJson;
    JSONObject jsonObject2 = null;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        et1= (EditText) findViewById(R.id.createnpwd);
        et2= (EditText) findViewById(R.id.reenternpwd);
        submit=(Button)findViewById(R.id.submitpasswrd);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mail  = prefs.getString("EMAILID","");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newpwd=et1.getText().toString();
                reenterpwd=et2.getText().toString();

                if(et1.length()==0) {
                    et1.setError("cannot be blank");
                } else if (et2.length()==0){
                    et2.setError(" cannot be blank");
                } else if (et1.length()<8||et1.length()>70){
                    Toast.makeText(getApplicationContext(), "Password should contain minimum 8 characters and max 70 characters", Toast.LENGTH_LONG).show();
                }
                else if (!isValidPassword(newpwd)){
                    Toast.makeText(getApplicationContext(), "Password Should contain Special Characters and numbers", Toast.LENGTH_LONG).show();
                }
                else if(!newpwd.equals(reenterpwd)){
                    et1.setError("password doesn't match");
                }else {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    editor = preferences.edit();
                    //editor.putString("EMAILID", loginid);
                    editor.putString("PWD", newpwd);
                    editor.commit();
                   // String str = preferences.getString("EMAILID", "");
                    Log.e(TAG, "Preferences string: " + newpwd);
                    new ResetPasswrdTask().execute("http://10.80.15.119:8080/OptnCpt/rest/service/recoverPasswordUpdation");
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
        matcher = pattern.matcher(newpwd);
        return matcher.matches();
    }
    public class ResetPasswrdTask extends AsyncTask<String,String,String> {

        HttpURLConnection connection = null;
        BufferedReader bufferedReader;
        URL url;
        InputStream inputStream;

        @Override
        protected void onPreExecute() {
            newpwd=et1.getText().toString();
            reenterpwd=et2.getText().toString();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                jsonObject2 = new JSONObject();
                jsonObject2.put("mail", ""+mail);
                jsonObject2.put("newPassword", ""+newpwd);
                jsonObject2.put("confirmNewPassword", ""+reenterpwd);
                String jsonObj2 = jsonObject2.toString();

                connection.setRequestProperty("recoverPasswordUpdationDetails", ""+jsonObj2);
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
                    boolean str=jsonobj.getBoolean("status");
                    if (str==true)
                    {
                        String msg =jsonobj.getString("msg");
                       Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
//                        empid=(String)jsonobj.get("employeeId");
//                        Log.e(TAG, "EmployeeId: "+empid );
//                        editor.putString("EMPID",empid);
//                        editor.commit();
//                        Intent i=new Intent(getApplication(),ResetPassword.class);
//                        startActivity(i);
                        finish();
                        // Toast.makeText(getApplicationContext(),"otp has sent your EmailId",Toast.LENGTH_SHORT).show();
                    }else {

                        errmsg=(String)jsonobj.get("err_msg");
                        Log.e(TAG, "ErrorMsg: "+errmsg );
                        Toast.makeText(getApplicationContext(), errmsg, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            super.onPostExecute(result);
        }
    }
}
