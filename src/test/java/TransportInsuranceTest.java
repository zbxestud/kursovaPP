import kn11sp.yaremechko.insurances.TransportInsurance;
import org.junit.Assert;
import org.junit.Test;

public class TransportInsuranceTest {
    TransportInsurance in1 = new TransportInsurance(1,1,1," ","A", " ");
    double expected1 =1897.5;
    TransportInsurance in2 = new TransportInsurance(1,1,1," ","B", " ");
    double expected2 = 1980;
    TransportInsurance in3 = new TransportInsurance(1,1,1," ","C", " ");
    double expected3 = 2062.5;
    TransportInsurance in4 = new TransportInsurance(1,1,1," ","D", " ");
    double expected4 = 2145;
    TransportInsurance in5 = new TransportInsurance(1,1,1," ","E", " ");
    double expected5 = 2227.5;
    TransportInsurance in6 = new TransportInsurance(1,1,1," ","F", " ");
    double expected6 = 2310;
    TransportInsurance in7 = new TransportInsurance(1,1,1," ","J", " ");
    double expected7 = 2392.5;
    TransportInsurance in8 = new TransportInsurance(1,1,1," ","M", " ");
    double expected8 = 2475;
    TransportInsurance in9 = new TransportInsurance(1,1,1," ","S", " ");
    double expected9 = 2722.5;
    @Test
    public void testCountA(){
        Assert.assertEquals(expected1,in1.countPrice(),0.01);
    }
    @Test
    public void testCountB(){
        Assert.assertEquals(expected2,in2.countPrice(),0.01);
    }
    @Test
    public void testCountC(){
        Assert.assertEquals(expected3,in3.countPrice(),0.01);
    }
    @Test
    public void testCountD(){
        Assert.assertEquals(expected4,in4.countPrice(),0.01);
    }
    @Test
    public void testCountE(){
        Assert.assertEquals(expected5,in5.countPrice(),0.01);
    }
    @Test
    public void testCountF(){
        Assert.assertEquals(expected6,in6.countPrice(),0.01);
    }
    @Test
    public void testCountJ(){
        Assert.assertEquals(expected7,in7.countPrice(),0.01);
    }
    @Test
    public void testCountM(){
        Assert.assertEquals(expected8,in8.countPrice(),0.01);
    }
    @Test
    public void testCountS(){
        Assert.assertEquals(expected9,in9.countPrice(),0.01);
    }


}
