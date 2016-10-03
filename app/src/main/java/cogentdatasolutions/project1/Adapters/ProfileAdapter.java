package cogentdatasolutions.project1.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cogentdatasolutions.project1.Fragments.AddressDetails;
import cogentdatasolutions.project1.Fragments.EducationalFragment;
import cogentdatasolutions.project1.Fragments.ExperienceDetails;
import cogentdatasolutions.project1.Fragments.PersonalDetails;
import cogentdatasolutions.project1.Fragments.Preferences;
import cogentdatasolutions.project1.Fragments.ResumeDetails;

/**
 * Created by madhu on 12-Sep-16.
 */
public class ProfileAdapter extends FragmentStatePagerAdapter
{
    int mNumoftabs;

    public ProfileAdapter(FragmentManager fm,int Numoftabs) {
        super(fm);
        this.mNumoftabs=Numoftabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                ResumeDetails resumeDetails=new ResumeDetails();
                return resumeDetails;
            case 1:
                EducationalFragment educationalFragment=new EducationalFragment();
                return educationalFragment;
            case 2:
                ExperienceDetails experienceDetails=new ExperienceDetails();
                return experienceDetails;
            case 3:
                PersonalDetails personalDetails=new PersonalDetails();
                return personalDetails;
            case 4:
                AddressDetails addressDetails=new AddressDetails();
                return addressDetails;
            case 5:
                Preferences preferences=new Preferences();
                return preferences;

        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumoftabs;
    }
}
