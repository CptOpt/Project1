package cogentdatasolutions.project1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

/**
 * Created by madhu on 30-Aug-16.
 */
public class Forgototp extends Activity
{
    private static final String TAG = Forgototp.class.getSimpleName();
    EditText forgototp;
    String otp,empid ,mail,errmsg;
    Button submit;
    String finalJson;
    JSONObject jsonObject2 = null;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgototp);
        forgototp =(EditText)findViewById(R.id.forgototp);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mail  = prefs.getString("EMAILID","");
        submit=(Button)findViewById(R.id.otpsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (forgototp.length()==0)
                {
                    forgototp.setError("cannot be blank");
                }else
                {
                    new ForgotOtpTask().execute("http://10.80.15.119:8080/OptnCpt/rest/service/recoverPasswordVerificationCode");

                }

            }
        });

    }
    public class ForgotOtpTask extends AsyncTask<String,String,String> {

        HttpURLConnection connection = null;
        BufferedReader bufferedReader;
        URL url;
        InputStream inputStream;

        @Override
        protected void onPreExecute() {
            otp=forgototp.getText().toString();
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
                jsonObject2.put("verificationCode", ""+otp);

                String jsonObj2 = jsonObject2.toString();

                connection.setRequestProperty("verificationCodeDetails", ""+jsonObj2);
                Log.e(TAG, "Details: "+jsonObj2);
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
                    String str=jsonobj.getString("status");
                    if (str.equals("true"))
                    {
                        String msg =jsonobj.getString("msg");
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getApplication(),ResetPassword.class);
                        startActivity(i);
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

