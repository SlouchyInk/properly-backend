package rent.properly.properly.Property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
    private PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyDto createProperty(PropertyDto propertyDto) {
        Property property = new Property();
        property.setAddress(propertyDto.getAddress());

        Property newProperty = propertyRepository.save(property);

        PropertyDto propertyResponse = new PropertyDto();
        propertyResponse.setId(newProperty.getId());
        propertyResponse.setAddress(newProperty.getAddress());

        return propertyResponse;
    }

    @Override
    public List<PropertyDto> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
    }

    private PropertyDto mapToDto(Property property) {
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setId(property.getId());
        propertyDto.setAddress(property.getAddress());
        return propertyDto;
    }

    private Property mapToEntity(PropertyDto propertyDto) {
        Property property = new Property();
        property.setId(propertyDto.getId());
        property.setAddress(propertyDto.getAddress());
        return property;
    }
}
