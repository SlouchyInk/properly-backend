package rent.properly.properly.unit;

public interface UnitService {
    UnitDto createUnit(UnitDto unitDto);
    UnitResponse getAllUnits(int pageNo, int pageSize);
    UnitDto getUnitById(Long id);
    UnitDto updateUnit(Long id, UnitDto unitDto);
    void deleteUnitById(Long id);
}
