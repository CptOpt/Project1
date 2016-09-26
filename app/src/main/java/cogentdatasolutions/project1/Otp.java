package cogentdatasolutions.project1;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
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

public class Otp extends AppCompatActivity {
    EditText et1;
    String otp,email,finalJson,errmsg;
    TextInputLayout otplayout;
    private static final String TAG = Otp.class.getSimpleName();

    JSONObject jsonObject2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        et1=(EditText)findViewById(R.id.input_otp);
        otplayout=(TextInputLayout)findViewById(R.id.layout_otp);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        email = prefs.getString("EMAILID","");
        Button submit=(Button)findViewById(R.id.otpsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.length()==0)
                {
                    otplayout.setError("cannot be empty");
                }else
                {
                    new VerifycodeJson().execute("http://10.80.15.119:8080/OptnCpt/rest/service/verificationSubmission");
                }
            }
        });

    }
    public class VerifycodeJson extends AsyncTask<String,String,String> {

        HttpURLConnection connection = null;
        BufferedReader bufferedReader;
        URL url;
        InputStream inputStream;

        @Override
        protected void onPreExecute() {

            otp = et1.getText().toString();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                jsonObject2 = new JSONObject();
                jsonObject2.put("verificationCode", "" + otp);
                jsonObject2.put("mail", "" + email);
                Log.e(TAG, "RESPONSE FROM SERVER IS: " + email);
                String jsonObj2 = jsonObject2.toString();

                connection.setRequestProperty("verificationCodeDetails", "" + jsonObj2);
                connection.connect();
                inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder buffer = new StringBuilder();

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }
                finalJson = buffer.toString();
                Log.e(TAG, "RESPONSE FROM SERVER IS: " + finalJson);
                return finalJson;

            } catch (IOException e) {
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

            if (finalJson != null) {
                try {
                    JSONObject jsonobj = new JSONObject(finalJson);
                    Log.e(TAG, "JSON OBJECT FROM SERVER: " + jsonobj);
                    Boolean str = jsonobj.getBoolean("status");
                    if (str == true) {

                        String msg = jsonobj.getString("msg");
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                    } else {
                        errmsg = jsonobj.getString("err_msg");
                        Toast.makeText(getApplicationContext(), errmsg, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
          et1.setText("");
            super.onPostExecute(result);
        }

    }
}


