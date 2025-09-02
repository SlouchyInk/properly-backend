package rent.properly.property;

import lombok.Data;

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
