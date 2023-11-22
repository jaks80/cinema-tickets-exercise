package uk.gov.dwp.uc.pairtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import thirdparty.discount.exception.InvalidDiscountCodeException;
import uk.gov.dwp.uc.pairtest.discount.DiscountServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketPurchaseRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.util.Constants;
import uk.gov.dwp.uc.pairtest.util.PurchaseValidation;

/**
 *
 * @author yusufakhond
 */
public class TicketServiceTest {

    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        ticketService = new TicketServiceImpl();
    }

    @Test
    public void testTicketPurchaseRequest() {

        TicketPurchaseRequest ticketPurchaseRequest = new TicketPurchaseRequestBuilder(1).setAdult(2).setChild(2).setInfant(1).build();
        ticketService.purchaseTickets(ticketPurchaseRequest);
    }

    @Test
    public void testMaximumPurchaseLimit() {

        TicketPurchaseRequest ticketPurchaseRequest = new TicketPurchaseRequestBuilder(1).setAdult(15).setChild(4).setInfant(3).build();

        InvalidPurchaseException thrown = Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            ticketService.purchaseTickets(ticketPurchaseRequest);
        });

        Assertions.assertEquals(PurchaseValidation.MAXIMUM_TICKET_ALLOWED_MESSAGE, thrown.getMessage());
    }

    @Test
    public void testHasAdultInGroup() {

        TicketPurchaseRequest ticketPurchaseRequest = new TicketPurchaseRequestBuilder(1).setChild(4).setInfant(3).build();

        InvalidPurchaseException thrown = Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            ticketService.purchaseTickets(ticketPurchaseRequest);
        });

        Assertions.assertEquals(PurchaseValidation.HAS_AT_LEAST_ONE_ADULT_MESSAGE, thrown.getMessage());
    }

    @Test
    public void testHasInfantMoreThanAdult() {
        System.out.println("infantMoreThanAdult");
        TicketPurchaseRequest ticketPurchaseRequest = new TicketPurchaseRequestBuilder(1).setAdult(2).setChild(3).setInfant(3).build();
        InvalidPurchaseException thrown = Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            ticketService.purchaseTickets(ticketPurchaseRequest);
        });

        Assertions.assertEquals(PurchaseValidation.INFANT_MORE_THAN_ADULT, thrown.getMessage());
    }

    @Test
    public void testInvalidDiscountCode() {
        System.out.println("InvalidDiscountCode");
        TicketPurchaseRequest ticketPurchaseRequest
                = new TicketPurchaseRequestBuilder(1)
                        .setAdult(2)
                        .setChild(3)
                        .setInfant(1)
                        .setDiscountCode("AASS")
                        .build();
        InvalidDiscountCodeException thrown = Assertions.assertThrows(InvalidDiscountCodeException.class, () -> {
            ticketService.purchaseTickets(ticketPurchaseRequest);
        });

        Assertions.assertEquals(DiscountServiceImpl.INVALID_DISCOUNT_CODE, thrown.getMessage());
    }

    @Test
    public void testInvalidAccountID() {
        System.out.println("InvalidAccountID");
        TicketPurchaseRequest ticketPurchaseRequest
                = new TicketPurchaseRequestBuilder(0)
                        .setAdult(2)
                        .setChild(3)
                        .setInfant(1)
                        .setDiscountCode("AASS")
                        .build();
        InvalidPurchaseException thrown = Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            ticketService.purchaseTickets(ticketPurchaseRequest);
        });

        Assertions.assertEquals(Constants.INVALID_ACCOUNT_ID, thrown.getMessage());
    }
}
