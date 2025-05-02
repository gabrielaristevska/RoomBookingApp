package mk.finki.ukim.emt.lab.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.emt.lab.model.domain.Accommodation;
import mk.finki.ukim.emt.lab.model.domain.Country;
import mk.finki.ukim.emt.lab.model.domain.Host;
import mk.finki.ukim.emt.lab.model.domain.User;
import mk.finki.ukim.emt.lab.model.enumerations.Category;
import mk.finki.ukim.emt.lab.model.enumerations.Role;
import mk.finki.ukim.emt.lab.repository.AccommodationRepository;
import mk.finki.ukim.emt.lab.repository.CountryRepository;
import mk.finki.ukim.emt.lab.repository.HostRepository;
import mk.finki.ukim.emt.lab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, AccommodationRepository accommodationRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.accommodationRepository = accommodationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
    public void init(){
        Country country1=new Country("Macedonia","Europe");
        Country country2=new Country("Italy","Europe");
        Country country3=new Country("Japan","Asia");
        countryRepository.save(country1);
        countryRepository.save(country2);
        countryRepository.save(country3);
        Host host=new Host("Petre","Petrov",country1);
        hostRepository.save(host);
        hostRepository.save(new Host("Lucio","Raphael",country2));
        hostRepository.save(new Host("Cho","Tanaka",country3));
        accommodationRepository.save(new Accommodation("Hotel Karposh", Category.HOTEL,host,50));
        userRepository.save(new User(
                "host",
                passwordEncoder.encode("host"),
                "host",
                "host",
                Role.ROLE_HOST
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));

    }
}
