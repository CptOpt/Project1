package cogentdatasolutions.project1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by madhu on 27-Aug-16.
 */
public class MyJobAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public MyJobAdapter(FragmentManager fm,int NumOfTabs) {
        super(fm);
        this.mNumOfTabs =NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MyJobs myJobs = new MyJobs();
                return myJobs;
            case 1:
                MySaved mySaved = new MySaved();
                return mySaved;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
