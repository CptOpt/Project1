package cogentdatasolutions.project1;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
     //  getSupportActionBar().setTitle("CptnOpt");
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        final TabLayout tabLayout = (TabLayout) findViewById(R.id.Tabs);

        //tabLayout.isClickable();

        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.mag));
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.heart));
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.building));
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.pro));
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.add));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager pager =(ViewPager)findViewById(R.id.Viewpage);
        final Pageradapter pageradapter = new Pageradapter(getSupportFragmentManager(),tabLayout.getTabCount());

        pager.setAdapter(pageradapter);
       pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int tabIconColor = ContextCompat.getColor(getApplicationContext(),R.color.tabSelected);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {



                int tabIconColor = ContextCompat.getColor(getApplicationContext(),R.color.tabUnSelected);
                tab.getIcon().setColorFilter(tabIconColor,PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.homeic) {
            // Handle the camera action
        } else if (id == R.id.viewprofile) {

        } else if (id == R.id.updateprofile) {

        } else if (id == R.id.profilesummary) {

        } else if (id == R.id.uploadpic) {

        } else if (id == R.id.uploadresume) {

        }
        else if (id == R.id.addprojects) {

        } else if (id == R.id.savedjobs) {

        } else if (id == R.id.appliedjobs) {

        }else if (id == R.id.visibility) {

        } else if (id == R.id.contactsettings) {

        } else if (id == R.id.changepassword) {

        }else if (id == R.id.review) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
