package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.TemporaryResidenceProfile;
import com.bezkoder.spring.security.jwt.payload.request.TemporaryResidenceProfileRequest;
import com.bezkoder.spring.security.jwt.repository.TemporaryResidenceProfileRepository;
import com.bezkoder.spring.security.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TemporaryResidenceProfileService {
    TemporaryResidenceProfile infoTemporaryResidenceProfile(Long id);
    List<TemporaryResidenceProfile> listTemporaryResidenceProfiles();
    List<TemporaryResidenceProfile> listTemporaryResidenceProfilesByIsExpired(Integer isExpired, Long userId);
    List<TemporaryResidenceProfile> listTemporaryResidenceProfilesByWardCode(String wardCode, Long userId);
    List<TemporaryResidenceProfile> listTemporaryResidenceProfilesByWardCode2(String wardCode);
    Boolean addTemporaryResidenceProfile(TemporaryResidenceProfileRequest temporaryResidenceProfileRequest, Long userId);
    Boolean updateTemporaryResidenceProfile(Long id, TemporaryResidenceProfileRequest temporaryResidenceProfileRequest);
    Boolean deleteTemporaryResidenceProfile(Long id);
}
