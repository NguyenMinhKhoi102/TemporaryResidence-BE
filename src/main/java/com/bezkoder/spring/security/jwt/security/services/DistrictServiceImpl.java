package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.District;
import com.bezkoder.spring.security.jwt.models.Province;
import com.bezkoder.spring.security.jwt.payload.request.AddDistrictRequest;
import com.bezkoder.spring.security.jwt.payload.request.UpdateDistrictRequest;
import com.bezkoder.spring.security.jwt.repository.DistrictRepository;
import com.bezkoder.spring.security.jwt.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Override
    public District infoDistrict(String code) {
        return districtRepository.findByCodeAndIsDelete(code, 0)
                .orElseThrow(()->new RuntimeException("Không tìm thấy"));
    }

    @Override
    public District infoDistrictByFullNameAndProvinceCode(String fullName, String provinceCode){
        return districtRepository.findByFullNameAndProvinceCodeAndIsDelete(fullName, provinceCode, 0)
                .orElseThrow(()->new RuntimeException("Không tìm thấy"));
    }

    @Override
    public List<District> listDistrict() {
        return districtRepository.findAllByIsDelete(0);
    }

    @Override
    public List<District> listDistrictByProvinceCode(String province_code) {
        return districtRepository.findAllByProvinceCodeAndIsDelete(province_code, 0);
    }

    @Override
    public Boolean addDistrict(AddDistrictRequest request) {
        try{
            Province province = provinceRepository.findByCodeAndIsDelete(request.getProvinceCode(), 0)
                    .orElse(null);
            District district = new District(request.getCode(),
                    request.getName(), request.getNameEn(),
                    request.getFullName(), request.getFullNameEn(),
                    request.getCodeName(), 0, province);
            districtRepository.save(district);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean updateDistrict(String code, UpdateDistrictRequest request) {
        try{
            Province province = provinceRepository.findByCodeAndIsDelete(request.getProvinceCode(), 0)
                    .orElse(null);
            District district = districtRepository.findByCodeAndIsDelete(code, 0)
                    .orElseThrow(()->new RuntimeException("Không tìm thấy"));
            district.setName(request.getName());
            district.setNameEn(request.getNameEn());
            district.setFullName(request.getFullName());
            district.setFullNameEn(request.getFullNameEn());
            district.setCodeName(request.getCodeName());
            district.setProvince(province);
            districtRepository.save(district);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean deleteDistrict(String code) {
        try{
            District district = districtRepository.findByCodeAndIsDelete(code, 0)
                    .orElseThrow(()->new RuntimeException("Không tìm thấy"));
            district.setIsDelete(1);
            districtRepository.save(district);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
