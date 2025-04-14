package mk.finki.ukim.emt.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccommodationNotFoundException extends RuntimeException {
    public AccommodationNotFoundException(Long accommodationId) {
        super(String.format("Accommodation with id:%d not found.", accommodationId));
    }
}
