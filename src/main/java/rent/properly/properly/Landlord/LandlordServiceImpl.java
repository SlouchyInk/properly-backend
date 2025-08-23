package rent.properly.properly.Landlord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LandlordServiceImpl implements LandlordService {
    private LandlordRepository landlordRepository;

    @Autowired
    public LandlordServiceImpl(LandlordRepository landlordRepository) {
        this.landlordRepository = landlordRepository;
    }

    @Override
    public LandlordDto createLandlord(LandlordDto landlordDto) {
        Landlord landlord = new Landlord();
        landlord.setFirstName(landlordDto.getFirstName());
        landlord.setLastName(landlordDto.getLastName());
        landlord.setEmail(landlordDto.getEmail());

        Landlord newlandlord = landlordRepository.save(landlord);

        LandlordDto landlordResponse = new LandlordDto();
        landlordResponse.setId(newlandlord.getId());
        landlordResponse.setFirstName(newlandlord.getFirstName());
        landlordResponse.setLastName(newlandlord.getLastName());
        landlordResponse.setEmail(newlandlord.getEmail());

        return landlordResponse;
    }

    @Override
    public List<LandlordDto> getAllLandlords() {
        List<Landlord> landlords = landlordRepository.findAll();
        return landlords.stream().map(l -> mapToDto(l)).collect(Collectors.toList());
    }

    private LandlordDto mapToDto(Landlord landlord) {
        LandlordDto landlordDto = new LandlordDto();
        landlordDto.setId(landlord.getId());
        landlordDto.setFirstName(landlord.getFirstName());
        landlordDto.setLastName(landlord.getLastName());
        landlordDto.setEmail(landlord.getEmail());
        return landlordDto;
    }

    private Landlord mapToEntity(LandlordDto landlordDto) {
        Landlord landlord = new Landlord();
        landlord.setId(landlordDto.getId());
        landlord.setFirstName(landlordDto.getFirstName());
        landlord.setLastName(landlordDto.getLastName());
        landlord.setEmail(landlordDto.getEmail());
        return landlord;
    }
}
