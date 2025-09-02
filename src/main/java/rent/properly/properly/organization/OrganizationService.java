package rent.properly.properly.organization;

public interface OrganizationService {
    OrganizationDto createOrganization(OrganizationDto organizationDto);
    OrganizationResponse getAllOrganizations(int pageNo, int pageSize);
    OrganizationDto getOrganizationById(Long id);
    OrganizationDto updateOrganization(Long id, OrganizationDto organizationDto);
    void deleteOrganizationById(Long id);
}
