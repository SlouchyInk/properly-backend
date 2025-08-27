package rent.properly.properly.Lease;

import java.util.List;

public interface LeaseService {
    LeaseDto createLease(LeaseDto leaseDto);
    LeaseResponse getAllLeases(int pageNo, int pageSize);
    LeaseDto getLeaseById(Long id);
    List<LeaseDto> getLeaseByLandLordId(Long id);
    LeaseDto updateLease(Long id, LeaseDto leaseDto);
    void deleteLeaseById(Long id);
}
