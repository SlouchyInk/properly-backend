package rent.properly.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/org")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping()
    public ResponseEntity<OrganizationResponse> getOrganizations(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(organizationService.getAllOrganizations(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrganizationDto> organizationDetail(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getOrganizationById(id));
    }

    @PostMapping()
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody OrganizationDto organizationDto) {
        return new ResponseEntity<>(organizationService.createOrganization(organizationDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable("id") Long organizationId, @RequestBody OrganizationDto organizationDto) {
        OrganizationDto response = organizationService.updateOrganization(organizationId, organizationDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganizationById(id);
        return new ResponseEntity<>("organization delete", HttpStatus.OK);
    }
}
