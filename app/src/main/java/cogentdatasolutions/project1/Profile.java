package cogentdatasolutions.project1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * Created by Divya on 6/13/2016.
 */
public class Profile extends Fragment {
    Button resume,signin,signout,editpf,recommended,tc,policy;
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.profile,container,false);
      //  Animation animation1 = AnimationUtils.loadAnimation(getContext(),R.anim.popup);
        resume=(Button)view.findViewById(R.id.resume);
        editpf=(Button)view.findViewById(R.id.editprofile);
        recommended=(Button)view.findViewById(R.id.recmnd);
        tc=(Button)view.findViewById(R.id.tC);
        policy=(Button)view.findViewById(R.id.privacy);
        signin=(Button)view.findViewById(R.id.Sign);
       // signout=(Button)view.findViewById(R.id.Signout);
       signin.setTag(1);
        signin.setText("SignIn");
 //       StartAnimations();
//        signin.setAnimation(animation1);
//        recommended.setAnimation(animation1);
//        resume.setAnimation(animation1);
//        editpf.setAnimation(animation1);
//        policy.setAnimation(animation1);
//        tc.setAnimation(animation1);
      //  signin.setVisibility(View.VISIBLE);
        signin.setOnClickListener(


                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // signin.setVisibility(View.GONE);

                final int status=(Integer)v.getTag();
                if(status == 1)
                {
                    Intent i=new Intent(getActivity(),Signin.class);
                    startActivity(i);

                    signin.setText("SignOut");
                    signin.setTag(0);

                }   else{     signin.setText("SignIn");
                    signin.setTag(1);
                }

                new JSONTask().execute();
         }
        });
        editpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),EditDetails.class);
            }
        });
     //   signout.setVisibility(View.VISIBLE);
        return view;
    }
//    private void StartAnimations() {
//        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.popup);
//        anim.reset();
//        signin.clearAnimation();
//        signin.startAnimation(anim);
//        anim = AnimationUtils.loadAnimation(getContext(), R.anim.popup);
//        anim.reset();
//        recommended.clearAnimation();
//        recommended.startAnimation(anim);
//        anim = AnimationUtils.loadAnimation(getContext(), R.anim.popup);
//        anim.reset();
//        resume.clearAnimation();
//        resume.startAnimation(anim);
//        anim = AnimationUtils.loadAnimation(getContext(), R.anim.popup);
//        anim.reset();
//        editpf.clearAnimation();
//        editpf.startAnimation(anim);
//        anim = AnimationUtils.loadAnimation(getContext(), R.anim.popup);
//        anim.reset();
//        policy.clearAnimation();
//        policy.startAnimation(anim);
//        anim = AnimationUtils.loadAnimation(getContext(), R.anim.popup);
//        anim.reset();
//        tc.clearAnimation();
//        tc.startAnimation(anim);
//
//    }
public class JSONTask extends AsyncTask<Void,Void,Void>{
    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {


        super.onPostExecute(aVoid);
    }
}
}
