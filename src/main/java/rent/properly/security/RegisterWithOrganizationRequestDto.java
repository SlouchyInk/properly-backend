package rent.properly.security;

import lombok.Data;
import rent.properly.organization.OrgType;
import rent.properly.organization.PlanTier;

@Data
public class RegisterWithOrganizationRequestDto {
    private String username;
    private String password;

    private String organizationDisplayName;
    private OrgType organizationType;
    private PlanTier planTier;
}

