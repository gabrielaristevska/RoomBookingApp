package mk.finki.ukim.emt.lab.model.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ReservationsList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    private List<Accommodation> reservations;

    public ReservationsList() {
    }

    public ReservationsList(User user) {
        this.user = user;
        this.dateCreated=LocalDateTime.now();
        this.reservations=new ArrayList<>();
    }

    public void setReservations(List<Accommodation> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public User getUser() {
        return user;
    }

    public List<Accommodation> getReservations() {
        return reservations;
    }
}
