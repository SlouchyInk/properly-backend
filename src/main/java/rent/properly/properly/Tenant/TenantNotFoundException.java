package rent.properly.properly.Tenant;

public class TenantNotFoundException extends RuntimeException {
    public static final long serialVersionUID = 1;

    public TenantNotFoundException(String message) {
        super(message);
    }
}
