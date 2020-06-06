package offer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for cases when no offers found for given criteria.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No offer found")
public class OfferNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OfferNotFoundException(String message) {
        super(message);
    }
}
