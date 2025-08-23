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

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping()
    public ResponseEntity<List<TenantDto>> getTenants(HttpEntity<Object> httpEntity) {
        return new ResponseEntity<>(tenantService.getAllTenants(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public Tenant tenantDetails(@PathVariable Long id) {
        return new Tenant(1L,
                "Hayden",
                "Durham",
                "hayden.t.durham@gmail.com",
                "+1(901)232-4545"
        );
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TenantDto> createTenant(@RequestBody TenantDto tenantDto) {
        return new ResponseEntity<>(tenantService.createTenant(tenantDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable Long id, @RequestBody Tenant tenant) {
        return new ResponseEntity<>(tenant, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTenant(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
