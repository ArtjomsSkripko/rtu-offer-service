package offer.exception

import spock.lang.Specification

class OfferNotFoundExceptionSpockTest extends Specification {

    void "test Offer not found exception"() {
        given: "error message to pass in exception"
        String msg = "No offer found in DB"

        when: "create exception using constructor"
        OfferNotFoundException result = new OfferNotFoundException(msg)

        then: "expect that created exception message is equal to passed message"
        result.message == msg
    }
}


