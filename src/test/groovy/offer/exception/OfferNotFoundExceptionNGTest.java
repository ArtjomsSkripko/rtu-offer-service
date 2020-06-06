package offer.exception;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class OfferNotFoundExceptionNGTest {

    @Test
    public void testConstructorWithMessage() {
        String msg = "No offer found in DB";
        OfferNotFoundException offerNotFoundException = new OfferNotFoundException(msg);
        assertEquals(offerNotFoundException.getMessage(), msg);
    }
}
