package mk.finki.ukim.emt.lab.model.exceptions;

public class AccommodationAlreadyInListException extends RuntimeException {
    public AccommodationAlreadyInListException(String username, Long accommodationId) {
        super(String.format("Accommodation with id:%d already in list for user with username:%s.", accommodationId, username));
    }
}
