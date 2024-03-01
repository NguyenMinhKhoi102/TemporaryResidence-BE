package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.GeneralProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GeneralProfileRepository extends JpaRepository<GeneralProfile, Long> {

    Optional<GeneralProfile> findByIdAndIsDelete(Long id, Integer isDelete);
    Optional<GeneralProfile> findByIdAndIsDeleteAndStatus(Long id, Integer isDelete, Integer status);
    List<GeneralProfile> findAllByIsDelete(Integer isDelete);
    List<GeneralProfile> findAllByIsDeleteAndTypeProfileIdAndWardCode(Integer isDelete, Integer typeProfileId, String wardCode);
    List<GeneralProfile> findAllByIsDeleteAndIsCopyAndUserId(Integer isDelete, Integer isCopy, Long userId);
    List<GeneralProfile> findAllByIsDeleteAndUserId(Integer isDelete,Long userId);

}
