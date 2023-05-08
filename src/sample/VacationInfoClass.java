package sample;

public class VacationInfoClass
{
    private String position, username,
            firstname, lastname, jobID,
            paymentType, paymentValue, daysOff;

    public VacationInfoClass (String position, String username, String firstname, String lastname, String jobID, String paymentType, String paymentValue, String daysOff)
    {
        this.position = position;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.jobID = jobID;
        this.paymentType = paymentType;
        this.paymentValue = paymentValue;
        this.daysOff = daysOff;
    }

    public String getPosition ()
    {
        return position;
    }

    public void setPosition (String position)
    {
        this.position = position;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getFirstname ()
    {
        return firstname;
    }

    public void setFirstname (String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname ()
    {
        return lastname;
    }

    public void setLastname (String lastname)
    {
        this.lastname = lastname;
    }

    public String getJobID ()
    {
        return jobID;
    }

    public void setJobID (String jobID)
    {
        this.jobID = jobID;
    }

    public String getPaymentType ()
    {
        return paymentType;
    }

    public void setPaymentType (String paymentType)
    {
        this.paymentType = paymentType;
    }

    public String getPaymentValue ()
    {
        return paymentValue;
    }

    public void setPaymentValue (String paymentValue)
    {
        this.paymentValue = paymentValue;
    }

    public String getDaysOff ()
    {
        return daysOff;
    }

    public void setDaysOff (String daysOff)
    {
        this.daysOff = daysOff;
    }
}
