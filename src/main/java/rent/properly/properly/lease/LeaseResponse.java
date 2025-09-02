package rent.properly.properly.lease;

import lombok.Data;

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
