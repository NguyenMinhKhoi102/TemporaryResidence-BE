package com.bezkoder.spring.security.jwt.components;

import com.bezkoder.spring.security.jwt.models.Province;
import com.bezkoder.spring.security.jwt.repository.ProvinceRepository;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(5)
public class ProvinceDataLoader implements CommandLineRunner {

    final private ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceDataLoader(ProvinceRepository provinceRepository){
        this.provinceRepository = provinceRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        long count = provinceRepository.count();

        if(count == 0){
            try{
                List<List<String>> data = new ArrayList<>();
                Resource resource = new ClassPathResource("data/provinces.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Tách giá trị dựa trên dấu phẩy và khoảng trắng
                    String[] values = line.split(",\\s*");
                    List<String> row = new ArrayList<>();
                    for (String value : values) {
                        row.add(value);
                    }
//                    data.add(row);
                    Province province = new Province(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), Double.parseDouble(row.get(6)), Double.parseDouble(row.get(7)), 0);
                    provinceRepository.save(province);
                }
//                System.out.println(data);
                reader.close();
            }catch (Exception e){
                System.out.println(e);
            }

        }
    }
}
