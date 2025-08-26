package rent.properly.properly.Lease;

import lombok.Data;
import rent.properly.properly.Landlord.LandlordDto;

import java.util.List;

@Data
public class LeaseResponse {
    private List<LeaseDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
