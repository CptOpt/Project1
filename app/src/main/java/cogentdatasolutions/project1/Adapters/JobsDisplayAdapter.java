package cogentdatasolutions.project1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cogentdatasolutions.project1.JobDetails;
import cogentdatasolutions.project1.R;

/**
 * Created by madhu on 01-Oct-16.
 */
public class JobsDisplayAdapter extends ArrayAdapter
{
   List list=new ArrayList();
    public JobsDisplayAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(JobDetails object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        ContactHolder contactHolder;
        if(row==null)
        {
            LayoutInflater inflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.row,parent,false);
            contactHolder=new ContactHolder();
            contactHolder.tx_jobTitle= (TextView) row.findViewById(R.id.jobtitletext);
            contactHolder.tx_compnyname=(TextView) row.findViewById(R.id.cmpanyName);
            contactHolder.tx_postddate=(TextView) row.findViewById(R.id.posteDate);
            contactHolder.tx_skills=(TextView) row.findViewById(R.id.skillsdisplay);
            contactHolder.tx_locations=(TextView) row.findViewById(R.id.loction);
            row.setTag(contactHolder);
        }
        else
        {
            contactHolder=(ContactHolder)row.getTag();
        }
        JobDetails details= (JobDetails) this.getItem(position);
        contactHolder.tx_jobTitle.setText(details.getJob_Title());
        contactHolder.tx_compnyname.setText(details.getCompany_name());
        contactHolder.tx_postddate.setText(details.getPosted_Date());
        contactHolder.tx_skills.setText(details.getSkills());
        contactHolder.tx_locations.setText(details.getLocations());
        return row;
    }
    static class ContactHolder
    {
        TextView tx_jobTitle,tx_compnyname,tx_postddate,tx_skills,tx_locations;
    }
}
