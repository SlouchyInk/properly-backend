package rent.properly.properly.Unit;

import lombok.Data;

import java.util.List;

@Data
public class UnitResponse {
    private List<UnitDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
