package cogentdatasolutions.project1.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

import cogentdatasolutions.project1.R;

/**
 * Created by madhu on 26-Jul-16.
 */
public class Changepassword extends Activity
{
    private static final String TAG = Changepassword.class.getSimpleName();
    private EditText et1,et2;
    private Button btn;
    SharedPreferences.Editor editor;
    private String response;
    private TextView tv;
    String email,password;
    String currentpwdstr,newpwdstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);
        et1 = (EditText)findViewById(R.id.currentPwd);
        et2 =(EditText)findViewById(R.id.newPwd);
//        et3 = (EditText)findViewById(R.id.conPwd);
        btn = (Button)findViewById(R.id.btnSub);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        email  = prefs.getString("EMAILID","");
        password = prefs.getString("PWD","");
        Log.e(TAG, "EmailAddress: "+email );
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentpwdstr = et1.getText().toString();
                newpwdstr = et2.getText().toString();
                if (!password.equals(currentpwdstr))
                {
                    et1.setError("Current Password Doesn't Match");

                }else{
                    new ResetPwd().execute("http://10.80.15.119:8080/OptnCpt/rest/service/changePassword");

                }

            }
        });
    }
    public class ResetPwd extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;
        HttpURLConnection connection;
        BufferedReader reader;
        JSONObject jsonObject;

//        String confpwdstr = et3.getText().toString();


        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(Changepassword.this);
            dialog.setTitle("Password Changing");
            dialog.setMessage("Initializing......");
            dialog.setCancelable(false);
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection)url.openConnection();
                jsonObject = new JSONObject();
                jsonObject.put("mail",""+email);
                jsonObject.put("oldPassword",""+currentpwdstr);
                jsonObject.put("newPassword",""+newpwdstr);
//                jsonObject.put("newpassword",""+confpwdstr);
                jsonObject.toString().trim();
                Log.e(TAG, "My Json Obj: "+jsonObject );
                connection.setRequestMethod("POST");
                connection.setRequestProperty("changePasswordDetails",""+jsonObject);

                InputStream is = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder builder = new StringBuilder();

                String line = "";
                while ((line = reader.readLine())!=null){
                    builder.append(line);
                }

                response = builder.toString();
                return response;

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {
                if (connection!=null)
                    connection.disconnect();
            }
            return null;
        }

        //        private void displayToast(String str) {
//            Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
//        }
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);

            if (response!=null){
                try {
                    JSONObject resObj = new JSONObject(response);
                    Log.e(TAG, "Response JsonObject: " +resObj );
                    String responseValue = resObj.getString("status");
                    Log.e(TAG, "Response Value: " +responseValue );

                    if (responseValue.matches("true")){
                        String responsemsgtrue = resObj.getString("msg");
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        editor = preferences.edit();
                        editor.putString("PWD",newpwdstr);
                        editor.commit();
                        Toast.makeText(getApplicationContext(),responsemsgtrue, Toast.LENGTH_LONG).show();
                        finish();

                    }else {
                       String responsemsgfalse = resObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), responsemsgfalse , Toast.LENGTH_LONG).show();
                       // Log.e(TAG, "Response Msg: "+responsemsgfalse );
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else{
                Toast.makeText(getApplicationContext(), "Server failed Try again later", Toast.LENGTH_LONG).show();
            }
            dialog.dismiss();
        }


    }


}
