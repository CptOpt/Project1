package cogentdatasolutions.project1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Divya on 6/14/2016.
 */
public class Signinadapter extends FragmentStatePagerAdapter {

    int mNumOFTabs;

    public Signinadapter(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOFTabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Login logined = new Login();
                return logined;
            case 1:
                Register reg = new Register();
                return reg;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOFTabs;
    }
}

