package rent.properly.properly.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PropertyResponse getAllProperties(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Property> properties = propertyRepository.findAll(pageable);
        List<Property> propertyList = properties.getContent();
        List<PropertyDto> content = propertyList.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        PropertyResponse propertyResponse = new PropertyResponse();
        propertyResponse.setContent(content);
        propertyResponse.setPageNo(properties.getNumber());
        propertyResponse.setPageSize(properties.getSize());
        propertyResponse.setTotalPages(properties.getTotalPages());
        propertyResponse.setTotalElements(properties.getTotalElements());
        propertyResponse.setLast(properties.isLast());

        return propertyResponse;
    }

    @Override
    public PropertyDto getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("property not found with id " + id));
        return mapToDto(property);
    }

    @Override
    public PropertyDto updateProperty(Long id, PropertyDto propertyDto) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("property could not be updated"));
        property.setAddress(propertyDto.getAddress());
        Property updatedProperty = propertyRepository.save(property);
        return mapToDto(updatedProperty);
    }

    @Override
    public void deletePropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("property could not be deleted"));
        propertyRepository.delete(property);
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
