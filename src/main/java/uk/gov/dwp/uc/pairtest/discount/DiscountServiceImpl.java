package uk.gov.dwp.uc.pairtest.discount;

import uk.gov.dwp.uc.pairtest.util.Triple;
import java.util.ArrayList;
import java.util.List;
import thirdparty.discount.Discount;
import thirdparty.discount.DiscountService;
import thirdparty.discount.exception.InvalidDiscountCodeException;

/**
 * This class holds discount code data applicable to customer
 * For simplicity I have created a Tipple generic object for each discount code
 * mapped to customer id
 * @author yusufakhond
 */
public class DiscountServiceImpl implements DiscountService {

    private final List<Triple> discountCodes = new ArrayList<>();
    public static final String INVALID_DISCOUNT_CODE = "Invalid discount code";

    public DiscountServiceImpl() {
        //Some hard coded discount code. Idially they would come from DB
        discountCodes.add(new Triple(1, "ASBD", 3.5));
        discountCodes.add(new Triple(2, "ASBE", 4.5));
        discountCodes.add(new Triple(3, "ASBF", 4.5));
        discountCodes.add(new Triple(4, "ASBG", 10.0));

    }

    @Override
    public Discount getDiscountPercentage(long accountId, String discountCode) throws InvalidDiscountCodeException {
        Triple t = discountCodes.stream().filter(triple -> triple.getSecond().equals(discountCode)).findAny().orElse(null);

        if (t == null || Long.parseLong(String.valueOf(t.getFirst())) != accountId) {
            throw new InvalidDiscountCodeException(INVALID_DISCOUNT_CODE);
        }
        return new Discount((double) t.getThird());
    }
}
