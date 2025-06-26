package com.example.properly.tenant;

import com.example.properly.lease.Lease;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/tenant")
class TenantController {
    private final TenantRepository tenantRepository;

    private TenantController(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Tenant> findById(@PathVariable Long requestedId) {
        Optional<Tenant> tenant = tenantRepository.findById(requestedId);
        if (tenant.isPresent()) {
            return ResponseEntity.ok(tenant.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<Void> createTenant(@RequestBody Tenant newTenantRequest, UriComponentsBuilder ucb) {
        Tenant savedTenant = tenantRepository.save(newTenantRequest);
        URI locationOfNewTenant = ucb
                .path("tenant/{id}")
                .buildAndExpand(savedTenant.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewTenant).build();
    }
    
    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> putTenant(@PathVariable Long requestedId, @RequestBody Tenant tenantUpdate) {
        Optional<Tenant> tenant = tenantRepository.findById(requestedId);
        if (tenant.isPresent()) {
            Tenant updatedTenant = new Tenant(tenant.get().getFirstName(), tenant.get().getLastName(), tenant.get().getEmail(), tenant.get().getPhone());
            tenantRepository.save(updatedTenant);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteTenant(@PathVariable Long id) {
        tenantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

     @GetMapping()
    private ResponseEntity<Iterable<Tenant>> findAll(Pageable pageable) {
        Page<Tenant> page = tenantRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.DESC,"lastName"))
                )
        );
        return ResponseEntity.ok(tenantRepository.findAll());
    }
}
