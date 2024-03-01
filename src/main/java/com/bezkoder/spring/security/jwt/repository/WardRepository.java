package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WardRepository extends JpaRepository<Ward, String> {

    Optional<Ward> findByCode(String code);
    Optional<Ward> findByCodeAndIsDelete(String code, Integer isDelete);
    Optional<Ward> findByFullNameAndDistrictCodeAndIsDelete(String fullName, String districtCode, Integer isDelete);
    List<Ward> findAllByIsDelete(Integer isDelete);
    List<Ward> findAllByDistrictCodeAndIsDelete(String districtCode, Integer isDelete);
}
