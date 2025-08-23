package rent.properly.properly.Landlord;

import java.util.List;
import java.util.Optional;

public interface LandlordService {
    LandlordDto createLandlord(LandlordDto landlordDto);
    List<LandlordDto> getAllLandlords();
}
