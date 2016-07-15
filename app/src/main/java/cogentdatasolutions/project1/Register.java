package cogentdatasolutions.project1;

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
public class Register extends Fragment {
    private static final String TAG = Register.class.getSimpleName();
    private static final String PACKAGE = "com.cogentdatasolutions.project1";
    Button register;
    JSONObject jsonObject1=null;


    EditText fullanme,emailAddress, password1, password2;
    String name,mail,password,confrmpaswrd;

    //ImageButton ln_login,fb_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.register,container,false);
        fullanme = (EditText) view.findViewById(R.id.fullname);
        emailAddress = (EditText) view.findViewById(R.id.emailAddress);
        password1 = (EditText) view.findViewById(R.id.password1);
        password2 = (EditText) view.findViewById(R.id.password2);
        register = (Button) view.findViewById(R.id.register);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // register.setVisibility(View.GONE);
                 name=fullanme.getText().toString();
                mail=emailAddress.getText().toString();
                password=password1.getText().toString();
                confrmpaswrd=password2.getText().toString();

                if(emailAddress.length()==0 && password1.length()==0 && password2.length()==0 && fullanme.length()==0){
                    fullanme.setError("FullName cannot be Blank");
                    emailAddress.setError("Email Id required for registration");
                    password1.setError("Password required");
                    password2.setError("password required");
                } else if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress.getText().toString()).matches()){
                    emailAddress.setError("Invalid Email Id");
                }else if (password.length()<8||password.length()>15){
                    password1.setError("password should be 8-15 charactes");
                    password2.setError("password should be 8-15 charactes");
                }else  if (!password.trim().equals(confrmpaswrd.trim())){
                    password1.setError("password and confirm password must be same");
                }else {

                    new JSONTask().execute("http://10.80.15.119:8080/OptnCpt/rest/service/registration");

                    Toast.makeText(getActivity(), "Registering.....", Toast.LENGTH_SHORT).show();
                }

            }
        });

//        ln_login = (ImageButton) view.findViewById(R.id.ln_login);
//        ln_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login_linkedin();
//            }
//        });
//
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
                jsonObject1.put("Full Name", "" + name);
                jsonObject1.put("Email", "" + mail);
                jsonObject1.put("Password", "" + password);


                jsonObject1.toString().trim();
                //Header
                //connection.setRequestProperty("jsonobject",""+jsonObject1.toString());
                connection.setRequestProperty("registerObject", "" + jsonObject1);
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

    }
    }
