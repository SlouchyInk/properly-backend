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
    public ResponseEntity<PropertyResponse> getProperties(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(propertyService.getAllProperties(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PropertyDto> getPropertyDetail(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        return new ResponseEntity<>(property, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<PropertyDto> updateProperty(@PathVariable("id") Long propertyId, @RequestBody PropertyDto propertyDto) {
        PropertyDto response = propertyService.updateProperty(propertyId, propertyDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id) {
        propertyService.deletePropertyById(id);
        return new ResponseEntity<>("Property deleted", HttpStatus.OK);
    }
}
