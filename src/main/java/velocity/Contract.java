package velocity;

public class Contract
{
    private String company;
    private String contract;
    private String country;
    private Company companyDetails = new Company( "1", "2" );

    public Contract()
    {
    }

    public Contract( String company,
        String contract,
        String country )
    {
        this.company = company;
        this.contract = contract;
        this.country = country;
    }

    public String getCompany()
    {
        return company;
    }

    public void setCompany( String company )
    {
        this.company = company;
    }

    public String getContract()
    {
        return contract;
    }

    public void setContract( String contract )
    {
        this.contract = contract;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry( String country )
    {
        this.country = country;
    }

    public Company getCompanyDetails()
    {
        return companyDetails;
    }

    public void setCompanyDetails( Company companyDetails )
    {
        this.companyDetails = companyDetails;
    }

}
