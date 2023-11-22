package uk.gov.dwp.uc.pairtest.discount;

import thirdparty.discount.Discount;

/**
 *
 * @author yusufakhond
 */
public class DiscountCode {

    private long accountId;
    private String discountCode;
    private Discount discount;
    
    public DiscountCode(){
    
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
