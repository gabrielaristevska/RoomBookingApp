package mk.finki.ukim.emt.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationsListNotFoundException extends RuntimeException{
    public ReservationsListNotFoundException(Long reservationsListId){
        super(String.format("List with id:%d not found.",reservationsListId));
    }

    public ReservationsListNotFoundException(String username){
        super(String.format("List for user with username:%s not found.",username));
    }
}
