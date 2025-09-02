package rent.properly.properly.property;

public class PropertyNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public PropertyNotFoundException(String message) {
        super(message);
    }
}
