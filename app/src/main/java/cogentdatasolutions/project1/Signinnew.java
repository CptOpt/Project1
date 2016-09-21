package cogentdatasolutions.project1;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Signinnew extends AppCompatActivity {
TextView passwrd;
    String mailtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinnew);
        passwrd = (TextView) findViewById(R.id.forgotpasswrd);
        Toolbar signtool = (Toolbar) findViewById(R.id.signtoolbar);
        setSupportActionBar(signtool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        passwrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setCancelable(false);
                builder.setTitle("Enter Your Email Id");
                final EditText input = new EditText(getApplicationContext());
                input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                builder.setView(input);
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mailtxt = input.getText().toString();
                        // sample.setText(mailtxt);
                        // new ForgotPassTask().execute("http://10.80.15.119:8080/OptnCpt/rest/service/recoverPassword");
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }

        });
    }
}
