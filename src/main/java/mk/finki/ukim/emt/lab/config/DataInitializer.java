package mk.finki.ukim.emt.lab.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.emt.lab.model.Country;
import mk.finki.ukim.emt.lab.model.Host;
import mk.finki.ukim.emt.lab.repository.CountryRepository;
import mk.finki.ukim.emt.lab.repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
    }

    @PostConstruct
    public void init(){
        Country country1=new Country("Macedonia","Europe");
        Country country2=new Country("Italy","Europe");
        Country country3=new Country("Japan","Asia");
        countryRepository.save(country1);
        countryRepository.save(country2);
        countryRepository.save(country3);
        hostRepository.save(new Host("Petre","Petrov",country1));
        hostRepository.save(new Host("Lucio","Raphael",country2));
        hostRepository.save(new Host("Cho","Tanaka",country3));
    }
}
