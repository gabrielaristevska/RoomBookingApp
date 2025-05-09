package mk.finki.ukim.emt.lab.dto;

import mk.finki.ukim.emt.lab.model.domain.User;
import mk.finki.ukim.emt.lab.model.enumerations.Role;

public record DisplayUserDto(String username, String name, String surname, Role role) {
    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

}
