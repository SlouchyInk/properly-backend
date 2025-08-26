package rent.properly.properly.Landlord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
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
    public LandlordResponse getAllLandlords(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Landlord> landlords = landlordRepository.findAll(pageable);
        List<Landlord> landlordList = landlords.getContent();
        List<LandlordDto> content = landlordList.stream().map(l -> mapToDto(l)).collect(Collectors.toList());

        LandlordResponse landlordResponse = new LandlordResponse();
        landlordResponse.setContent(content);
        landlordResponse.setPageNo(landlords.getNumber());
        landlordResponse.setPageSize(landlords.getSize());
        landlordResponse.setTotalPages(landlords.getTotalPages());
        landlordResponse.setTotalElements(landlords.getTotalElements());
        landlordResponse.setLast(landlords.isLast());

        return landlordResponse;
    }

    @Override
    public LandlordDto getLandlordById(Long id) {
        Landlord landlord = landlordRepository.findById(id)
                .orElseThrow(() -> new LandlordNotFoundException("Landlord not found with id " + id));
        return mapToDto(landlord);
    }

    @Override
    public LandlordDto updateLandlord(Long id, LandlordDto landlordDto) {
        Landlord landlord = landlordRepository.findById(id)
                .orElseThrow(() -> new LandlordNotFoundException("Landlord could not be updated"));
        landlord.setFirstName(landlordDto.getFirstName());
        landlord.setLastName(landlordDto.getLastName());
        landlord.setEmail(landlordDto.getEmail());
        Landlord updatedLandlord = landlordRepository.save(landlord);
        return mapToDto(updatedLandlord);


    }

    @Override
    public void deleteLandlordById(Long id) {
        Landlord landlord = landlordRepository.findById(id)
                .orElseThrow(() -> new LandlordNotFoundException("Landlord could not be deleted"));
        landlordRepository.delete(landlord);
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
