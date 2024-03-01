package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {
    Optional<Province> findByCode(String code);

    Optional<Province> findByFullNameAndIsDelete(String fullName, Integer isDelete);

    Optional<Province> findByCodeAndIsDelete(String code, Integer isDelete);

    List<Province> findAllByIsDelete(Integer isDelete);
}
