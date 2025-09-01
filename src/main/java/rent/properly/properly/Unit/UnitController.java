package rent.properly.properly.Unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/unit/")
public class UnitController {

    private final UnitRepository unitRepository;
    private UnitService unitService;

    @Autowired
    public UnitController(UnitRepository unitRepository, UnitService unitService) {
        this.unitRepository = unitRepository;
        this.unitService = unitService;
    }

    @GetMapping()
    public ResponseEntity<UnitResponse> getUnits(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(unitService.getAllUnits(pageNo,pageSize));
    }

    @GetMapping("{id}")
    public ResponseEntity<UnitDto> getUnitDetail(@PathVariable Long id) {
        return ResponseEntity.ok(unitService.getUnitById(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UnitDto> createUnit(@RequestBody UnitDto unitDto) {
        return new ResponseEntity<>(unitService.createUnit(unitDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UnitDto> updateUnit(@PathVariable("id") Long unitId, @RequestBody UnitDto unitDto) {
        UnitDto response = unitService.updateUnit(unitId, unitDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUnit(@PathVariable Long id) {
        unitService.deleteUnitById(id);
        return ResponseEntity.ok("Unit deleted");
    }
}
