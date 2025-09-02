package rent.properly.properly.organization;

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
        organization.setFirstName(organizationDto.getFirstName());
        organization.setLastName(organizationDto.getLastName());
        organization.setEmail(organizationDto.getEmail());

        Organization newOrganization = organizationRepository.save(organization);

        OrganizationDto organizationResponse = new OrganizationDto();
        organizationResponse.setId(newOrganization.getId());
        organizationResponse.setFirstName(newOrganization.getFirstName());
        organizationResponse.setLastName(newOrganization.getLastName());
        organizationResponse.setEmail(newOrganization.getEmail());

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
        organization.setFirstName(organizationDto.getFirstName());
        organization.setLastName(organizationDto.getLastName());
        organization.setEmail(organizationDto.getEmail());
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
        organizationDto.setFirstName(organization.getFirstName());
        organizationDto.setLastName(organization.getLastName());
        organizationDto.setEmail(organization.getEmail());
        return organizationDto;
    }

    private Organization mapToEntity(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        organization.setId(organizationDto.getId());
        organization.setFirstName(organizationDto.getFirstName());
        organization.setLastName(organizationDto.getLastName());
        organization.setEmail(organizationDto.getEmail());
        return organization;
    }

}
