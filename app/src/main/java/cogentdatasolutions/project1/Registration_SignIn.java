package cogentdatasolutions.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registration_SignIn extends AppCompatActivity {
    Button registerbtn,signinbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__sign_in);
        registerbtn=(Button)findViewById(R.id.registerbtn);
        signinbtn=(Button)findViewById(R.id.signinbtn);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegisterNew.class);
                startActivity(i);
            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),Signinnew .class);
                startActivity(i1);

            }
        });
    }
}
