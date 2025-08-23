package rent.properly.properly.Property;

import java.util.List;

public interface PropertyService {
    PropertyDto createProperty(PropertyDto propertyDto);
    List<PropertyDto> getAllProperties();
}
