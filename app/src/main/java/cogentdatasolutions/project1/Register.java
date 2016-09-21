package cogentdatasolutions.project1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Divya on 6/14/2016.
 */
public class Register extends Fragment {
    private static final String TAG = Register.class.getSimpleName();
    private Button register;
    private JSONObject jsonObject1,jsonObject2 = null;
    private HttpURLConnection connection = null;
    private BufferedReader bufferedReader = null;
    private InputStream inputStream = null;
    URL url = null;
    private EditText firstName, lastName, emailAddress, password1, password2;
    String fname,lname, mail, password, confrmpaswrd;
    String finalJson,errmsg,vcode;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.register, container, false);
//        firstName = (EditText) view.findViewById(R.id.firstNameReg);
//        lastName = (EditText) view.findViewById(R.id.lastNameReg);
//        emailAddress = (EditText) view.findViewById(R.id.emailAddress);
//        password1 = (EditText) view.findViewById(R.id.password1);
//        password2 = (EditText) view.findViewById(R.id.password2);
//        register = (Button) view.findViewById(R.id.register);
//
//
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fname = firstName.getText().toString();
//                lname = lastName.getText().toString();
//                mail = emailAddress.getText().toString();
//                password = password1.getText().toString();
//                confrmpaswrd = password2.getText().toString();
//
//                if (fname.length() == 0) {
//                    firstName.setError("First Name cannot be Blank");
//                } else if (lname.length() == 0){
//                    lastName.setError("Last Name cannot be blank");
//                } else if (emailAddress.length() == 0) {
//                    emailAddress.setError("Email Id required for registration");
//                } else if (password1.length() == 0) {
//                    password1.setError("Password required");
//                } else if (password2.length() == 0) {
//                    password2.setError("password required");
//                } else if (!password.equals(confrmpaswrd)){
//                    password2.setError("Passwords doesn't match");
//                } else if (!isValidPassword(password)){
//                    password1.setError("Password should contain one alphabet, one symbol, one number");
//                }
//                else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress.getText().toString()).matches()) {
//                    emailAddress.setError("Invalid Email Id");
//                } else if (password.length() < 8 || password.length() > 15) {
//                    password1.setError("password should be 8-15 characters");
//                    password2.setError("password should be 8-15 characters");
//                } else if (!password.trim().equals(confrmpaswrd.trim())) {
//                    password1.setError("password and confirm password must be same");
//                } else {
//                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("EMAILID",mail);
//                    editor.apply();
//                    String str=preferences.getString("EMAILID","");
//                    Log.e(TAG, "Preferences string: "+str );
//
//                    new JSONTask().execute("http://10.80.15.119:8080/OptnCpt/rest/service/registration");
//
//                }
//
//            }
//        });
//
      return view;
//    }
//
//    public boolean isValidPassword(final String password){
//        Pattern pattern;
//        Matcher matcher;
////        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
//        final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
//        pattern = Pattern.compile(PASSWORD_PATTERN);
//        matcher = pattern.matcher(password);
//        return matcher.matches();
//
//    }
//
//
//    private class JSONTask extends AsyncTask<String, String, String> {
//        @Override
//        protected void onPreExecute() {
//            mail = emailAddress.getText().toString();
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            try {
//
//                URL url = new URL(params[0]);
//                connection = (HttpURLConnection) url.openConnection();
//
//                connection.setRequestMethod("POST");
//                jsonObject1 = new JSONObject();
//                jsonObject1.put("firstName", "" + fname);
//                jsonObject1.put("lastName",""+lname);
//                jsonObject1.put("mail", "" + mail);
//                jsonObject1.put("password", "" + password);
//                jsonObject1.put("conpassword", "" + confrmpaswrd);
//
//
//                String jsonObj = jsonObject1.toString();
//                Log.e(TAG, "doInBackground: " + jsonObj);
//                //Header
//                connection.setRequestProperty("registerObject", "" + jsonObj);
//                connection.connect();
//                inputStream = connection.getInputStream();
//                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//                StringBuilder buffer = new StringBuilder();
//
//                String line;
//
//                while ((line = bufferedReader.readLine()) != null) {
//                    buffer.append(line);
//                }
//
//                finalJson = buffer.toString();
//                Log.e(TAG, "JSON Object" + finalJson);
//
//                return finalJson;
//
//            } catch (IOException | JSONException e) {
//                e.printStackTrace();
//            } finally {
//                if (connection != null) {
//                    connection.disconnect();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            if (finalJson != null) {
//                try {
//                    JSONObject jobj = new JSONObject(finalJson);
//                    Log.e(TAG, "Response Json: " + jobj);
//                    String str = (String) jobj.get("status");
//                    if (str.equals("true")) {
//                        String msg=jobj.getString("msg");
//                        Toast.makeText(getContext(),msg, Toast.LENGTH_SHORT).show();
//                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
//                        builder.setCancelable(false);
//                        builder.setTitle("Enter Verification Code");
//                        final EditText input=new EditText(getContext());
//                        input.setInputType(InputType.TYPE_CLASS_TEXT);
//                        builder.setView(input);
//                        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                vcode=input.getText().toString();
//                                // sample.setText(mailtxt);
//                                new VerifycodeJson().execute("http://10.80.15.119:8080/OptnCpt/rest/service/verificationSubmission");
//                            }
//                        });
////                        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
////                            @Override
////                            public void onClick(DialogInterface dialog, int which) {
////                                dialog.cancel();
////                            }
////                        });
//                        builder.show();
//                    } else
//                    errmsg=jobj.getString("err_msg");
//                        Toast.makeText(getContext(), errmsg, Toast.LENGTH_SHORT).show();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            super.onPostExecute(result);
//        }
//    }
//    public class VerifycodeJson extends AsyncTask<String,String,String>{
//
//        HttpURLConnection connection = null;
//        BufferedReader bufferedReader;
//        URL url;
//        InputStream inputStream;
//
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            try {
//                url = new URL(params[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//
//                jsonObject2 = new JSONObject();
//                jsonObject2.put("verificationCode", ""+vcode);
//                jsonObject2 .put("mail",""+mail);
//                Log.e(TAG, "RESPONSE FROM SERVER IS: "+mail);
//                String jsonObj2 = jsonObject2.toString();
//
//                connection.setRequestProperty("verificationCodeDetails", ""+jsonObj2);
//                connection.connect();
//                inputStream = connection.getInputStream();
//                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//                StringBuilder buffer = new StringBuilder();
//
//                String line;
//
//                while ((line = bufferedReader.readLine()) != null) {
//                    buffer.append(line);
//                }
//                finalJson = buffer.toString();
//                Log.e(TAG, "RESPONSE FROM SERVER IS: "+finalJson);
//                return finalJson;
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            } finally {
//                if (connection!=null){
//                    connection.disconnect();
//                }
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            if (finalJson!=null){
//                try {
//                    JSONObject jsonobj=new JSONObject(finalJson);
//                    Log.e(TAG, "JSON OBJECT FROM SERVER: "+jsonobj);
//                    String str=jsonobj.getString("status");
//                    if (str.equals("true"))
//                    {
//
//                            String msg=jsonobj.getString("msg");
//                            Toast.makeText(getContext(),msg, Toast.LENGTH_SHORT).show();
//
//                        }else {
//                        errmsg=jsonobj.getString("err_msg");
//                        Toast.makeText(getContext(), errmsg, Toast.LENGTH_SHORT).show();                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            super.onPostExecute(result);
//        }
    }

}
