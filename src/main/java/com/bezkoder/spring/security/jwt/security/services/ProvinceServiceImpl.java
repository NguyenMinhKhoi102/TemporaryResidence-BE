package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.Province;
import com.bezkoder.spring.security.jwt.payload.request.AddProvinceRequest;
import com.bezkoder.spring.security.jwt.payload.request.UpdateProvinceRequest;
import com.bezkoder.spring.security.jwt.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService{

    @Autowired
    ProvinceRepository provinceRepository;

    @Override
    public Province infoProvince(String code){
        return provinceRepository.findByCodeAndIsDelete(code, 0)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy"));
    }

    @Override
    public Province infoProvinceByFullName(String fullName){
        return provinceRepository.findByFullNameAndIsDelete(fullName, 0)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy"));
    }

    @Override
    public List<Province> listProvince() {
        return provinceRepository.findAllByIsDelete(0);
    }

    @Override
    public Boolean addProvince(AddProvinceRequest request) {
        try{
            provinceRepository.save(new Province(request.getCode(),
                    request.getName(), request.getNameEn(),
                    request.getFullName(), request.getFullNameEn(),
                    request.getCodeName(), request.getLatitude(),
                    request.getLongitude(), 0));
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean updateProvince(String code, UpdateProvinceRequest request) {
        try{
            Province province = provinceRepository.findByCodeAndIsDelete(code, 0)
                    .orElseThrow(()-> new RuntimeException("Không tìm thấy"));
            province.setName(request.getName());
            province.setNameEn(request.getNameEn());
            province.setFullName(request.getFullName());
            province.setFullNameEn(request.getFullNameEn());
            province.setCodeName(request.getCodeName());
            province.setLatitude(request.getLatitude());
            province.setLongitude(request.getLongitude());
            provinceRepository.save(province);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean deleteProvince(String code) {
        try{
            Province province = provinceRepository.findByCodeAndIsDelete(code, 0)
                    .orElseThrow(()-> new RuntimeException("Không tìm thấy"));
            province.setIsDelete(1);;
            provinceRepository.save(province);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
