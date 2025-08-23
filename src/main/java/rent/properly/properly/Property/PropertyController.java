package rent.properly.properly.Property;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/property/")
public class PropertyController {

    private PropertyService propertyService;

    private final PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping()
    public ResponseEntity<List<PropertyDto>> getProperties() {
        return new ResponseEntity<>(propertyService.getAllProperties(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public Property getPropertyDetail(@PathVariable Long id) {
        return new Property(1L, "123 Spooner St");
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        return new ResponseEntity<>(property, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
