package cogentdatasolutions.project1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Divya on 6/13/2016.
 */
public class Saved extends Fragment
{
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved,container,false);
        tabLayout = (TabLayout) view.findViewById(R.id.innerTab);
        tabLayout.addTab(tabLayout.newTab().setText("My Saved Jobs"));
        tabLayout.addTab(tabLayout.newTab().setText("My Jobs"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        return view;

    }
}
