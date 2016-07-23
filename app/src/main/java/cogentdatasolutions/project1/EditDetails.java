package cogentdatasolutions.project1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by madhu on 15-Jul-16.
 */
public class EditDetails extends Activity
{
    Button next;
    EditText email,dob;
    String mail;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editdetails);
        email= (EditText) findViewById(R.id.email);
        dob= (EditText) findViewById(R.id.dob);
        Intent i=getIntent();
       mail=i.getStringExtra("key1");
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
                Intent i=new Intent(EditDetails.this,EditAddress.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void UpdateLabel() {
        String myformat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myformat, Locale.US);
        dob.setText(sdf.format(myCalendar.getTime()));
    }
}
