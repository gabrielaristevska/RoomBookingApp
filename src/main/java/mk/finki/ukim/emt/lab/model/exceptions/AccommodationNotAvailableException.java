package mk.finki.ukim.emt.lab.model.exceptions;

public class AccommodationNotAvailableException extends RuntimeException {
    public AccommodationNotAvailableException(Long accommodationId) {
        super(String.format("Accommodation with id:%d is not available!", accommodationId));
    }
}
