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
    public LeaseController(LeaseRepository leaseRepository, LeaseService leaseService) {
        this.leaseRepository = leaseRepository;
        this.leaseService = leaseService;
    }

    @GetMapping()
    public ResponseEntity<LeaseResponse> getLeases(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(leaseService.getAllLeases(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LeaseDto> leaseDetail(@PathVariable Long id) {
        return ResponseEntity.ok(leaseService.getLeaseById(id));
    }

    @GetMapping("{landlordId}/leases")
    public ResponseEntity<List<LeaseDto>> getLeasesByLandlordId(@PathVariable(value = "landlordId") Long landlordId) {
        return ResponseEntity.ok(leaseService.getLeaseByLandLordId(landlordId));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LeaseDto> createLease(@RequestBody LeaseDto leaseDto) {
        return new ResponseEntity<>(leaseService.createLease(leaseDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<LeaseDto> updateLease(@PathVariable("id") Long leaseId, @RequestBody LeaseDto leaseDto) {
        LeaseDto response = leaseService.updateLease(leaseId, leaseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLease(@PathVariable Long id) {
        leaseService.deleteLeaseById(id);
        return new ResponseEntity<>("Lease deleted",HttpStatus.OK);
    }
}
