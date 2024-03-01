package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.TemporaryResidenceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TemporaryResidenceProfileRepository extends JpaRepository<TemporaryResidenceProfile, Long> {

    Optional<TemporaryResidenceProfile> findByIdAndIsDelete(Long id, Integer isDelete);
    Optional<TemporaryResidenceProfile> findByCmndCccdAndIsDelete(String cmndCccd, Integer isDelete);
    List<TemporaryResidenceProfile> findAllByIsDelete(Integer isDelete);
    List<TemporaryResidenceProfile> findAllByIsDeleteAndIsExpiredAndUserId(Integer isDelete, Integer isExpired, Long userId);
    List<TemporaryResidenceProfile> findAllByIsDeleteAndWardCodeAndUserId(Integer isDelete, String wardCode, Long userId);
    List<TemporaryResidenceProfile> findAllByIsDeleteAndWardCode(Integer isDelete, String wardCode);

}
