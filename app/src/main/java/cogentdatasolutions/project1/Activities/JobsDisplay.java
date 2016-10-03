package cogentdatasolutions.project1.Activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cogentdatasolutions.project1.Adapters.JobsDisplayAdapter;
import cogentdatasolutions.project1.Fragments.Register;
import cogentdatasolutions.project1.JobDetails;
import cogentdatasolutions.project1.R;

/**
 * Created by madhu on 30-Sep-16.
 */
public class JobsDisplay extends ListActivity
{
    private static final String TAG = Register.class.getSimpleName();
    private Button register;
    private JSONObject jsonObject1= null;
    private HttpURLConnection connection = null;
    private BufferedReader bufferedReader = null;
    private InputStream inputStream = null;
    URL url = null;
    String job_Title,company_name,posted_Date,skills,locations;
    String final_json,err_msg;
    JobsDisplayAdapter displayAdapter;
    ListView listView;
    /** Called when the activity is first created. */
    //@SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobsdisplay);
        listView = (ListView) findViewById(android.R.id.list);
        displayAdapter = new JobsDisplayAdapter(this, R.layout.row);
        final_json = getIntent().getExtras().getString("JSON_DATA");
        Log.e(TAG, "Response Json: " + final_json);
        listView.setAdapter(displayAdapter);
        if (final_json != null)
        {
            JSONObject jobj = null;
            try {
                jobj = new JSONObject(final_json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "Response Json: " + jobj);
            boolean str = false;
            try {
                str = jobj.getBoolean("status");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (str == true) {
                String msg= null;
                try {
                    msg = jobj.getString("msg");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONArray parentJson = null;
                try {
                    parentJson = new JSONArray(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < parentJson.length(); i++) {
                    JSONObject childObj = null;
                    try {
                        childObj = parentJson.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        job_Title = childObj.getString("job_title");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        company_name = (childObj.getString("company_name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        posted_Date = childObj.getString("posted_date");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        skills = childObj.getString("skills");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        locations = childObj.getString("locations");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JobDetails details = new JobDetails(job_Title, company_name, posted_Date, skills, locations);
                        displayAdapter.add(details);

                    }

                }


        }

    }


    }
