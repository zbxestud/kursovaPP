package kn11sp.yaremechko.derivative;
import kn11sp.yaremechko.insurances.BaseInsurance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.apache.logging.log4j.*;
public class Derivative {

    private int id;
    private ArrayList<BaseInsurance> insurances;
    private Scanner scn = new Scanner(System.in);
    private static final Logger logger = LogManager.getRootLogger();
    //private static MailLogger errorLogger = new MailLogger();
    public Derivative(int id, ArrayList<BaseInsurance> insurances){
        this.id =id;
        this.insurances = insurances;
    }

    public double count() {
        double sum=0;
        System.out.println("List of insurances");
        for (int i = 0;i< insurances.size();i++){
            System.out.println((i+1)+" "+insurances.get(i));
            sum+=insurances.get(i).countPrice();
        }
        System.out.println(String.format("total price is %,.2f $", sum));
        logger.info("successful counting sum of list");
        return sum;
    }
    public String search(int levelOfRisk,double price ) {
        for (int i = 0; i<insurances.size(); i++){
            if(insurances.get(i).getLevelOfRisk()==levelOfRisk && insurances.get(i).countPrice()<price){
                System.out.println((i+1)+" "+insurances.get(i));
                logger.info("successful search in the list ");
                return insurances.get(i).toString();
            }
        }
        //errorLogger.logMail("unsuccessful search");
        return "No such insurance";
    }
    public ArrayList<BaseInsurance> sort() {
        for (int i = 0; i < insurances.size(); i++) {
            for (int j = 0; j < insurances.size() - i - 1; j++) {
                if (insurances.get(j).getLevelOfRisk() < insurances.get(j + 1).getLevelOfRisk()) {
                    Collections.swap(insurances, j, j + 1);
                }
            }
        }
        logger.info("successful sort of list ");
        return insurances;
    }

    public void setInsurances(ArrayList<BaseInsurance> insurances) {
        this.insurances = insurances;
    }

    @Override
    public String toString() {
        return "Derivative "+ id;
    }
}
