package rent.properly.properly.Landlord;

import lombok.Data;

import java.util.List;

@Data
public class LandlordResponse {
    private List<LandlordDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
