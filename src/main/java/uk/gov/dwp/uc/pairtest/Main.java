package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketPurchaseRequest;

/**
 *
 * @author yusufakhond
 */
public class Main {
    public static void main(String[] args) {
        TicketPurchaseRequest ticketPurchaseRequest = 
                new TicketPurchaseRequestBuilder(4).setAdult(1).setChild(2).setInfant(1).setDiscountCode("ASBG").build();
        
        TicketService ticketService = new TicketServiceImpl();
        ticketService.purchaseTickets(ticketPurchaseRequest);
    }
    
}
