package uk.gov.dwp.uc.pairtest.discount;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import thirdparty.discount.Discount;

/**
 *
 * @author yusufakhond
 */
public class DiscountServiceImplTest {

    @Test
    public void testGetDiscountPercentage() {
        System.out.println("getDiscountPercentage");
        long accountId = 3;
        String discountCode = "ASBF";
        DiscountServiceImpl instance = new DiscountServiceImpl();
        
        Discount result = instance.getDiscountPercentage(accountId, discountCode);
        assertEquals(4.5, result.percentage());
    }
}
