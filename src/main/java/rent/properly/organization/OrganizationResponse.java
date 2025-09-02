package rent.properly.organization;

import lombok.Data;

import java.util.List;

@Data
public class OrganizationResponse {
    private List<OrganizationDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
