package com.bezkoder.spring.security.jwt.components;

import com.bezkoder.spring.security.jwt.models.ERole;
import com.bezkoder.spring.security.jwt.models.Role;
import com.bezkoder.spring.security.jwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class RoleDataLoader implements CommandLineRunner {

    final private RoleRepository roleRepository;

    @Autowired
    public RoleDataLoader(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = roleRepository.count();

        if(count == 0){
            Role role1 = new Role(ERole.ROLE_ADMIN);
            Role role2 = new Role(ERole.ROLE_DISTRICT);
            Role role3 = new Role(ERole.ROLE_PROVINCE);
            Role role4 = new Role(ERole.ROLE_WARD);
            Role role5 = new Role(ERole.ROLE_USER);
            roleRepository.save(role1);
            roleRepository.save(role2);
            roleRepository.save(role3);
            roleRepository.save(role4);
            roleRepository.save(role5);
        }
    }
}
