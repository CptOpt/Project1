package cogentdatasolutions.project1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Divya on 8/20/2016.
 */
public class Sessions  {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public Sessions(Context context){
        preferences = PreferenceManager.getDefaultSharedPreferences(context);

    }
    public void setLoginDetails(String username,String password){
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
    }
    public String getUsername(){
        String username = preferences.getString("username","");

        return username;
    }
    public String getPassword(){
        String Password = preferences.getString("password","");
        return Password;
    }
}
