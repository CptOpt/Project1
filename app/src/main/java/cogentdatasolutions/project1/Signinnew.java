package cogentdatasolutions.project1;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Signinnew extends AppCompatActivity {
TextView passwrd;
    String mailtxt;
    EditText etlogin,etpasswrd;
    TextInputLayout loginlayout,passwrdlayout;
    Button signinbtn;
    final Context context = this;

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
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.dialog_input);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Submit",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        //new ForgotPassTask().execute("http://10.80.15.119:8080/OptnCpt/rest/service/recoverPassword");
                                        // get user input and set it to result
                                        // edit text
                                       // result.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }


        });
    }
}
