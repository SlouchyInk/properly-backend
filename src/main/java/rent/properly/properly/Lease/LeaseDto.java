package rent.properly.properly.Lease;

import lombok.Data;
import rent.properly.properly.Landlord.Landlord;
import rent.properly.properly.Property.Property;
import rent.properly.properly.Tenant.Tenant;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data

public class LeaseDto {
    private Long id;
    private Tenant tenant;
    private Property property;
    private Landlord landlord;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal rentAmount;
}
