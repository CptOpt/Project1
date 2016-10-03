package cogentdatasolutions.project1;

/**
 * Created by madhu on 01-Oct-16.
 */
public class JobDetails
{
    private String job_Title,company_name,posted_Date,skills,locations;
public JobDetails(String job_Title,String company_name,String posted_Date,String skills,String locations)
{
    this.setJob_Title(job_Title);
    this.setCompany_name(company_name);
    this.setPosted_Date(posted_Date);
    this.setSkills(skills);
    this.setLocations(locations);
}

    public String getJob_Title() {
        return job_Title;
    }

    public void setJob_Title(String job_Title) {
        this.job_Title = job_Title;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPosted_Date() {
        return posted_Date;
    }

    public void setPosted_Date(String posted_Date) {
        this.posted_Date = posted_Date;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }
}
