package rent.properly.properly.Lease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent.properly.properly.Landlord.Landlord;
import rent.properly.properly.Property.Property;
import rent.properly.properly.Tenant.Tenant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lease/")
public class LeaseController {

    private final LeaseRepository leaseRepository;
    private LeaseService leaseService;

    @Autowired
    public LeaseController(LeaseService leaseService, LeaseRepository leaseRepository) {
        this.leaseService = leaseService;
        this.leaseRepository = leaseRepository;
    }

    @GetMapping()
    public ResponseEntity<List<LeaseDto>> getLease() {
        return new ResponseEntity<>(leaseService.getAllLeases(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Lease> leaseDetail(@PathVariable Long id, HttpEntity<Object> httpEntity) {
        Lease lease = leaseRepository.findById(id)
                .orElseThrow(() -> new LeaseNotFoundException("Lease not found with id "+ id));
        return new ResponseEntity<>(lease, HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LeaseDto> createLease(@RequestBody LeaseDto leaseDto) {
        return new ResponseEntity<>(leaseService.createLease(leaseDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Lease> updateLease(@PathVariable Long id, @RequestBody Lease lease) {
        return new ResponseEntity<>(lease, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLease(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
