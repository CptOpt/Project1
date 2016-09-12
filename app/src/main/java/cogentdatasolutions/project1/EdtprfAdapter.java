package cogentdatasolutions.project1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Sucharitha on 9/6/2016.
 */
public class EdtprfAdapter extends FragmentStatePagerAdapter {
    int mNumofTabs;
    public EdtprfAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumofTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                AddressTab addressTab = new AddressTab();
                return addressTab;
            case 1:
                EducationTab educationTab = new EducationTab();
                return educationTab;
            case 2:
                ExperienceTab experienceTab = new ExperienceTab();
                return experienceTab;
            case 3:
                PersonalTab personalTab = new PersonalTab();
                return personalTab;
            case 4:
                PreferencesTab preferencesTab = new PreferencesTab();
                return preferencesTab;
            case 5:
                ResumeTab resumeTab = new ResumeTab();
                return resumeTab;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumofTabs;
    }
}
