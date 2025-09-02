package rent.properly.security;

import lombok.Data;

@Data
public class RegisterTenantRequestDto {
    private String username;
    private String password;

    private Long organizationId;
}
