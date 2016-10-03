package cogentdatasolutions.project1.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cogentdatasolutions.project1.Activities.AddProject;
import cogentdatasolutions.project1.Activities.Feedback;
import cogentdatasolutions.project1.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Divya on 6/13/2016.
 */
public class Xtras extends Fragment {
    CircleImageView addreview,addsalary,addinterview,addproject,feedback,referfriend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xtras,container,false);

        addreview= (CircleImageView) view.findViewById(R.id.review);
        addsalary= (CircleImageView) view.findViewById(R.id.salary);
        addinterview= (CircleImageView) view.findViewById(R.id.interview);
        addproject= (CircleImageView) view.findViewById(R.id.pics);
        addproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),AddProject.class);
                startActivity(i);
            }
        });
        feedback= (CircleImageView) view.findViewById(R.id.feedback);
        referfriend= (CircleImageView) view.findViewById(R.id.referfriend);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Feedback.class);
                startActivity(i);
            }
        });
        return view;
    }
}
