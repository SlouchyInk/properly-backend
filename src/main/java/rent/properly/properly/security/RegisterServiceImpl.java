package rent.properly.properly.security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rent.properly.properly.organization.Organization;
import rent.properly.properly.organization.OrganizationRepository;

import java.util.ArrayList;
import java.util.Collections;
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
    public void registerWithOrganization(RegisterWithOrganizationRequest request) {
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

        Role ownerRole = roleRepository.findByName("LANDLORD").orElseGet(() -> {
            Role r = new Role();
            r.setName("LANDLORD");
            return roleRepository.save(r);
        });

        List<Role> roles = new ArrayList<>();
        roles.add(ownerRole);
        user.setRoles(roles);
        user.setOrganization(org);

        organizationRepository.save(org);
        userRepository.save(user);
    }
}
