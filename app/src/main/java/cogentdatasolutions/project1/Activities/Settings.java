package cogentdatasolutions.project1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cogentdatasolutions.project1.R;

/**
 * Created by Sucharitha on 8/9/2016.
 */
public class Settings extends Activity
{
    Button visibility,communication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        visibility= (Button) findViewById(R.id.visibility);
        communication= (Button) findViewById(R.id.communications);
        visibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Settings.this,VisibilitySettings.class);
                startActivity(i);
                finish();
            }
        });
        communication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Settings.this,Communication.class);
                startActivity(i);
                finish();
            }
        });
    }

}
