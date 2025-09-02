package rent.properly.properly.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public TenantResponse getAllTenants(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Tenant> tenants = tenantRepository.findAll(pageable);
        List<Tenant> tenantList = tenants.getContent();
        List<TenantDto> content = tenantList.stream().map(t -> mapToDto(t)).collect(Collectors.toList());

        TenantResponse tenantResponse = new TenantResponse();
        tenantResponse.setContent(content);
        tenantResponse.setPageNo(tenants.getNumber());
        tenantResponse.setPageSize(tenants.getSize());
        tenantResponse.setTotalPages(tenants.getTotalPages());
        tenantResponse.setTotalElements(tenants.getTotalElements());
        tenantResponse.setLast(tenants.isLast());
        return tenantResponse;
    }

    @Override
    public TenantDto getTenantById(Long id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException("tenant not found with id " + id));
        return mapToDto(tenant);
    }

    @Override
    public TenantDto updateTenant(Long id, TenantDto tenantDto) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException("tenant could not be updated"));
        tenant.setFirstName(tenantDto.getFirstName());
        tenant.setLastName(tenantDto.getLastName());
        tenant.setEmail(tenantDto.getEmail());
        tenant.setPhoneNumber(tenantDto.getPhoneNumber());
        Tenant updatedTenant = tenantRepository.save(tenant);
        return mapToDto(updatedTenant);
    }

    @Override
    public void deleteTenantById(Long id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException("tenant could not be deleted"));
        tenantRepository.delete(tenant);
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
