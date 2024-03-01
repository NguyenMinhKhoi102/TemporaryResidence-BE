package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.TypeProfile;
import com.bezkoder.spring.security.jwt.payload.request.TypeProfileRequest;
import com.bezkoder.spring.security.jwt.repository.TypeProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TypeProfileService {
    TypeProfile infoTypeProfile(Integer id);
    List<TypeProfile> listTypeProfile(Boolean active);
    Boolean addTypeProfile(TypeProfileRequest typeProfileRequest);
    Boolean updateTypeProfile(Integer id, TypeProfileRequest typeProfileRequest);
    Boolean deleteTypeProfile(Integer id);
}
