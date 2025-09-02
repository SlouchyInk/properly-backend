package rent.properly.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public OrganizationDto createOrganization(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        organization.setDisplayName(organizationDto.getDisplayName());
        organization.setLeases(organizationDto.getLeases());
        organization.setProperties(organizationDto.getProperties());
        organization.setTenants(organizationDto.getTenants());
        organization.setUsers(organizationDto.getUsers());
        organization.setType(organizationDto.getType());
        organization.setPlanTier(organizationDto.getPlanTier());

        Organization newOrganization = organizationRepository.save(organization);

        OrganizationDto organizationResponse = new OrganizationDto();
        organizationResponse.setId(newOrganization.getId());
        organizationResponse.setDisplayName(newOrganization.getDisplayName());
        organizationResponse.setLeases(newOrganization.getLeases());
        organizationResponse.setProperties(newOrganization.getProperties());
        organizationResponse.setTenants(newOrganization.getTenants());
        organizationResponse.setUsers(newOrganization.getUsers());
        organizationResponse.setType(newOrganization.getType());
        organizationResponse.setPlanTier(newOrganization.getPlanTier());

        return organizationResponse;
    }

    @Override
    public OrganizationResponse getAllOrganizations(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Organization> organizations = organizationRepository.findAll(pageable);
        List<Organization> organizationList = organizations.getContent();
        List<OrganizationDto> content = organizationList.stream().map(l -> mapToDto(l)).collect(Collectors.toList());

        OrganizationResponse organizationResponse = new OrganizationResponse();
        organizationResponse.setContent(content);
        organizationResponse.setPageNo(organizations.getNumber());
        organizationResponse.setPageSize(organizations.getSize());
        organizationResponse.setTotalPages(organizations.getTotalPages());
        organizationResponse.setTotalElements(organizations.getTotalElements());
        organizationResponse.setLast(organizations.isLast());

        return organizationResponse;
    }

    @Override
    public OrganizationDto getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException("organization not found with id " + id));
        return mapToDto(organization);
    }

    @Override
    public OrganizationDto updateOrganization(Long id, OrganizationDto organizationDto) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException("organization could not be updated"));
        organization.setDisplayName(organizationDto.getDisplayName());
        organization.setLeases(organizationDto.getLeases());
        organization.setProperties(organizationDto.getProperties());
        organization.setTenants(organizationDto.getTenants());
        organization.setUsers(organizationDto.getUsers());
        organization.setType(organizationDto.getType());
        organization.setPlanTier(organizationDto.getPlanTier());
        Organization updatedOrganization = organizationRepository.save(organization);
        return mapToDto(updatedOrganization);
    }

    @Override
    public void deleteOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException("organization could not be deleted"));
        organizationRepository.delete(organization);
    }

    private OrganizationDto mapToDto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(organization.getId());
        organizationDto.setDisplayName(organization.getDisplayName());
        organizationDto.setLeases(organization.getLeases());
        organizationDto.setProperties(organization.getProperties());
        organizationDto.setTenants(organization.getTenants());
        organizationDto.setUsers(organization.getUsers());
        organizationDto.setType(organization.getType());
        organizationDto.setPlanTier(organization.getPlanTier());
        return organizationDto;
    }

    private Organization mapToEntity(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        organization.setId(organizationDto.getId());
        organization.setDisplayName(organizationDto.getDisplayName());
        organization.setLeases(organizationDto.getLeases());
        organization.setProperties(organizationDto.getProperties());
        organization.setTenants(organizationDto.getTenants());
        organization.setUsers(organizationDto.getUsers());
        organization.setType(organizationDto.getType());
        organization.setPlanTier(organizationDto.getPlanTier());
        return organization;
    }

}
