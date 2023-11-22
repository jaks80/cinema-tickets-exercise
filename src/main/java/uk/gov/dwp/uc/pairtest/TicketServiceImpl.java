package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.reservation.SeatReservationServiceImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import thirdparty.discount.DiscountService;
import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.discount.DiscountServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketPurchaseRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.util.PurchaseValidation;

public class TicketServiceImpl implements TicketService {

    protected static final Logger logger = Logger.getLogger(TicketServiceImpl.class.getName());

    /**
     * Should only have private methods other than the one below.
     */
    @Override
    public void purchaseTickets(TicketPurchaseRequest ticketPurchaseRequest) throws InvalidPurchaseException {

        PurchaseValidation.validatePurchase(ticketPurchaseRequest);

        int amountPayable = ticketPurchaseRequest.getTotalAmount();
        if (ticketPurchaseRequest.getDiscountCode() != null) {
            amountPayable = applyDiscount(ticketPurchaseRequest.getAccountId(), ticketPurchaseRequest.getDiscountCode(), ticketPurchaseRequest.getTotalAmount());
        }

        boolean paymentSuccess = makePayment(ticketPurchaseRequest.getAccountId(), amountPayable);

        if (paymentSuccess) {
            reserveSeat(ticketPurchaseRequest.getAccountId(), ticketPurchaseRequest.calculateTotalSeatsToBeAllocated());
        }
    }

    private int applyDiscount(long accountId, String discountCode, int totalAmountToPay) {
        if (discountCode == null) {
            return totalAmountToPay;
        }

        DiscountService discountService = new DiscountServiceImpl();
        double discountPercentage = discountService.getDiscountPercentage(accountId, discountCode).percentage();
        return totalAmountToPay - (int) (totalAmountToPay * discountPercentage) / 100;
    }

    private boolean makePayment(long accountId, int totalAmountToPay) {
        TicketPaymentService ticketPaymentService = new TicketPaymentServiceImpl();
        ticketPaymentService.makePayment(accountId, totalAmountToPay);
        logger.log(Level.INFO, "<<<<£{0} paid for accountId :{1}>>>>", new Object[]{totalAmountToPay, accountId});
        return true;
    }

    private int reserveSeat(long accountId, int totalSeatsToAllocate) {
        SeatReservationService seatReservationService = new SeatReservationServiceImpl();
        seatReservationService.reserveSeat(accountId, totalSeatsToAllocate);
        return totalSeatsToAllocate;
    }
}
