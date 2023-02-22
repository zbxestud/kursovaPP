package kn11sp.yaremechko.insurances;

import java.util.HashMap;
import java.util.Hashtable;

public class TransportInsurance extends BaseInsurance {
    private int id;
    private HashMap<String, Double> indexes = new HashMap<String, Double>();
    private int timeInYears;
    private int levelOfRisk;
    private String transportName;
    private String transportClass;
    private String transportNumber;
    protected final double payPerYear = 1500;
    @Override
    public double getPayPerYear() { return this.payPerYear; }
    @Override
    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }
    @Override
    public String getTransportClass() {
        return transportClass;
    }

    public void setTransportClass(String transportClass) {
        this.transportClass = transportClass;
    }
    @Override
    public String getTransportNumber() {
        return transportNumber;
    }

    public void setTransportNumber(String transportNumber) {
        this.transportNumber = transportNumber;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String, Double> getIndexes() {
        return indexes;
    }
    public void setIndexes(HashMap<String, Double> indexes) {
        this.indexes = indexes;
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

    public TransportInsurance(int id, int timeInYears, int levelOfRisk, String transportName, String transportClass, String transportNumber) {
        this.id = id;
        this.timeInYears = timeInYears;
        this.levelOfRisk = levelOfRisk;
        this.transportName = transportName;
        this.transportClass = transportClass;
        this.transportNumber = transportNumber;
        this.indexes.put("A", 1.15);
        this.indexes.put("B", 1.20);
        this.indexes.put("C", 1.25);
        this.indexes.put("D", 1.30);
        this.indexes.put("E", 1.35);
        this.indexes.put("F", 1.40);
        this.indexes.put("J", 1.45);
        this.indexes.put("M", 1.50);
        this.indexes.put("S", 1.65);
    }

    @Override
    public double countPrice(){
        double price = getPayPerYear()*getTimeInYears()*indexes.get(getTransportClass())*(1+(getLevelOfRisk()/10.0));
        return price;
    }

    @Override
    public String toString() {
        return String.format("Transport name "+getTransportName()+" number "+getTransportNumber()+" risk level "+getLevelOfRisk()+
                " with total cost %,.2f $", countPrice());
    }
}
