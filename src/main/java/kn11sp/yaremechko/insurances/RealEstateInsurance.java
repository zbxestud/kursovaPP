package kn11sp.yaremechko.insurances;

public class RealEstateInsurance extends BaseInsurance{
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

    private int id;
    private int timeInYears;
    private int levelOfRisk;
    private double area;
    protected final double payPerYear = 2500;
    private String city;
    private String address;
    @Override
    public double getArea() {
        return area;
    }
    public void setArea(double area) {
        this.area = area;
    }
    @Override
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public double getPayPerYear() { return this.payPerYear; }
    public RealEstateInsurance(int id,int timeInYears, int levelOfRisk, String city, String address, double area) {
        this.id = id;
        this.timeInYears = timeInYears;
        this.levelOfRisk = levelOfRisk;
        this.city = city;
        this.address = address;
        this.area = area;
    }

    @Override
    public double countPrice(){
        double price = getPayPerYear()*getTimeInYears()*(1+(getLevelOfRisk()/10.0))*getArea();
        return price;
    }

    @Override
    public String toString() {
        return String.format("City: "+getCity()+" address: "+ getAddress()+" risk level - " + getLevelOfRisk()+" insurance time: "+
                getTimeInYears()+" total cost %,.2f $", countPrice());
    }
}
