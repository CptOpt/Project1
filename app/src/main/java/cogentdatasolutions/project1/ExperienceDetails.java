package cogentdatasolutions.project1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by madhu on 12-Sep-16.
 */
public class ExperienceDetails extends android.support.v4.app.Fragment
{
    Calendar myCalendar=Calendar.getInstance();
    EditText estartdate,eenddate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.experiencedetails,container,false);
        estartdate=(EditText)view.findViewById(R.id.input_startdate);
        eenddate=(EditText)view.findViewById(R.id.input_Enddate);
        final DatePickerDialog.OnDateSetListener startdate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                UpdateLabel();
            }
        };
        final DatePickerDialog.OnDateSetListener enddate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                UpdateLabel1();
            }
        };
        estartdate.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),startdate,
                        myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        eenddate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(getActivity(),enddate,
                                myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });


        return view;

    }
    private void UpdateLabel() {
        String myformat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myformat, Locale.US);
        estartdate.setText(sdf.format(myCalendar.getTime()));
    }
    private void UpdateLabel1() {
        String myformat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myformat, Locale.US);
        eenddate.setText(sdf.format(myCalendar.getTime()));
    }
}
