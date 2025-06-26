package com.example.properly.lease;

import com.example.properly.property.Property;
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
@RequestMapping("/leases")
class LeaseController {
    private final LeaseRepository leaseRepository;

    private LeaseController(LeaseRepository leaseRepository) {
        this.leaseRepository = leaseRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Lease> findById(@PathVariable Long requestedId) {
        Optional<Lease> leaseOptional = leaseRepository.findById(requestedId);
        if (leaseOptional.isPresent()) {
            return ResponseEntity.ok(leaseOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<Void> createLease(@RequestBody Lease newLeaseRequest, UriComponentsBuilder ucb) {
        Lease savedLease = leaseRepository.save(newLeaseRequest);
        URI locationOfNewLease = ucb
                .path("leases/{id}")
                .buildAndExpand(savedLease.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewLease).build();
    }

    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> putProperty(@PathVariable Long requestedId, @RequestBody Lease leaseUpdate) {
        Optional<Lease> lease = leaseRepository.findById(requestedId);
        if (lease.isPresent()) {
            Lease updatedLease = new Lease(lease.get().getTenant(), lease.get().getProperty(), lease.get().getRentAmount(), lease.get().getStartDate(), lease.get().getEndDate(), lease.get().getLeaseStatus());
            leaseRepository.save(leaseUpdate);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteLease(@PathVariable Long id) {
        leaseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    private ResponseEntity<Iterable<Lease>> findAll(Pageable pageable) {
        Page<Lease> page = leaseRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.DESC,"status"))
                )
        );
        return ResponseEntity.ok(leaseRepository.findAll());
    }
}
