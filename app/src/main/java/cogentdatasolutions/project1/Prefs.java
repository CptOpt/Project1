package cogentdatasolutions.project1;

import android.content.SharedPreferences;

/**
 * Created by madhu on 16-Jul-16.
 */
public class Prefs {

    private String email,password,fullName;

    public String Prefs(String mail, String pwd, String name){
        this.email = mail;
        this.password = pwd;
        this.fullName = name;
        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
