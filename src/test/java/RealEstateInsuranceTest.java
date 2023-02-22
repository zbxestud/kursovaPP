import kn11sp.yaremechko.insurances.RealEstateInsurance;
import org.junit.Assert;
import org.junit.Test;

public class RealEstateInsuranceTest {
    RealEstateInsurance in1 = new RealEstateInsurance(1, 1,1," ", " ", 3);
    @Test
    public void testCount(){
        double expected = 8250;
        Assert.assertEquals(expected, in1.countPrice(),0.01);
    }
}
