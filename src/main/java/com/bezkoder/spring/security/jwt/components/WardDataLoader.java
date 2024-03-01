package com.bezkoder.spring.security.jwt.components;

import com.bezkoder.spring.security.jwt.models.District;
import com.bezkoder.spring.security.jwt.models.Province;
import com.bezkoder.spring.security.jwt.models.Ward;
import com.bezkoder.spring.security.jwt.repository.DistrictRepository;
import com.bezkoder.spring.security.jwt.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(7)
public class WardDataLoader implements CommandLineRunner {

    final private WardRepository wardRepository;
    final private DistrictRepository districtRepository;

    @Autowired
    public WardDataLoader(WardRepository wardRepository, DistrictRepository districtRepository){
        this.wardRepository = wardRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = wardRepository.count();
        if(count == 0){
            try{
                List<List<String>> data = new ArrayList<>();
                Resource resource = new ClassPathResource("data/wards.txt");
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
                    District district = districtRepository.findByCode(row.get(6)).orElse(null);
                    Ward ward = new Ward(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5),0, district);
                    wardRepository.save(ward);
                }
//                System.out.println(data);
                reader.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
