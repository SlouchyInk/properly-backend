package rent.properly.tenant;

import lombok.Data;

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
