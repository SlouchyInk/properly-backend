package rent.properly.properly.Tenant;

import java.util.List;

public interface TenantService {
    TenantDto createTenant(TenantDto tenantDto);
    List<TenantDto> getAllTenants();
}
