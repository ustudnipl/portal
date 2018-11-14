package pl.ustudni.portal.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.ustudni.portal.dao.RoleRepository;
import pl.ustudni.portal.model.Role;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;

//    @Autowired
//    @Qualifier("userRepository")
//    private UserRepository userRepository;

    @Autowired
    @Qualifier("roleRepository")
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        // create initial roles
        createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("USER");

        // create initial user

        alreadySetup = true;
    }

    private Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByRole(name);

        if (role == null) {
            role = new Role(name);
        }

        role = roleRepository.save(role);
        return role;
    }
}