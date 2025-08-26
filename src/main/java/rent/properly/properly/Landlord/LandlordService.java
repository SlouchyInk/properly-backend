package rent.properly.properly.Landlord;

import java.util.List;
import java.util.Optional;

public interface LandlordService {
    LandlordDto createLandlord(LandlordDto landlordDto);
    LandlordResponse getAllLandlords(int pageNo, int pageSize);
    LandlordDto getLandlordById(Long id);
    LandlordDto updateLandlord(Long id, LandlordDto landlordDto);
    void deleteLandlordById(Long id);
}
