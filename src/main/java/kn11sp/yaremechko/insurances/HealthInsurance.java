package kn11sp.yaremechko.insurances;

public class HealthInsurance extends BaseInsurance{
    private int id;
    private int timeInYears;
    private int levelOfRisk;
    private String personName;
    protected final double payPerYear = 2000;



    @Override
    public String getPersonName() {
        return personName;
    }
    @Override
    public double getPayPerYear() { return this.payPerYear; }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getTimeInYears() {
        return timeInYears;
    }

    @Override
    public void setTimeInYears(int timeInYears) {
        this.timeInYears = timeInYears;
    }

    @Override
    public int getLevelOfRisk() {
        return levelOfRisk;
    }

    @Override
    public void setLevelOfRisk(int levelOfRisk) {
        this.levelOfRisk = levelOfRisk;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }


    public HealthInsurance(int id, int timeInYears, int levelOfRisk, String personName) {
        this.id = id;
        this.timeInYears = timeInYears;
        this.levelOfRisk = levelOfRisk;
        this.personName = personName;
    }

    @Override
    public String toString() {
        return String.format("Person name: "+getPersonName()+" risk level "+getLevelOfRisk()+
                " total cost %,.2f $", countPrice());
    }
}
