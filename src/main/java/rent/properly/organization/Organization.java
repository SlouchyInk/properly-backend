package rent.properly.organization;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rent.properly.lease.Lease;
import rent.properly.property.Property;
import rent.properly.security.UserEntity;
import rent.properly.tenant.Tenant;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "display_name", nullable = false, length = 120)
    private String displayName;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lease> leases = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Property> properties = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tenant> tenants = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> users = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 16)
    private OrgType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_tier", nullable = false, length = 16)
    private PlanTier planTier;

    public void addUser(UserEntity user) {
        users.add(user);
        user.setOrganization(this);
    }

    public void removeUser(UserEntity user) {
        users.remove(user);
        user.setOrganization(null);
    }
}

