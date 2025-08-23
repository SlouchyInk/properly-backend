package rent.properly.properly.Landlord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/landlord/")
public class LandlordController {

    private LandlordService landlordService;

    @Autowired
    public LandlordController(LandlordService landlordService) {
        this.landlordService = landlordService;
    }

    @GetMapping()
    public ResponseEntity<List<LandlordDto>> getLandlords() {
        return new ResponseEntity<>(landlordService.getAllLandlords(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public Landlord landlordDetail(@PathVariable Long id, HttpEntity<Object> httpEntity) {
       return new Landlord(
               1L,
               "John",
               "Doe",
               "example@example.com"
       );
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
