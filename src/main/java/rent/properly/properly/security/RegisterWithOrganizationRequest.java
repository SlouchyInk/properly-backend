package rent.properly.properly.security;

import lombok.Data;
import rent.properly.properly.organization.OrgType;
import rent.properly.properly.organization.PlanTier;

@Data
public class RegisterWithOrganizationRequest {
    private String username;
    private String password;

    private String organizationDisplayName;
    private OrgType organizationType;
    private PlanTier planTier;
}

