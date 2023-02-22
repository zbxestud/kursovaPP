import kn11sp.yaremechko.insurances.BaseInsurance;
import kn11sp.yaremechko.derivative.Derivative;
import kn11sp.yaremechko.insurances.HealthInsurance;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DerivativeTest {
    BaseInsurance in1 = new HealthInsurance(1,1,1,"");
    BaseInsurance in2 = new HealthInsurance(1,1,2,"");
    BaseInsurance in3 = new HealthInsurance(1,1,3,"");
    BaseInsurance in4 = new HealthInsurance(1,1,4,"");
    BaseInsurance in5 = new HealthInsurance(1,1,5,"");
    BaseInsurance in6 = new HealthInsurance(1,1,6,"");
    BaseInsurance in7 = new HealthInsurance(1,1,7,"");
    BaseInsurance in8 = new HealthInsurance(1,1,8,"");
    BaseInsurance in9 = new HealthInsurance(1,1,9,"");
    BaseInsurance in10 = new HealthInsurance(1,1,10,"");
    ArrayList<BaseInsurance> insurancesVector = new ArrayList<>();
    ArrayList<BaseInsurance> sortedInsurancesVector = new ArrayList<>();

    BaseInsurance[] insurances = new BaseInsurance[]{in10,in9,in6,in7,in3,in2,in4,in5,in1,in8};
    BaseInsurance[] sortedInsurances = new BaseInsurance[]{in1,in2,in3,in4,in5,in6,in7,in8,in9,in10};
    double expectedSum=15.5;
    /*
    int searchRiskFactor = 4;
    double searchPrice = 1.4;
     */
    Derivative derivative;
    public DerivativeTest(){
        insurancesVector.addAll(List.of(insurances));
        sortedInsurancesVector.addAll(List.of(sortedInsurances));
        derivative = new Derivative(1,insurancesVector);
    }
    @Test
    public void menuSortTest(){
        Assert.assertArrayEquals(sortedInsurancesVector.toArray(),derivative.sort().toArray());
    }
    @Test
    public void menuCountTest(){
        Assert.assertEquals(expectedSum, derivative.count(),0.01);
    }
 /*
    @Test
    public void menuSearchTest(){
        Assert.assertEquals(in4,menu.search());
    }
 */
}
