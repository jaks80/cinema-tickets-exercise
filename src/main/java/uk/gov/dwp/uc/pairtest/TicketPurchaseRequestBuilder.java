package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketPurchaseRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketRequest;

/**
 * Purchase builder class to reduce purchase related object creation in many places and unit tests
 * @author yusufakhond
 */
public class TicketPurchaseRequestBuilder {

    private long accountId = 0;
    private int adult = 0;
    private int child = 0;
    private int infant = 0;
    private String discountCode;

    public TicketPurchaseRequestBuilder(long accountId) {
        this.accountId = accountId;
    }

    public TicketPurchaseRequestBuilder setAdult(int adult) {
        this.adult = adult;
        return this;
    }

    public TicketPurchaseRequestBuilder setChild(int child) {
        this.child = child;
        return this;
    }

    public TicketPurchaseRequestBuilder setInfant(int infant) {
        this.infant = infant;
        return this;
    }

    public TicketPurchaseRequestBuilder setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
        return this;
    }

    public TicketPurchaseRequest build() {
        TicketRequest adultTicketRequest = new TicketRequest(TicketRequest.Type.ADULT, adult);
        TicketRequest childTicketRequest = new TicketRequest(TicketRequest.Type.CHILD, child);
        TicketRequest infantTicketRequest = new TicketRequest(TicketRequest.Type.INFANT, infant);

        return new TicketPurchaseRequest(accountId, new TicketRequest[]{adultTicketRequest, childTicketRequest, infantTicketRequest}, discountCode);
    }
}
