import kn11sp.yaremechko.insurances.HealthInsurance;
import org.junit.*;
import org.junit.Assert;
public class HealthInsuranceTest {
    HealthInsurance in1 = new HealthInsurance(1,1,1," ");
    @Test
    public void testCount(){
        double expected = 2200;
        Assert.assertEquals(expected, in1.countPrice(), 0.01);
    }
}
