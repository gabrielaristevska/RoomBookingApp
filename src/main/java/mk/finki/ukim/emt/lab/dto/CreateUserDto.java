package mk.finki.ukim.emt.lab.dto;

import mk.finki.ukim.emt.lab.model.domain.User;
import mk.finki.ukim.emt.lab.model.enumerations.Role;

public record CreateUserDto(String username, String password, String repeatPassword, String name, String surname, Role role) {
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}
