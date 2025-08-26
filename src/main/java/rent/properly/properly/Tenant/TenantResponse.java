package rent.properly.properly.Tenant;

import lombok.Data;
import rent.properly.properly.Landlord.LandlordDto;

import java.util.List;

@Data
public class TenantResponse {
    private List<TenantDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
