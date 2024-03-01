package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.GeneralProfile;
import com.bezkoder.spring.security.jwt.payload.request.GeneralProfileRequest;
import com.bezkoder.spring.security.jwt.repository.GeneralProfileRepository;
import com.bezkoder.spring.security.jwt.repository.ReceiveResultsRepository;
import com.bezkoder.spring.security.jwt.repository.TypeNotificationRepository;
import com.bezkoder.spring.security.jwt.repository.TypeProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GeneralProfileService {
    GeneralProfile infoGeneralProfile(Long id);
    List<GeneralProfile> listGeneralProfiles();
    List<GeneralProfile> listGeneralProfilesByUserIdAndIsCopy(Long userId, Integer isCopy);
    List<GeneralProfile> listGeneralProfilesByUserId(Long userId);
    List<GeneralProfile> listGeneralProfilesByTypeAndByWard(Integer type, String code);
    GeneralProfile addGeneralProfile(GeneralProfileRequest generalProfileRequest, Long userId);
    Boolean updateGeneralProfile(Long id, GeneralProfileRequest generalProfileRequest);
    Boolean deleteGeneralProfile(Long id);
}
