package rent.properly.properly.Landlord;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent.properly.properly.Lease.Lease;
import rent.properly.properly.Lease.LeaseNotFoundException;
import rent.properly.properly.Lease.LeaseRepository;
import rent.properly.properly.Lease.LeaseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/landlord/")
public class LandlordController {

    private final LandlordRepository landlordRepository;
    private final LeaseService leaseService;
    private LandlordService landlordService;

    @Autowired
    public LandlordController(LandlordRepository landlordRepository, LandlordService landlordService, LeaseService leaseService) {
        this.landlordRepository = landlordRepository;
        this.landlordService = landlordService;
        this.leaseService = leaseService;
    }

    @GetMapping()
    public ResponseEntity<LandlordResponse> getLandlords(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(landlordService.getAllLandlords(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LandlordDto> landlordDetail(@PathVariable Long id) {
        return ResponseEntity.ok(landlordService.getLandlordById(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LandlordDto> createLandlord(@RequestBody LandlordDto landlordDto) {
        return new ResponseEntity<>(landlordService.createLandlord(landlordDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<LandlordDto> updateLandlord(@PathVariable("id") Long landlordId, @RequestBody LandlordDto landlordDto) {
        LandlordDto response = landlordService.updateLandlord(landlordId, landlordDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLandlord(@PathVariable Long id) {
        landlordService.deleteLandlordById(id);
        return new ResponseEntity<>("Landlord delete", HttpStatus.OK);
    }
}
