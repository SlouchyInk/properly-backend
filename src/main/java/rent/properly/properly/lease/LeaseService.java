package rent.properly.properly.lease;

import java.util.List;

public interface LeaseService {
    LeaseDto createLease(LeaseDto leaseDto);
    LeaseResponse getAllLeases(int pageNo, int pageSize);
    LeaseDto getLeaseById(Long id);
    List<LeaseDto> getLeaseByOrganizationId(Long id);
    LeaseDto updateLease(Long id, LeaseDto leaseDto);
    void deleteLeaseById(Long id);
}
