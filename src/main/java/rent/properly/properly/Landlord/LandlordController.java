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

import java.util.List;

@RestController
@RequestMapping("/api/v1/landlord/")
public class LandlordController {

    private final LandlordRepository landlordRepository;
    private LandlordService landlordService;

    @Autowired
    public LandlordController(LandlordRepository landlordRepository, LandlordService landlordService) {
        this.landlordRepository = landlordRepository;
        this.landlordService = landlordService;
    }

    @GetMapping()
    public ResponseEntity<List<LandlordDto>> getLandlords() {
        return new ResponseEntity<>(landlordService.getAllLandlords(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Landlord> landlordDetail(@PathVariable Long id, HttpEntity<Object> httpEntity) {
        Landlord landlord = landlordRepository.findById(id)
                .orElseThrow(() -> new LandlordNotFoundException("Landlord not found with id " + id));
        return new ResponseEntity<>(landlord, HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LandlordDto> createLandlord(@RequestBody LandlordDto landlordDto) {
        return new ResponseEntity<>(landlordService.createLandlord(landlordDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Landlord> updateLandlord(@PathVariable Long id, @RequestBody Landlord landlord) {
        return new ResponseEntity<>(landlord, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLandlord(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
