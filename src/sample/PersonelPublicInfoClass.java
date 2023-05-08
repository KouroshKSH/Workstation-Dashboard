package sample;

public class PersonelPublicInfoClass
{
    private String position, firstName, lastName;

    public PersonelPublicInfoClass(String position, String firstName, String lastName)
    {
        this.position = position;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPosition ()
    {
        return position;
    }

    public void setPosition (String position)
    {
        this.position = position;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }
}
