package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.TypeProfile;
import com.bezkoder.spring.security.jwt.payload.request.TypeProfileRequest;
import com.bezkoder.spring.security.jwt.repository.TypeProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProfileServiceImpl implements TypeProfileService {
    @Autowired
    TypeProfileRepository typeProfileRepository;

    @Override
    public TypeProfile infoTypeProfile(Integer id){
        return typeProfileRepository.findByIdAndIsDelete(id, 0).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
    }
    @Override
    public List<TypeProfile> listTypeProfile(Boolean active){
        return active ?
                typeProfileRepository.findAllByIsDeleteAndIsActive(0, 1) :
                typeProfileRepository.findAllByIsDelete(0);
    }
    @Override
    public Boolean addTypeProfile(TypeProfileRequest typeProfileRequest){
        try {
            TypeProfile typeProfile = TypeProfile.builder()
                    .name(typeProfileRequest.getName())
                    .isActive(1)
                    .isDelete(0)
                    .build();
            typeProfileRepository.save(typeProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean updateTypeProfile(Integer id, TypeProfileRequest typeProfileRequest){
        try {
            TypeProfile typeProfile = typeProfileRepository.findByIdAndIsDelete(id, 0).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            typeProfile.setName(typeProfileRequest.getName() == null ? typeProfile.getName() : typeProfileRequest.getName());
            typeProfile.setIsActive(typeProfileRequest.getIsActive() == null ? typeProfile.getIsActive() : typeProfileRequest.getIsActive());
            typeProfileRepository.save(typeProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean deleteTypeProfile(Integer id){
        try {
            TypeProfile typeProfile = typeProfileRepository.findByIdAndIsDelete(id, 0).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            typeProfile.setIsDelete(1);
            typeProfileRepository.save(typeProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
