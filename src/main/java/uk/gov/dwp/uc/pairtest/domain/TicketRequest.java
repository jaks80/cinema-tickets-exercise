package uk.gov.dwp.uc.pairtest.domain;

/**
 * Should be an Immutable Object
 * 1 purchase related calculation method specific to this class added here.
 */
public final class TicketRequest {

    private final int noOfTickets;
    private final Type type;
    private final int INFANT_PRICE = 0;
    private final int CHILD_PRICE = 10;
    private final int ADULT_PRICE = 20;

    public TicketRequest(Type type, int noOfTickets) {
        this.type = type;
        this.noOfTickets = noOfTickets;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public Type getTicketType() {
        return type;
    }

    public enum Type {
        ADULT, CHILD, INFANT
    }

    public int getTotalAmount() {
        if (Type.ADULT.equals(type)) {
            return ADULT_PRICE * noOfTickets;
        } else if (Type.CHILD.equals(type)) {
            return CHILD_PRICE * noOfTickets;
        }

        return 0;
    }
}
