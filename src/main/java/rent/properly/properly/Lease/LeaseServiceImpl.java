package rent.properly.properly.Lease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaseServiceImpl implements LeaseService {
    private LeaseRepository leaseRepository;

    @Autowired
    public LeaseServiceImpl(LeaseRepository leaseRepository) {
        this.leaseRepository = leaseRepository;
    }

    @Override
    public LeaseDto createLease(LeaseDto leaseDto) {
        Lease lease = new Lease();
        lease.setLandlord(leaseDto.getLandlord());
        lease.setProperty(leaseDto.getProperty());
        lease.setTenant(leaseDto.getTenant());
        lease.setEndDate(leaseDto.getEndDate());
        lease.setStartDate(leaseDto.getStartDate());
        lease.setRentAmount(leaseDto.getRentAmount());

        Lease newlease = leaseRepository.save(lease);

        LeaseDto leaseResponse = new LeaseDto();
        leaseResponse.setId(newlease.getId());
        leaseResponse.setLandlord(newlease.getLandlord());
        leaseResponse.setProperty(newlease.getProperty());
        leaseResponse.setTenant(newlease.getTenant());
        leaseResponse.setEndDate(newlease.getEndDate());
        leaseResponse.setStartDate(newlease.getStartDate());
        leaseResponse.setRentAmount(newlease.getRentAmount());

        return leaseResponse;
    }

    @Override
    public List<LeaseDto> getAllLeases() {
        List<Lease> leases = leaseRepository.findAll();
        return leases.stream().map(l -> mapToDto(l)).collect(Collectors.toList());
    }

    private LeaseDto mapToDto(Lease lease) {
        LeaseDto leaseDto = new LeaseDto();
        leaseDto.setId(lease.getId());
        leaseDto.setLandlord(lease.getLandlord());
        leaseDto.setProperty(lease.getProperty());
        leaseDto.setTenant(lease.getTenant());
        leaseDto.setEndDate(lease.getEndDate());
        leaseDto.setStartDate(lease.getStartDate());
        leaseDto.setRentAmount(lease.getRentAmount());
        return leaseDto;
    }

    private Lease mapToEntity(LeaseDto leaseDto) {
        Lease lease = new Lease();
        lease.setLandlord(leaseDto.getLandlord());
        lease.setProperty(leaseDto.getProperty());
        lease.setTenant(leaseDto.getTenant());
        lease.setEndDate(leaseDto.getEndDate());
        lease.setStartDate(leaseDto.getStartDate());
        lease.setRentAmount(leaseDto.getRentAmount());
        return lease;
    }
}
