package com.bezkoder.spring.security.jwt.components;

import com.bezkoder.spring.security.jwt.models.ReceiveResult;
import com.bezkoder.spring.security.jwt.repository.ReceiveResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class ReceiveResultDataLoader implements CommandLineRunner {
    final private ReceiveResultsRepository receiveResultsRepository;

    @Autowired
    public ReceiveResultDataLoader(ReceiveResultsRepository receiveResultsRepository){
        this.receiveResultsRepository = receiveResultsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = receiveResultsRepository.count();

        if(count == 0){
            ReceiveResult receiveResult = new ReceiveResult("Nhận qua cổng tạm trú");
            ReceiveResult receiveResult1 = new ReceiveResult("Nhận trực tiếp");
            ReceiveResult receiveResult2 = new ReceiveResult("Nhận qua email");
            receiveResultsRepository.save(receiveResult);
            receiveResultsRepository.save(receiveResult2);
            receiveResultsRepository.save(receiveResult1);
        }
    }
}
