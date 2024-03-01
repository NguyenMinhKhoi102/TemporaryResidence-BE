package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.Province;
import com.bezkoder.spring.security.jwt.payload.request.AddProvinceRequest;
import com.bezkoder.spring.security.jwt.payload.request.UpdateProvinceRequest;

import java.util.List;

public interface ProvinceService {
    Province infoProvince(String code);
    Province infoProvinceByFullName(String fullName);
    List<Province> listProvince();
    Boolean addProvince(AddProvinceRequest request);
    Boolean updateProvince(String code, UpdateProvinceRequest request);
    Boolean deleteProvince(String code);


}
