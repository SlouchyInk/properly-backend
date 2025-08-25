package rent.properly.properly.Landlord;

public class LandlordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public LandlordNotFoundException(String message) {
        super(message);
    }
}
