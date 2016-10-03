package cogentdatasolutions.project1.Activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import cogentdatasolutions.project1.Adapters.ProfileAdapter;
import cogentdatasolutions.project1.R;

/**
 * Created by madhu on 12-Sep-16.
 */
public class Editprofile1 extends AppCompatActivity
{
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile1);
        viewPager=(ViewPager)findViewById(R.id.propager);
        tabLayout=(TabLayout)findViewById(R.id.prftab);
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.resumeuplaod));
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.educationdetails));
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.workexp));
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.personaldeatils));
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.address));
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.services));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager=(ViewPager)findViewById(R.id.propager);
         ProfileAdapter adapter=new ProfileAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getApplicationContext(),R.color.newtabselected);
                tab.getIcon().setColorFilter(tabIconColor,PorterDuff.Mode.SRC_IN);
//              int tabIconColor = ContextCompat.getColor(getApplicationContext(),getResources().getColor(R.color.newtabselected));
//                tab.getIcon().setColorFilter(tabIconColor,PorterDuff.Mode.SRC_IN);
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tabSelected));

//                tabLayout.setTabTextColors(getResources().getColor(android.R.color.black),getResources().getColor(android.R.color.white));
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getApplicationContext(),R.color.tabSelected);
                tab.getIcon().setColorFilter(tabIconColor,PorterDuff.Mode.SRC_IN);

//                tabLayout.setTabTextColors(getResources().getColor(android.R.color.white),getResources().getColor(android.R.color.black));
//                viewPager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//                int tabIconColor = ContextCompat.getColor(getApplicationContext(),R.color.newtabselected);
//                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
//                tabLayout.setTabTextColors(getResources().getColor(android.R.color.black),getResources().getColor(android.R.color.white));
//                viewPager.setCurrentItem(tab.getPosition());


            }
        });
    }
}
