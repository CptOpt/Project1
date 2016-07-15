package cogentdatasolutions.project1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by madhu on 21-Jun-16.
 */
public class Searchadapter extends FragmentStatePagerAdapter {

    int mNumoftabs;

    public Searchadapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumoftabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Jobstab job = new Jobstab();
                return job;
            case 1:
                CompaniesTab companies = new CompaniesTab();
                return companies;
            case 2:
                Salariestab salaries = new Salariestab();
                return salaries;
        }

        return null;
    }

    @Override
    public int getCount() {
        return mNumoftabs;
    }
}

