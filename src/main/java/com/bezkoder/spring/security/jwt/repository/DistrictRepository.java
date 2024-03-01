package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.District;
import com.bezkoder.spring.security.jwt.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DistrictRepository extends JpaRepository<District, String> {
    Optional<District> findByCode(String code);
    Optional<District> findByFullNameAndIsDelete(String fullName, Integer isDelete);
    Optional<District> findByFullNameAndProvinceCodeAndIsDelete(String fullName, String provinceCode, Integer isDelete);
    Optional<District> findByCodeAndIsDelete(String code, Integer isDelete);

    List<District> findAllByIsDelete(Integer isDelete);
    List<District> findAllByProvinceCodeAndIsDelete(String provinceCode, Integer isDelete);
}
