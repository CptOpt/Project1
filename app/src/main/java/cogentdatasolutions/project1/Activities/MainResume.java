package cogentdatasolutions.project1.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.StringTokenizer;

import cogentdatasolutions.project1.R;

public class MainResume extends AppCompatActivity {
    Button resume,upload;
    private static final String TAG = Fab.class.getSimpleName();
    TextView  skip;
    String pictureDirectoryPath, picturePath;
    Uri uri;
    String uploadedFileName,mail,fileName;
    StringTokenizer tokens;
    String first;
    String file_1;
    File file1;
    FileBody fileBody1;
    String response,empid;
    private JSONObject jsonObject1 = null;
    String errmsg,resumeerr;
    String RES_URL="http://10.80.15.119:8080/OptnCpt/rest/service/jobseekerResumeParsing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resume);
        resume=(Button)findViewById(R.id.main_resumeuplaod);
       upload=(Button)findViewById(R.id.upld);
        skip=(TextView)findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainResume.this,Editprofile1.class);
                startActivity(i);
            }
        });
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                mail  = prefs.getString("EMAILID","");
                //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                empid = prefs.getString("EMPID","");
                try {
                    startActivityForResult(Intent.createChooser(intent, "Select file to upload"), 2);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Please install File Manager", Toast.LENGTH_SHORT).show();
                }

            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new UploadpdfTask().execute(RES_URL);
                AsyncHttpClient httpClient = new AsyncHttpClient();
                httpClient.addHeader("Content-type", "application/json");
                MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
//                file1 = new File(Environment.getExternalStorageDirectory(),file_1);
                fileBody1 = new FileBody(file1);
                reqEntity.addPart("resume",fileBody1);
                httpClient.addHeader("content-length",reqEntity.getContentLength()+"");
                httpClient.addHeader(reqEntity.getContentType().getName(),reqEntity.getContentType().getValue());



                RequestParams requestParams = new RequestParams();
                jsonObject1= new JSONObject();
                try {
                    jsonObject1.put("mail", ""+mail);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonObject1.put("jobseekerId", ""+empid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Log.e(TAG, "connecting to server6: "+RES_URL);
                String jsonObj1 = jsonObject1.toString();

                requestParams.put("parsingDetails", ""+jsonObj1);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 2) {
                Uri selectdFileUri = data.getData();
                file1 = new File(selectdFileUri.getPath());
                //   Log.e(TAG, "File: " + file1.getName());
                uploadedFileName = file1.getName();
                //  Log.e(TAG, "FILE NAME: " + uploadedFileName);
                tokens = new StringTokenizer(uploadedFileName, ":");
                first = tokens.nextToken();
                file_1 = tokens.nextToken().trim();
                // Log.e(TAG, "FILE 1: " + file_1);

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
//    public class UploadpdfTask extends AsyncTask<String,String,String> {
//
//        private ProgressDialog dialog;
//
//        @Override
//        protected void onPreExecute() {
//
//            super.onPreExecute();
//            dialog = new ProgressDialog(MainResume.this);
//            dialog.setTitle("Uploading");
//            dialog.setMessage("Please Wait...");
//            dialog.setCancelable(true);
//            dialog.show();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//                URL url = new URL(params[0]);
//              //  Log.e(TAG, "connecting to server1: "+RES_URL);
//                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//               // Log.e(TAG, "connecting to server2: "+RES_URL);
//                String reqHead = "Accept:application/json";
//               // Log.e(TAG, "connecting to server3: "+RES_URL);
//                connection.setRequestMethod("POST");
//              //  Log.e(TAG, "connecting to server4: "+RES_URL);
//                connection.setRequestProperty("connection","Keep-Alive"+reqHead);
//               // Log.e(TAG, "connecting to server5: "+RES_URL);
//                // connection.setRequestProperty("mail",""+mail);
//               // connection.setRequestProperty("jobseekerId",""+empid);
//                jsonObject1= new JSONObject();
//                jsonObject1.put("mail", ""+mail);
//                jsonObject1.put("jobseekerId", ""+empid);
//               // Log.e(TAG, "connecting to server6: "+RES_URL);
//                String jsonObj1 = jsonObject1.toString();
//
//                connection.setRequestProperty("parsingDetails", ""+jsonObj1);
//             //   Log.e(TAG, "connecting to server7: "+RES_URL);
//
//                MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
//              //  Log.e(TAG, "connecting to server8: "+RES_URL);
////                file1 = new File(Environment.getExternalStorageDirectory(),file_1);
//                fileBody1 = new FileBody(file1);
//               // Log.e(TAG, "connecting to server9: "+RES_URL);
//                reqEntity.addPart("resume",fileBody1);
//               // Log.e(TAG, "connecting to server10: "+RES_URL);
//                connection.addRequestProperty("content-length",reqEntity.getContentLength()+"");
//               // Log.e(TAG, "connecting to server11: "+RES_URL);
//                connection.addRequestProperty(reqEntity.getContentType().getName(),reqEntity.getContentType().getValue());
//              //  Log.e(TAG, "connecting to server12: "+RES_URL);
//                OutputStream os = connection.getOutputStream();
//               // Log.e(TAG, "connecting to server13: "+RES_URL);
//                reqEntity.writeTo(connection.getOutputStream());
//               os.close();
//                connection.connect();
//             //   Log.e(TAG, "connecting to server14: "+RES_URL);
//
//                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
//                    return readStream(connection.getInputStream());
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        private String readStream(InputStream inputStream) {
//
//            BufferedReader reader;
//            StringBuilder builder = new StringBuilder();
//
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            try {
//                while ((line = reader.readLine())!=null){
//                    builder.append(line);
//                }
//                response = builder.toString();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return response;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            if (response!=null) {
//
//                JSONObject jobj;
//                try {
//                    jobj = new JSONObject(response);
//                    Log.e(TAG, "Response Json: " + response);
//                   boolean stat=jobj.getBoolean("status");
//                    if (stat==true){
//                        String msg=jobj.getString("msg");
//                        Toast.makeText(MainResume.this,msg,Toast.LENGTH_LONG).show();
//                    }else {
//                        resumeerr = jobj.getString("err_msg");
//                        Toast.makeText(MainResume.this, resumeerr, Toast.LENGTH_LONG).show();
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                dialog.dismiss();
//                // Toast.makeText(getApplicationContext(), "Files Uploaded..", Toast.LENGTH_SHORT).show();
//            }
////            else
////                Toast.makeText(getApplicationContext(), "Server Failed...", Toast.LENGTH_SHORT).show();
//        }
//    }

}
