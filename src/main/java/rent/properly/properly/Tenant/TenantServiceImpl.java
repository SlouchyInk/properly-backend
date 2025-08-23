package rent.properly.properly.Tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TenantServiceImpl implements TenantService {
    private TenantRepository tenantRepository;

    @Autowired

    public TenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public TenantDto createTenant(TenantDto tenantDto) {
        Tenant tenant = new Tenant();
        tenant.setFirstName(tenantDto.getFirstName());
        tenant.setLastName(tenantDto.getLastName());
        tenant.setEmail(tenantDto.getEmail());
        tenant.setPhoneNumber(tenantDto.getPhoneNumber());

        Tenant newTenant = tenantRepository.save(tenant);

        TenantDto tenantResponse = new TenantDto();
        tenantResponse.setId(newTenant.getId());
        tenantResponse.setFirstName(newTenant.getFirstName());
        tenantResponse.setLastName(newTenant.getLastName());
        tenantResponse.setEmail(newTenant.getEmail());
        tenantResponse.setPhoneNumber(newTenant.getPhoneNumber());

        return tenantResponse;
    }

    @Override
    public List<TenantDto> getAllTenants() {
        List<Tenant> tenants = tenantRepository.findAll();
        return tenants.stream().map(t -> mapToDto(t)).collect(Collectors.toList());
    }

    private TenantDto mapToDto(Tenant tenant) {
        TenantDto tenantDto = new TenantDto();
        tenantDto.setId(tenant.getId());
        tenantDto.setFirstName(tenant.getFirstName());
        tenantDto.setLastName(tenant.getLastName());
        tenantDto.setEmail(tenant.getEmail());
        tenantDto.setPhoneNumber(tenant.getPhoneNumber());
        return tenantDto;
    }

    private Tenant mapToEntity(TenantDto tenantDto) {
        Tenant tenant = new Tenant();
        tenant.setId(tenantDto.getId());
        tenant.setFirstName(tenantDto.getFirstName());
        tenant.setLastName(tenantDto.getLastName());
        tenant.setEmail(tenantDto.getEmail());
        tenant.setPhoneNumber(tenantDto.getPhoneNumber());
        return tenant;
    }
}
