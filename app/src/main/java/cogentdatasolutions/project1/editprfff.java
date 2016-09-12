package cogentdatasolutions.project1;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by madhu on 06-Sep-16.
 */
public class Editprfff extends AppCompatActivity
{
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofilelayout);
        viewPager = (ViewPager)findViewById(R.id.view1);
        tabLayout = (TabLayout)findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Resume Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Personal Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Education Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Preferences"));
        tabLayout.addTab(tabLayout.newTab().setText("Experience Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Address Details"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager)findViewById(R.id.view1);
        EdtprfAdapter edtprfAdapter = new EdtprfAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(edtprfAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabLayout.setTabTextColors(getResources().getColor(android.R.color.black),getResources().getColor(android.R.color.white));
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
