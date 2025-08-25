package rent.properly.properly.Property;

public class PropertyNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public PropertyNotFoundException(String message) {
        super(message);
    }
}
