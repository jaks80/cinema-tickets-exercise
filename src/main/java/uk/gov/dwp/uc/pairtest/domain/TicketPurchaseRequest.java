package uk.gov.dwp.uc.pairtest.domain;

import uk.gov.dwp.uc.pairtest.domain.TicketRequest.Type;

/**
 * Should be an Immutable Object
 * Some purchase related calculation methods specific to this class added here.
 */
public final class TicketPurchaseRequest {

    private final long accountId;
    private final TicketRequest[] ticketRequests;
    private final String discountCode;

    public static final int MAX_PURCHASE_LIMIT = 20;

    public TicketPurchaseRequest(long accountId, TicketRequest[] ticketRequests, String discountCode) {
        this.accountId = accountId;
        this.ticketRequests = ticketRequests;
        this.discountCode = discountCode;
    }

    public long getAccountId() {
        return accountId;
    }

    public TicketRequest[] getTicketTypeRequests() {
        return ticketRequests;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * This is a domain class, so some TicketPurchaseRequest related
     * calculations are implemented bellow. For a complex scenario bellow
     * methods could be in a Business Logic class
     */
    public int getTotalAmount() {
        int totalAmount = 0;
        for (TicketRequest ticketRequest : ticketRequests) {
            totalAmount = totalAmount + ticketRequest.getTotalAmount();
        }
        return totalAmount;
    }

    public int getTotalTickets() {
        int totalNoOfTickets = 0;
        for (TicketRequest ticketRequest : ticketRequests) {
            totalNoOfTickets = totalNoOfTickets + ticketRequest.getNoOfTickets();
        }
        return totalNoOfTickets;
    }

    public int getTotalInfantTickets() {
        for (TicketRequest ticketRequest : ticketRequests) {
            if (ticketRequest.getTicketType().equals(Type.INFANT)) {
                return ticketRequest.getNoOfTickets();
            }
        }
        return 0;
    }

    public int getTotalAdultTickets() {
        for (TicketRequest ticketRequest : ticketRequests) {
            if (ticketRequest.getTicketType().equals(Type.ADULT)) {
                return ticketRequest.getNoOfTickets();
            }
        }
        return 0;
    }

    public int calculateTotalSeatsToBeAllocated() {
        return getTotalTickets() - getTotalInfantTickets();
    }

    public boolean hasAdult() {
        for (TicketRequest ticketRequest : ticketRequests) {
            if (TicketRequest.Type.ADULT.equals(ticketRequest.getTicketType()) && ticketRequest.getNoOfTickets() > 0) {
                return true;
            }
        }
        return false;
    }
}
