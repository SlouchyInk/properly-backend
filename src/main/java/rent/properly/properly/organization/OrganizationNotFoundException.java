package rent.properly.properly.organization;

public class OrganizationNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public OrganizationNotFoundException(String message) {
        super(message);
    }
}
