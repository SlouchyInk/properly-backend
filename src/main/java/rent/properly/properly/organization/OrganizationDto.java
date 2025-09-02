package rent.properly.properly.organization;

import lombok.Data;
import rent.properly.properly.lease.Lease;
import rent.properly.properly.property.Property;
import rent.properly.properly.security.UserEntity;
import rent.properly.properly.tenant.Tenant;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrganizationDto {
    private long id;
    private String displayName;
    private List<Property> properties = new ArrayList<>();
    private List<Lease> leases = new ArrayList<>();
    private List<UserEntity> users = new ArrayList<>();
    private List<Tenant> tenants = new ArrayList<>();
    private OrgType type;
    private PlanTier planTier;
}
