package rent.properly.properly.Tenant;

import lombok.Data;

@Data
public class TenantDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
