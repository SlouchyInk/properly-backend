package rent.properly.security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rent.properly.organization.Organization;
import rent.properly.organization.OrganizationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerWithOrganization(RegisterWithOrganizationRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken!");
        }

        if (request.getOrganizationDisplayName() == null || request.getOrganizationDisplayName().isBlank()) {
            throw new IllegalArgumentException("Organization display name is required.");
        }
        if (request.getOrganizationType() == null) {
            throw new IllegalArgumentException("Organization type is required.");
        }
        if (request.getPlanTier() == null) {
            throw new IllegalArgumentException("Organization plan tier is required.");
        }


        Organization org = new Organization();
        org.setDisplayName(request.getOrganizationDisplayName());
        org.setType(request.getOrganizationType());
        org.setPlanTier(request.getPlanTier());

        organizationRepository.save(org);

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        Role ownerRole = roleRepository.findByName("ROLE_LANDLORD").orElseGet(() -> {
            Role r = new Role();
            r.setName("ROLE_LANDLORD");
            return roleRepository.save(r);
        });

        List<Role> roles = new ArrayList<>();
        roles.add(ownerRole);
        user.setRoles(roles);
        user.setOrganization(org);

        organizationRepository.save(org);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void registerWithTenant(RegisterTenantRequestDto request) {
        if (request.getUsername() == null || request.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username is required.");
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required.");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken!");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role tenantRole = roleRepository.findByName("TENANT").orElseGet(() -> {
            Role r = new Role();
            r.setName("TENANT");
            return roleRepository.save(r);
        });

        List<Role> roles = new ArrayList<>();
        roles.add(tenantRole);
        user.setRoles(roles);

        if (request.getOrganizationId() != null) {
            Organization org = organizationRepository.findById(request.getOrganizationId())
                    .orElseThrow(() -> new IllegalArgumentException("Organization not found."));
            user.setOrganization(org);
        }

        userRepository.save(user);
    }
}
