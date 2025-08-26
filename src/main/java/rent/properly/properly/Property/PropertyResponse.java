package rent.properly.properly.Property;

import lombok.Data;
import rent.properly.properly.Lease.LeaseDto;

import java.util.List;

@Data
public class PropertyResponse {
    private List<PropertyDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
