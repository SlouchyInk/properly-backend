package rent.properly.properly.Lease;

import lombok.Data;
import rent.properly.properly.Landlord.Landlord;
import rent.properly.properly.Property.Property;
import rent.properly.properly.Tenant.Tenant;
import rent.properly.properly.Unit.Unit;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class LeaseDto {
    private Long id;
    private Tenant tenant;
    private Unit unit;
    private Landlord landlord;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal rentAmount;
}
