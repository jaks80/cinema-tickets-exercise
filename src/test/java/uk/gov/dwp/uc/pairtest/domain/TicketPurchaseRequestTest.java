package uk.gov.dwp.uc.pairtest.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.gov.dwp.uc.pairtest.TicketPurchaseRequestBuilder;

/**
 *
 * @author yusufakhond
 */
public class TicketPurchaseRequestTest {

    @Test
    public void testGetTotalAmount() {

        System.out.println("getTotalAmount");
        TicketPurchaseRequest ticketPurchaseRequest = new TicketPurchaseRequestBuilder(1).setAdult(4).setChild(7).setInfant(2).build();
        int expResult = 150;
        int result = ticketPurchaseRequest.getTotalAmount();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTotalTickets() {
        System.out.println("getTotalTickets");
        TicketPurchaseRequest ticketPurchaseRequest = new TicketPurchaseRequestBuilder(1).setAdult(4).setChild(7).setInfant(2).build();
        int expResult = 13;
        int result = ticketPurchaseRequest.getTotalTickets();
        assertEquals(expResult, result);
    }

    @Test
    public void testHasAdult() {
        System.out.println("hasAdult");
        TicketPurchaseRequest ticketPurchaseRequest = new TicketPurchaseRequestBuilder(1).setAdult(4).setChild(7).setInfant(2).build();
        boolean expResult = true;
        boolean result = ticketPurchaseRequest.hasAdult();
        assertEquals(expResult, result);
    }

    @Test
    public void testHasNoAdult() {
        System.out.println("hasNoAdult");
        TicketPurchaseRequest ticketPurchaseRequest = new TicketPurchaseRequestBuilder(1).setChild(3).setInfant(1).build();
        boolean expResult = false;
        boolean result = ticketPurchaseRequest.hasAdult();
        assertEquals(expResult, result);
    }

    @Test
    public void testSeatReservation() {
        System.out.println("seatReservation");
        TicketPurchaseRequest ticketPurchaseRequest = new TicketPurchaseRequestBuilder(1).setAdult(2).setChild(3).setInfant(1).build();
        int expResult = 5;
        int result = ticketPurchaseRequest.calculateTotalSeatsToBeAllocated();
        assertEquals(expResult, result);
    }
}
