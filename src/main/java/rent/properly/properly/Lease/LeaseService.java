package rent.properly.properly.Lease;

import java.util.List;

public interface LeaseService {
    LeaseDto createLease(LeaseDto leaseDto);
    List<LeaseDto> getAllLeases();
}
