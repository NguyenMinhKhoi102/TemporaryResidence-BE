package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.District;
import com.bezkoder.spring.security.jwt.payload.request.AddDistrictRequest;
import com.bezkoder.spring.security.jwt.payload.request.UpdateDistrictRequest;

import java.util.List;

public interface DistrictService {
    District infoDistrict(String code);
    District infoDistrictByFullNameAndProvinceCode(String fullName, String provinceCode);
    List<District> listDistrict();
    List<District> listDistrictByProvinceCode(String province_code);
    Boolean addDistrict(AddDistrictRequest request);
    Boolean updateDistrict(String code, UpdateDistrictRequest request);
    Boolean deleteDistrict(String code);
}
