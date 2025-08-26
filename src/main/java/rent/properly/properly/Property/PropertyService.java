package rent.properly.properly.Property;

import java.util.List;

public interface PropertyService {
    PropertyDto createProperty(PropertyDto propertyDto);
    PropertyResponse getAllProperties(int pageNo, int pageSize);
    PropertyDto getPropertyById(Long id);
    PropertyDto updateProperty(Long id, PropertyDto propertyDto);
    void deletePropertyById(Long id);
}
