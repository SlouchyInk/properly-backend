package rent.properly.lease;

import lombok.Data;
import rent.properly.organization.Organization;
import rent.properly.tenant.Tenant;
import rent.properly.unit.Unit;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LeaseDto {
    private Long id;
    private Tenant tenant;
    private Unit unit;
    private Organization organization;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal rentAmount;
}
