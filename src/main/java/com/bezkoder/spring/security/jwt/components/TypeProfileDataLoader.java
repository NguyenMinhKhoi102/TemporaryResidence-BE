package com.bezkoder.spring.security.jwt.components;

import com.bezkoder.spring.security.jwt.models.TypeProfile;
import com.bezkoder.spring.security.jwt.repository.TypeProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class TypeProfileDataLoader implements CommandLineRunner {

    private final TypeProfileRepository typeProfileRepository;

    @Autowired
    public TypeProfileDataLoader(TypeProfileRepository typeProfileRepository){
        this.typeProfileRepository = typeProfileRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = typeProfileRepository.count();

        if(count == 0){
            TypeProfile typeProfile1 = new TypeProfile("Đăng ký tạm trú");
            TypeProfile typeProfile2 = new TypeProfile("Gia hạn tạm trú");
            TypeProfile typeProfile3 = new TypeProfile("Trích lục tạm trú");
            typeProfileRepository.save(typeProfile1);
            typeProfileRepository.save(typeProfile2);
            typeProfileRepository.save(typeProfile3);
        }
    }
}
