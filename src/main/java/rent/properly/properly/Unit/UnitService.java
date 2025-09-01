package rent.properly.properly.Unit;

import rent.properly.properly.Property.PropertyDto;
import rent.properly.properly.Property.PropertyResponse;

public interface UnitService {
    UnitDto createUnit(UnitDto unitDto);
    UnitResponse getAllUnits(int pageNo, int pageSize);
    UnitDto getUnitById(Long id);
    UnitDto updateUnit(Long id, UnitDto unitDto);
    void deleteUnitById(Long id);
}
