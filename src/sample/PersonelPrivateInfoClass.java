package sample;

public class PersonelPrivateInfoClass
{
    private String position, username,
            firstname, lastname, password,
            personalID, jobID,
            paymentType, paymentValue,
            birthdate, degree;

    public PersonelPrivateInfoClass (String position, String username,
                                     String firstname, String lastname, String password,
                                     String personalID, String jobID,
                                     String paymentType, String paymentValue,
                                     String birthdate, String degree)
    {
        this.position     = position;
        this.username     = username;
        this.firstname    = firstname;
        this.lastname     = lastname;
        this.password     = password;
        this.personalID   = personalID;
        this.jobID        = jobID;
        this.paymentType  = paymentType;
        this.paymentValue = paymentValue;
        this.birthdate    = birthdate;
        this.degree       = degree;
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

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getPersonalID ()
    {
        return personalID;
    }

    public void setPersonalID (String personalID)
    {
        this.personalID = personalID;
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

    public String getBirthdate ()
    {
        return birthdate;
    }

    public void setBirthdate (String birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getDegree ()
    {
        return degree;
    }

    public void setDegree (String degree)
    {
        this.degree = degree;
    }
}
