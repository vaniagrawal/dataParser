public class Country {
    private String name;
    private int fips;
    private Education2016 vote2016;
    private Education2016 educ2016;
    private Employment2016 employ2016;

    public Country(String name, int fips, Education2016 vote2016, Education2016 educ2016, Employment2016 employ2016) {
        this.name = name;
        this.fips = fips;
        this.vote2016 = vote2016;
        this.educ2016 = educ2016;
        this.employ2016 = employ2016;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFips() {
        return fips;
    }

    public void setFips(int fips) {
        this.fips = fips;
    }

    public Education2016 getVote2016() {
        return vote2016;
    }

    public void setVote2016(Education2016 vote2016) {
        this.vote2016 = vote2016;
    }

    public Education2016 getEduc2016() {
        return educ2016;
    }

    public void setEduc2016(Education2016 educ2016) {
        this.educ2016 = educ2016;
    }

    public Employment2016 getEmploy2016() {
        return employ2016;
    }

    public void setEmploy2016(Employment2016 employ2016) {
        this.employ2016 = employ2016;
    }
}
