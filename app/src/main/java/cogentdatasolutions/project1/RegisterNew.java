package cogentdatasolutions.project1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class RegisterNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);
        Toolbar registertool=(Toolbar)findViewById(R.id.regToolbar);
        setSupportActionBar(registertool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
