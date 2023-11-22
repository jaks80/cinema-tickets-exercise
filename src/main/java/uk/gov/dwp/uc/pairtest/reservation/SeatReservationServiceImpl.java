package uk.gov.dwp.uc.pairtest.reservation;

import java.util.logging.Level;
import java.util.logging.Logger;
import thirdparty.seatbooking.SeatReservationService;

/**
 *
 * @author yusufakhond
 */
public class SeatReservationServiceImpl implements SeatReservationService {

    protected static final Logger logger = Logger.getLogger(SeatReservationServiceImpl.class.getName());

    @Override
    public void reserveSeat(long accountId, int totalSeatsToAllocate) {
        logger.log(Level.INFO, "<<<<{0} seats reserved for accountId :{1}>>>>", new Object[]{totalSeatsToAllocate, accountId});
    }
}
