package rent.properly.properly.Tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    private TenantService tenantService;
    private TenantRepository tenantRepository;

    @Autowired
    public TenantController(TenantService tenantService, TenantRepository tenantRepository) {
        this.tenantService = tenantService;
        this.tenantRepository = tenantRepository;
    }

    @GetMapping()
    public ResponseEntity<TenantResponse> getTenants(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(tenantService.getAllTenants(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TenantDto> tenantDetails(@PathVariable Long id) {
        return ResponseEntity.ok(tenantService.getTenantById(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TenantDto> createTenant(@RequestBody TenantDto tenantDto) {
        return new ResponseEntity<>(tenantService.createTenant(tenantDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TenantDto> updateTenant(@PathVariable("id") Long tenantId, @RequestBody TenantDto tenantDto) {
        TenantDto repsonse = tenantService.updateTenant(tenantId, tenantDto);
        return new ResponseEntity<>(repsonse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenantById(id);
        return new ResponseEntity<>("Tenant deleted", HttpStatus.OK);
    }
}
