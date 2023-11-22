package uk.gov.dwp.uc.pairtest.util;

import uk.gov.dwp.uc.pairtest.domain.TicketPurchaseRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

/**
 * Business logic class. Al validation goes here.
 *
 * @author yusufakhond
 */
public class PurchaseValidation {

    //TicketPurchaseRequest related constants for validation
    public static final String MAXIMUM_TICKET_ALLOWED_MESSAGE = "Only a maximum of 20 tickets that can be purchased at a time";
    public static final String HAS_AT_LEAST_ONE_ADULT_MESSAGE = "Child and Infant tickets cannot be purchased without purchasing an Adult ticket";
    public static final String INFANT_MORE_THAN_ADULT = "Infant tickets cannot be more than Adult tickets since Infants seat onAdult's lap";

    /**
     * Purchase validation method
     *
     * @param ticketPurchaseRequest
     */
    public static void validatePurchase(TicketPurchaseRequest ticketPurchaseRequest) {

        if (ticketPurchaseRequest.getAccountId() <= 0) {
            throw new InvalidPurchaseException(Constants.INVALID_ACCOUNT_ID);
        }

        if (ticketPurchaseRequest.getTotalTickets() > TicketPurchaseRequest.MAX_PURCHASE_LIMIT) {
            throw new InvalidPurchaseException(MAXIMUM_TICKET_ALLOWED_MESSAGE);
        }

        if (!ticketPurchaseRequest.hasAdult()) {
            throw new InvalidPurchaseException(HAS_AT_LEAST_ONE_ADULT_MESSAGE);
        }

        if (ticketPurchaseRequest.getTotalInfantTickets() > ticketPurchaseRequest.getTotalAdultTickets()) {
            throw new InvalidPurchaseException(INFANT_MORE_THAN_ADULT);
        }
    }
}
