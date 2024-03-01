package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.District;
import com.bezkoder.spring.security.jwt.models.Ward;
import com.bezkoder.spring.security.jwt.payload.request.AddWardRequest;
import com.bezkoder.spring.security.jwt.payload.request.UpdateWardRequest;
import com.bezkoder.spring.security.jwt.repository.DistrictRepository;
import com.bezkoder.spring.security.jwt.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardServiceImpl implements WardService{

    @Autowired
    WardRepository wardRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Override
    public Ward infoWard(String code) {
        return wardRepository.findByCodeAndIsDelete(code, 0)
                .orElseThrow(()->new RuntimeException("Không tìm thấy"));
    }

    @Override
    public Ward infoWardByFullNameAndDistrictCode(String fullName, String districtCode){
        return wardRepository.findByFullNameAndDistrictCodeAndIsDelete(fullName, districtCode, 0)
                .orElseThrow(()->new RuntimeException("Không tìm thấy"));
    }



    @Override
    public List<Ward> listWard() {
        return wardRepository.findAllByIsDelete(0);
    }

    @Override
    public List<Ward> listWardByDistrictCode(String districtCode) {
        return wardRepository.findAllByDistrictCodeAndIsDelete(districtCode, 0);
    }

    @Override
    public Boolean addWard(AddWardRequest request) {
        try {
            District district = districtRepository.findByCodeAndIsDelete(request.getDistrictCode(), 0)
                    .orElse(null);
            Ward ward = new Ward(request.getCode(),
                    request.getName(), request.getNameEn(),
                    request.getFullName(), request.getFullNameEn(),
                    request.getCodeName(), 0, district);
            wardRepository.save(ward);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean updateWard(String code, UpdateWardRequest request) {
        try {
            District district = districtRepository.findByCodeAndIsDelete(request.getDistrictCode(), 0)
                    .orElse(null);
            Ward ward = wardRepository.findByCodeAndIsDelete(code, 0)
                    .orElseThrow(()->new RuntimeException("Không tìm thấy"));
            ward.setName(request.getName());
            ward.setNameEn(request.getNameEn());
            ward.setFullName(request.getFullName());
            ward.setFullNameEn(request.getFullNameEn());
            ward.setCodeName(request.getCodeName());
            ward.setDistrict(district);
            wardRepository.save(ward);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean deleteWard(String code) {
        try {
            Ward ward = wardRepository.findByCodeAndIsDelete(code, 0)
                    .orElseThrow(()->new RuntimeException("Không tìm thấy"));
            ward.setIsDelete(1);
            wardRepository.save(ward);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
