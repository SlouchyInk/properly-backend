package com.example.properly.property;

import com.example.properly.lease.Lease;
import com.example.properly.tenant.Tenant;
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
@RequestMapping("/property")
class PropertyController {
    private final PropertyRepository propertyRepository;

    private PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Property> findById(@PathVariable Long requestedId) {
        Optional<Property> propertyOptional = propertyRepository.findById(requestedId);
        if (propertyOptional.isPresent()) {
            return ResponseEntity.ok(propertyOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    private ResponseEntity<Void> createProperty(@RequestBody Property newPropertyRequest, UriComponentsBuilder ucb) {
        Property savedProperty = propertyRepository.save(newPropertyRequest);
        URI locationOfNewProperty = ucb
                .path("property/{id}")
                .buildAndExpand(savedProperty.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewProperty).build();
    }

    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> putProperty(@PathVariable Long requestedId, @RequestBody Property propertyUpdate) {
        Optional<Property> property = propertyRepository.findById(requestedId);
        if (property.isPresent()) {
            Property updatedProperty = new Property(property.get().getAddress(), property.get().getUnitNumber());
            propertyRepository.save(propertyUpdate);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    private ResponseEntity<Iterable<Property>> findAll(Pageable pageable) {
        Page<Property> page = propertyRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.DESC,"address"))
                )
        );
        return ResponseEntity.ok(propertyRepository.findAll());
    }
}
