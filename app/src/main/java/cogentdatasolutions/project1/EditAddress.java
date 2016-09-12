package cogentdatasolutions.project1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
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

/**
 * Created by madhu on 15-Jul-16.
 */
public class EditAddress extends Activity
{
    private static final String TAG = Register.class.getSimpleName();
    private Button save;
    private JSONObject jsonObject1,jsonObject2 = null;
    private HttpURLConnection connection = null;
    private BufferedReader bufferedReader = null;
    private InputStream inputStream = null;
    TextView tv;
    URL url = null;
    String msg;

    String finalJson,empid;

    EditText eadressLine,eadreesLine1,estreetName,estreetName1,epincode,epincode1;
    Button submit;
    AutoCompleteTextView pState,pCountry,tState,tCountry;
    String adress,adress1,street,street1,pin,pin1,country,country1,state,state1;
    String jadress,jadress1,jstreet,jstreet1,jpin,jpin1,jcountry,jcountry1,jstate,jstate1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editaddress);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        empid = prefs.getString("EMPID","");
        tv=(TextView)findViewById(R.id.txt);
        eadressLine  = (EditText)findViewById(R.id.addrline);
          //eadressLine.setText("namekkkuhjhhx");
        eadreesLine1 = (EditText)findViewById(R.id.addrline1);

        estreetName = (EditText)findViewById(R.id.streetname);

        estreetName1 = (EditText)findViewById(R.id.streetname1);

        epincode = (EditText)findViewById(R.id.pincode);

        epincode1 = (EditText)findViewById(R.id.pincode1);

        pState = (AutoCompleteTextView)findViewById(R.id.statespinner);

        pCountry = (AutoCompleteTextView)findViewById(R.id.countriesSpinner);

        tState = (AutoCompleteTextView)findViewById(R.id.statespinner1);

        tCountry = (AutoCompleteTextView)findViewById(R.id.countriesSpinner1);
        new GettingEditAddressJson().execute("http://10.80.15.119:8080/OptnCpt/rest/service/DisplayEmployeeAddresses");

        submit= (Button) findViewById(R.id.submit);
        submit.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EditAddressJson().execute("http://10.80.15.119:8080/OptnCpt/rest/service/storeEmployeeAddresses");
                finish();

            }
        });


    }
    private class EditAddressJson extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            adress=eadressLine.getText().toString();
            adress1=eadreesLine1.getText().toString();
            street=estreetName.getText().toString();
            street1=estreetName1.getText().toString();
            pin=epincode.getText().toString();
            pin1=epincode1.getText().toString();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {


                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                jsonObject1 = new JSONObject();
                jsonObject1.put("addressLine1", "" + adress);
                jsonObject1.put("addressLine2", "" + adress1);
                jsonObject1.put("streetName1", "" + street);
                jsonObject1.put("streetName2", "" + street1);
                jsonObject1.put("state1", "" +"texas");
                jsonObject1.put("state2", "" + "hyderabad");
                jsonObject1.put("country1", "" + "UnitedStates");
                jsonObject1.put("country2", "" + "India");
                jsonObject1.put("pincode1", "" + pin);
                jsonObject1.put("pincode2", "" + pin1);
                jsonObject1.put("employeeId", "" + empid);
                String jsonObj = jsonObject1.toString();
                Log.e(TAG, "doInBackground: " + jsonObj);
                //Header
                connection.setRequestProperty("employeeaddressdetails", "" + jsonObj);
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
                    String str = (String) jobj.get("status");
                    if (str.equals("updated successfully")) {
                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                        finish();

                    } else
                        msg=(String)jobj.get("err_msg");
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            super.onPostExecute(result);
        }
    }
    private class GettingEditAddressJson extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {

            eadressLine  = (EditText)findViewById(R.id.addrline);
            eadreesLine1 = (EditText)findViewById(R.id.addrline1);
            estreetName = (EditText)findViewById(R.id.streetname);
            estreetName1 = (EditText)findViewById(R.id.streetname1);
            epincode = (EditText)findViewById(R.id.pincode);
            epincode1 = (EditText)findViewById(R.id.pincode1);
            pState = (AutoCompleteTextView)findViewById(R.id.statespinner);
            pCountry = (AutoCompleteTextView)findViewById(R.id.countriesSpinner);
            tState = (AutoCompleteTextView)findViewById(R.id.statespinner1);
            tCountry = (AutoCompleteTextView)findViewById(R.id.countriesSpinner1);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                jsonObject1 = new JSONObject();
                jsonObject1.put("employeeId", empid);
                String jsonObj = jsonObject1.toString();
                Log.e(TAG, "doInBackground: " + jsonObj);
                //Header
                connection.setRequestProperty("empaddrdetails", "" + jsonObj);
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
                    String str = (String) jobj.get("addrDetails");
                    JSONObject parentjson=new JSONObject(str);
                    if(parentjson.has("addressLine1"))
                    {
                        jadress=parentjson.getString("addressLine1");
                        Log.e(TAG, "Response Json: " + jadress);
                        jadress1=parentjson.getString("addressLine2");
                        jstreet=parentjson.getString("streetName");
                        jstreet1=parentjson.getString("streetName2");
                        jstate=parentjson.getString("state1");
                        jstate1=parentjson.getString("state2");
                        jcountry=parentjson.getString("country1");
                        jcountry1=parentjson.getString("country2");
                        jpin=parentjson.getString("pincode1");
                        jpin1=parentjson.getString("pincode2");
                        eadressLine.setText(jadress);
                        eadreesLine1.setError(jadress1);
                        estreetName.setText(jstreet);
                        estreetName1.setText(jstreet1);
                        epincode1.setText(jpin1);
                        epincode.setText(jpin);
                        tState.setText(jstate);
                        pState.setText(jstate1);
                        tCountry.setText(jcountry);
                        pCountry.setText(jcountry1);
                    } else{
                        eadressLine.setText("");
                        eadreesLine1.setText("");
                        estreetName.setText("");
                        estreetName1.setText("");
                        epincode.setText("");
                        epincode1.setText("");
                        tState.setText("");
                        pState.setText("");
                        tCountry.setText("");
                        pCountry.setText("");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            super.onPostExecute(result);
        }
    }
}
