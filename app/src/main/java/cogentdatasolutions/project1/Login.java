package cogentdatasolutions.project1;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Divya on 6/14/2016.
 */
public class Login extends Fragment {
    EditText emailId, password;
    Button submit;
    TextView textView;
    String loginid,loginpassword;
    JSONObject jsonObject1=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login,container,false);

        emailId = (EditText) view.findViewById(R.id.emailId);
        password = (EditText) view.findViewById(R.id.password);
        submit = (Button) view.findViewById(R.id.submit);
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.cogentdatasolutions.com";
                Intent i = new Intent();
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginid=emailId.getText().toString();
                loginpassword=password.getText().toString();

                if(emailId.length()==0 && password.length()==0) {
                    emailId.setError("Email cannot be blank");
                    //emailId.setError(Html.fromHtml("<font color='green'>Hello</font>"));
                    password.setError("Password cannot be blank");
                    //password.setError(Html.fromHtml("<font color='green'>Hello</font>"));
                } else if(!Patterns.EMAIL_ADDRESS.matcher(emailId.getText().toString()).matches()){
                    emailId.setError("Invalid Email Address");
                }else {

                new JSONTask().execute("http://10.80.15.119:8080/OptnCpt/rest/service/registration");

                Toast.makeText(getActivity(), "login successfull.....", Toast.LENGTH_SHORT).show();
            }


            }
        });
        return view;
    }


    public class JSONTask extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader bufferedReader = null;

            try {


                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();


                connection.setRequestMethod("POST");
                jsonObject1 = new JSONObject();
               // jsonObject1.put("Full Name", "" + name);
                jsonObject1.put("Email", "" + loginid);
                jsonObject1.put("Password", "" + loginpassword);


                jsonObject1.toString().trim();
                //Header
                //connection.setRequestProperty("jsonobject",""+jsonObject1.toString());
                connection.setRequestProperty("loginObject", "" + jsonObject1);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
        }
    }
}
