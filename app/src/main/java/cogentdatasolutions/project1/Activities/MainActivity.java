package cogentdatasolutions.project1.Activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import cogentdatasolutions.project1.Adapters.Pageradapter;
import cogentdatasolutions.project1.Fragments.MyJobs;
import cogentdatasolutions.project1.Fragments.MySaved;
import cogentdatasolutions.project1.R;

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
        FragmentManager fragmentManager=getSupportFragmentManager();
        int id = item.getItemId();

        if (id == R.id.homeic) {
            {


            }
        } else if (id == R.id.viewprofile) {
            Intent i=new Intent(MainActivity.this,Fab.class);
          startActivity(i);


        } else if (id == R.id.updateprofile) {
            Intent i5=new Intent(MainActivity.this,Editprofile1.class);
            startActivity(i5);

        } else if (id == R.id.profilesummary) {


        } else if (id == R.id.uploadpic) {

        } else if (id == R.id.uploadresume) {
            Intent i1=new Intent(MainActivity.this,Editprofile1.class);
            startActivity(i1);
        }
        else if (id == R.id.addprojects) {
            Intent i7=new Intent(MainActivity.this,AddProject.class);
            startActivity(i7);
        } else if (id == R.id.savedjobs1) {

          fragmentManager.beginTransaction().replace(R.id.content_frame,new MySaved()).commit();

        } else if (id == R.id.appliedjobs1) {
     fragmentManager.beginTransaction().replace(R.id.content_frame,new MyJobs()).commit();
        }else if (id == R.id.visibility)
        {
            Intent i2=new Intent(MainActivity.this,VisibilitySettings.class);
            startActivity(i2);

        } else if (id == R.id.contactsettings) {
            Intent i3=new Intent(MainActivity.this,Communication.class);
            startActivity(i3);

        } else if (id == R.id.changepwd) {
            Intent i4=new Intent(MainActivity.this,Changepassword.class);
            startActivity(i4);

        }else if (id == R.id.review) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
