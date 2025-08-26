package rent.properly.properly.Lease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public LeaseResponse getAllLeases(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Lease> leases = leaseRepository.findAll(pageable);
        List<Lease> leaseList = leases.getContent();
        List<LeaseDto> content =leaseList.stream().map(l -> mapToDto(l)).collect(Collectors.toList());

        LeaseResponse leaseResponse = new LeaseResponse();
        leaseResponse.setContent(content);
        leaseResponse.setPageNo(leases.getNumber());
        leaseResponse.setPageSize(leases.getSize());
        leaseResponse.setTotalPages(leases.getTotalPages());
        leaseResponse.setTotalElements(leases.getTotalElements());
        leaseResponse.setLast(leases.isLast());

        return leaseResponse;
    }

    @Override
    public LeaseDto getLeaseById(Long id) {
        Lease lease = leaseRepository.findById(id)
                .orElseThrow(() -> new LeaseNotFoundException("Lease not found with id "+ id));
        return mapToDto(lease);

    }

    @Override
    public LeaseDto updateLease(Long id, LeaseDto leaseDto) {
        Lease lease = leaseRepository.findById(id)
                .orElseThrow(() -> new LeaseNotFoundException("Lease could not be updated"));
        lease.setLandlord(leaseDto.getLandlord());
        lease.setProperty(leaseDto.getProperty());
        lease.setTenant(leaseDto.getTenant());
        lease.setEndDate(leaseDto.getEndDate());
        lease.setStartDate(leaseDto.getStartDate());
        lease.setRentAmount(leaseDto.getRentAmount());

        Lease updatedLease = leaseRepository.save(lease);
        return mapToDto(updatedLease);
    }

    @Override
    public void deleteLeaseById(Long id) {
        Lease lease = leaseRepository.findById(id)
                .orElseThrow(() -> new LeaseNotFoundException("Lease could not be deleted"));
        leaseRepository.delete(lease);
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
