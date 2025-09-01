package rent.properly.properly.Unit;

import lombok.Data;
import rent.properly.properly.Lease.Lease;
import rent.properly.properly.Property.Property;

import java.util.List;

@Data
public class UnitDto {
    private long id;
    private Property property;
    private List<Lease> leases;
    private String name;
    private int beds;
    private int baths;
    private Integer rent;
    private Integer sqft;
}
