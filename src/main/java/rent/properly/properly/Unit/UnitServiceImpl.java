package rent.properly.properly.Unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rent.properly.properly.Lease.LeaseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {
    private final LeaseRepository leaseRepository;
    private UnitRepository unitRepository;

    @Autowired
    public UnitServiceImpl(UnitRepository unitRepository, LeaseRepository leaseRepository) {
        this.unitRepository = unitRepository;
        this.leaseRepository = leaseRepository;
    }

    @Override
    public UnitDto createUnit(UnitDto unitDto) {
        Unit unit = new Unit();
        unit.setProperty(unitDto.getProperty());
        unit.setLeases(unitDto.getLeases());
        unit.setName(unitDto.getName());
        unit.setBeds(unitDto.getBeds());
        unit.setBaths(unitDto.getBaths());
        unit.setRent(unitDto.getRent());
        unit.setSqft(unitDto.getSqft());

        Unit newUnit = unitRepository.save(unit);

        UnitDto unitResponse = new UnitDto();
        unitResponse.setId(newUnit.getId());
        unitResponse.setProperty(newUnit.getProperty());
        unitResponse.setLeases(newUnit.getLeases());
        unitResponse.setName(newUnit.getName());
        unitResponse.setBeds(newUnit.getBeds());
        unitResponse.setBaths(newUnit.getBaths());
        unitResponse.setRent(newUnit.getRent());
        unitResponse.setSqft(newUnit.getSqft());

        return unitResponse;
    }

    @Override
    public UnitResponse getAllUnits(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Unit> units = unitRepository.findAll(pageable);
        List<Unit> unitList = units.getContent();
        List<UnitDto> content = unitList.stream().map(u -> mapToDto(u)).collect(Collectors.toList());

        UnitResponse unitResponse = new UnitResponse();
        unitResponse.setContent(content);
        unitResponse.setPageNo(units.getNumber());
        unitResponse.setPageSize(units.getSize());
        unitResponse.setTotalPages(units.getTotalPages());
        unitResponse.setTotalElements(units.getTotalElements());
        unitResponse.setLast(units.isLast());

        return unitResponse;
    }

    @Override
    public UnitDto updateUnit(Long id, UnitDto unitDto) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new UnitNotFoundException("Unit could not be updated"));
        unit.setProperty(unitDto.getProperty());
        unit.setLeases(unitDto.getLeases());
        unit.setName(unitDto.getName());
        unit.setBeds(unitDto.getBeds());
        unit.setBaths(unitDto.getBaths());
        unit.setRent(unitDto.getRent());
        unit.setSqft(unitDto.getSqft());

        Unit updatedUnit = unitRepository.save(unit);
        return mapToDto(updatedUnit);
    }

    @Override
    public void deleteUnitById(Long id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new UnitNotFoundException("Unit could not be deleted"));
        unitRepository.delete(unit);
    }

    @Override
    public UnitDto getUnitById(Long id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new UnitNotFoundException("Unit not found with id " + id));
        return mapToDto(unit);
    }

    private UnitDto mapToDto(Unit unit) {
        UnitDto unitDto = new UnitDto();
        unitDto.setId(unit.getId());
        unitDto.setProperty(unit.getProperty());
        unitDto.setLeases(unit.getLeases());
        unitDto.setName(unit.getName());
        unitDto.setBeds(unit.getBeds());
        unitDto.setBaths(unit.getBaths());
        unitDto.setRent(unit.getRent());
        unitDto.setSqft(unit.getSqft());
        return unitDto;
    }

    private Unit mapToEntity(UnitDto unitDto) {
        Unit unit = new Unit();
        unit.setId(unitDto.getId());
        unit.setProperty(unitDto.getProperty());
        unit.setLeases(unitDto.getLeases());
        unit.setName(unitDto.getName());
        unit.setBeds(unitDto.getBeds());
        unit.setBaths(unitDto.getBaths());
        unit.setRent(unitDto.getRent());
        unit.setSqft(unitDto.getSqft());
        return unit;
    }
}
