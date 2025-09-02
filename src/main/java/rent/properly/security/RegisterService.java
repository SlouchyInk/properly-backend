package rent.properly.security;

public interface RegisterService {
    void registerWithOrganization(RegisterWithOrganizationRequestDto request);
    void registerWithTenant(RegisterTenantRequestDto request);
}
