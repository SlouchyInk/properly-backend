package rent.properly.properly.Unit;

public class UnitNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;
    public UnitNotFoundException(String message) {
        super(message);
    }
}
