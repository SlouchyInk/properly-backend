package rent.properly.tenant;

public class TenantNotFoundException extends RuntimeException {
    public static final long serialVersionUID = 1;

    public TenantNotFoundException(String message) {
        super(message);
    }
}
