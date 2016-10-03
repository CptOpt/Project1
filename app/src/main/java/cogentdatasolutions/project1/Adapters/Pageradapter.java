package cogentdatasolutions.project1.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cogentdatasolutions.project1.Activities.Company;
import cogentdatasolutions.project1.Fragments.Profile;
import cogentdatasolutions.project1.Fragments.Saved;
import cogentdatasolutions.project1.Fragments.Search;
import cogentdatasolutions.project1.Fragments.Xtras;

/**
 * Created by Divya on 6/13/2016.
 */
public class Pageradapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public Pageradapter(FragmentManager fm,int NumOfTabs) {
        super(fm);
        this.mNumOfTabs =NumOfTabs;
        //super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Search search = new Search();
                return search;
            case 1:
                Saved saved = new Saved();
                return saved;
            case 2:
                Company company = new Company();
                return company;
            case 3:
                Profile profile = new Profile();
                return profile;
            case 4:
                Xtras xtras = new Xtras();
                return xtras;

        }
            return null;
    }

    @Override
    public int getCount() {return mNumOfTabs;}
    }

