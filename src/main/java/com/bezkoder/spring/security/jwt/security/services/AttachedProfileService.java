package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.AttachedProfile;
import com.bezkoder.spring.security.jwt.payload.request.AttachedProfileRequest;

import java.util.List;


public interface AttachedProfileService {
    AttachedProfile infoAttachedProfile(Long id);
    List<AttachedProfile> listAttachedProfile();
    List<AttachedProfile> listAttachedProfileByTemporaryResidenceProfileId(Long id);
    List<AttachedProfile> listAttachedProfileByGeneralProfileId(Long id);
    Boolean addAttachedProfile(AttachedProfileRequest attachedProfileRequest);
    Boolean updateAttachedProfile(Long id, AttachedProfileRequest attachedProfileRequest);
    Boolean deleteAttachedProfile(Long id);


}
