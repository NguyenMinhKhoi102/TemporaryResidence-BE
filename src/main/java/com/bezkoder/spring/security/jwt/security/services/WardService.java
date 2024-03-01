package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.Ward;
import com.bezkoder.spring.security.jwt.payload.request.AddWardRequest;
import com.bezkoder.spring.security.jwt.payload.request.UpdateWardRequest;

import java.util.List;

public interface WardService {
    Ward infoWard(String code);
    Ward infoWardByFullNameAndDistrictCode(String fullName, String districtCode);
    List<Ward> listWard();
    List<Ward> listWardByDistrictCode(String districtCode);
    Boolean addWard(AddWardRequest request);
    Boolean updateWard(String code, UpdateWardRequest request);
    Boolean deleteWard(String code);
}
