package rent.properly.properly.tenant;

public interface TenantService {
    TenantDto createTenant(TenantDto tenantDto);
    TenantResponse getAllTenants(int pageNo, int pageSize);
    TenantDto getTenantById(Long id);
    TenantDto updateTenant(Long id, TenantDto tenantDto);
    void deleteTenantById(Long id);
}
