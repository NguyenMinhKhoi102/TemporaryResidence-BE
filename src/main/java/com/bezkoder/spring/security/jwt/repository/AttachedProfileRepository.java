package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.AttachedProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttachedProfileRepository extends JpaRepository<AttachedProfile, Long> {
    List<AttachedProfile> findAllByIsDelete(Integer isDelete);
    List<AttachedProfile> findAllByGeneralProfileIdAndIsDelete(Long generalProfileId, Integer isDelete);
    List<AttachedProfile> findAllByTemporaryResidenceProfileIdAndIsDelete(Long temporaryResidenceProfileId, Integer isDelete);
    Optional<AttachedProfile> findByIdAndIsDelete(Long id, Integer isDelete);
}
