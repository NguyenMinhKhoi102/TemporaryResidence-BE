package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.MemberChangeTogether;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MemberChangeTogetherRepository extends JpaRepository<MemberChangeTogether, Long> {
    Boolean existsByCmndCccdAndIsDeleteAndGeneralProfileId(String cmndCccd, Integer isDelete, Long generalProfileId);

    List<MemberChangeTogether> findAllByIsDelete(Integer isDelete);
    List<MemberChangeTogether> findAllByGeneralProfileIdAndIsDelete(Long generalProfileId, Integer isDelete);

    Optional<MemberChangeTogether> findByIdAndIsDelete(Long id, Integer isDelete);
}
