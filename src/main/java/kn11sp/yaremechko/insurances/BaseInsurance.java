package kn11sp.yaremechko.insurances;

public abstract class BaseInsurance {
    protected int id;
    protected int timeInYears;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected int levelOfRisk;
    protected final double payPerYear = 1;

    public double getPayPerYear() { return this.payPerYear; }

    public int getTimeInYears() {
        return timeInYears;
    }

    public void setTimeInYears(int timeInYears) {
        this.timeInYears = timeInYears;
    }

    public int getLevelOfRisk() {
        return levelOfRisk;
    }

    public void setLevelOfRisk(int levelOfRisk) {
        this.levelOfRisk = levelOfRisk;
    }

    public BaseInsurance() {
    }

    public double countPrice(){
        double price = getPayPerYear()*getTimeInYears()*(1+(getLevelOfRisk()/10.0));
        return price;
    }

    @Override
    public String toString() {
        return "BaseInsurance{" +
                "timeInYears=" + timeInYears +
                ", levelOfRisk=" + levelOfRisk +
                ", payPerYear=" + payPerYear +
                '}';
    }

    public String getPersonName() {return ""; }
    public void setPersonName(String personName) {}
    public String getTransportName() {return ""; }
    public void setTransportName(String transportName){}
    public String getTransportClass() {
        return "";
    }
    public void setTransportClass(String transportClass){}
    public String getTransportNumber() {
        return "";
    }
    public void setTransportNumber(String transportNumber){}
    public double getArea(){return 0;};
    public void setArea(double area) {}
    public String getCity() {
        return "";
    }
    public void setCity(String city){}
    public String getAddress() {
        return "";
    }
    public void setAddress(String address){}
}
