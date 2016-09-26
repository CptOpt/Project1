package cogentdatasolutions.project1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Divya on 6/13/2016.
 */
public class Saved extends android.support.v4.app.Fragment
{
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved,container,false);
        tabLayout = (TabLayout) view.findViewById(R.id.innerTab);
        tabLayout.addTab(tabLayout.newTab().setText("My Saved Jobs"));
        tabLayout.addTab(tabLayout.newTab().setText("Applied Jobs"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewpager = (ViewPager)view.findViewById(R.id.view1);
        final MyJobAdapter myJobsAdapter = new MyJobAdapter(getFragmentManager(),tabLayout.getTabCount());
        viewpager.setAdapter(myJobsAdapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                int tabIconColor = ContextCompat.getColor(getActivity(),R.color.tabSelected);
//                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                tabLayout.setTabTextColors(getResources().getColor(android.R.color.black),getResources().getColor(android.R.color.white));
               viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabLayout.setTabTextColors(getResources().getColor(android.R.color.white),getResources().getColor(android.R.color.black));
//                int tabIconColor = ContextCompat.getColor(getActivity(),R.color.tabUnSelected);
//                tab.getIcon().setColorFilter(tabIconColor,PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;

    }
}
