package rent.properly.properly.Lease;

public class LeaseNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public LeaseNotFoundException(String message) {
        super(message);
    }
}
