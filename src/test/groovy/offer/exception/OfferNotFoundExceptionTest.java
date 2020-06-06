package offer.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OfferNotFoundExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        String msg = "No offer found in DB";
        OfferNotFoundException offerNotFoundException = new OfferNotFoundException(msg);
        assertEquals(offerNotFoundException.getMessage(), msg);
    }
}
