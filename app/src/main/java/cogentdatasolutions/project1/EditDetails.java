package cogentdatasolutions.project1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by madhu on 15-Jul-16.
 */
public class EditDetails extends AppCompatActivity
{
    private static final String TAG = Register.class.getSimpleName();

    private JSONObject jsonObject1,jsonObject2 = null;
    private HttpURLConnection connection = null;
    private BufferedReader bufferedReader = null;
    private InputStream inputStream = null;
    URL url = null;
    String finalJson;
    Button next,saveChanges;
    EditText email,dob;
    String mail,empId,addr1,addr2,street1,street2,country1,country2,state1,state2,pin1,pin2;
    Toolbar basicdetails;
    AutoCompleteTextView highestQualify,industry,specialization,university,passingYear,cLocation,jobType,pLocation,nationality;
    MultiAutoCompleteTextView skills;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editdetails);
        email= (EditText) findViewById(R.id.email);
        dob= (EditText) findViewById(R.id.dob);
        highestQualify = (AutoCompleteTextView)findViewById(R.id.qualifications);
        industry = (AutoCompleteTextView)findViewById(R.id.industry);
        specialization = (AutoCompleteTextView)findViewById(R.id.specialization);
        university = (AutoCompleteTextView)findViewById(R.id.university);
        passingYear = (AutoCompleteTextView)findViewById(R.id.passingYear);
        cLocation = (AutoCompleteTextView)findViewById(R.id.currentLocation);
        jobType = (AutoCompleteTextView)findViewById(R.id.jobtype);
        pLocation = (AutoCompleteTextView)findViewById(R.id.prefferedLocation);
        nationality = (AutoCompleteTextView)findViewById(R.id.nationality);
        skills = (MultiAutoCompleteTextView)findViewById(R.id.skills);
        basicdetails= (Toolbar) findViewById(R.id.detailsbar);
      setSupportActionBar(basicdetails);
   getSupportActionBar().setTitle("Basic Details");
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mail  = prefs.getString("EMAILID","");
        empId = prefs.getString("EMPID","");
        email.setText(mail); 
       email.setFocusable(false);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                UpdateLabel();
            }
        };
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditDetails.this,date,
                        myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        next= (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONTask().execute("http://10.80.15.119:8080/OptnCpt/rest/service/DisplayEmployeeAddresses");
            }
        });
    }
    private void UpdateLabel() {
        String myformat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myformat, Locale.US);
        dob.setText(sdf.format(myCalendar.getTime()));
    }

//    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.editoptionsmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.changepassword:
                Intent i=new Intent(EditDetails.this,Changepassword.class);
                startActivity(i);
                break;
            case R.id.settings:
                Intent i1=new Intent(EditDetails.this,Settings.class);
                startActivity(i1);
                break;
        }
        return true;
    }

    private class JSONTask extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {



            try {


                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                jsonObject1 = new JSONObject();
                jsonObject1.put("employeeId", "" + empId);
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
                    JSONObject parentObj = new JSONObject(finalJson);
                    Log.e(TAG, "Response Json:" + parentObj);
                   String str = (String) parentObj.get("addrDetails");
                    JSONObject childobj=new JSONObject(str);
                     addr1 = childobj.getString("addressLine1");
                    Log.e(TAG, "address1: " + addr1);
                     addr2=childobj.getString("addressLine2");
                    Log.e(TAG, "address2: " + addr2);
                     street1=childobj.getString("streetName1");
                     street2=childobj.getString("streetName2");
                    country1=childobj.getString("country1");
                    country2=childobj.getString("country2");
                    state1=childobj.getString("state1");
                    state2=childobj.getString("state2");
                     pin1=childobj.getString("pincode1");
                    pin2=childobj.getString("pincode2");


//                    if (str.equals("true")) {
//                        Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
//  }

                    Intent i=new Intent(EditDetails.this,EditAddress.class);
                    i.putExtra("A1",addr1);
                    i.putExtra("A2",addr2);
                    i.putExtra("S1",state1);
                    i.putExtra("S2",state2);
                    i.putExtra("P1",pin1);
                    i.putExtra("C1",country1);
                    i.putExtra("C2",country2);
                    i.putExtra("ST1",street1);
                    i.putExtra("ST2",street2);
                    i.putExtra("P2",pin2);
                    startActivity(i);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else
                Toast.makeText(getApplicationContext(), "Server Failed", Toast.LENGTH_SHORT).show();
            super.onPostExecute(result);
        }
    }


}
