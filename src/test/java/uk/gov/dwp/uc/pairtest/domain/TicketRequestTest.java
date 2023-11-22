package uk.gov.dwp.uc.pairtest.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author yusufakhond
 */
public class TicketRequestTest {

    @Test
    public void testGetTotalAmountAdult() {
        TicketRequest adultTicketRequest = new TicketRequest(TicketRequest.Type.ADULT, 2);

        int expResult = 40;
        int result = adultTicketRequest.getTotalAmount();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTotalAmountChild() {
        TicketRequest adultTicketRequest = new TicketRequest(TicketRequest.Type.CHILD, 3);

        int expResult = 30;
        int result = adultTicketRequest.getTotalAmount();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTotalAmountAdultInfant() {
        TicketRequest adultTicketRequest = new TicketRequest(TicketRequest.Type.INFANT, 2);

        int expResult = 0;
        int result = adultTicketRequest.getTotalAmount();
        assertEquals(expResult, result);
    }

}
