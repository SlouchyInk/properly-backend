package rent.properly.properly.security;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rent.properly.properly.organization.Organization;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = true)
    private Organization organization;

    public void setOrganization(Organization organization) {
        if (this.organization != null) {
            this.organization.getUsers().remove(this);
        }
        this.organization = organization;
        if (organization != null) {
            organization.getUsers().add(this);
        }
    }


}
