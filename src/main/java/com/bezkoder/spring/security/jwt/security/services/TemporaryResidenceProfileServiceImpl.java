package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.TemporaryResidenceProfile;
import com.bezkoder.spring.security.jwt.payload.request.TemporaryResidenceProfileRequest;
import com.bezkoder.spring.security.jwt.repository.TemporaryResidenceProfileRepository;
import com.bezkoder.spring.security.jwt.repository.UserRepository;
import com.bezkoder.spring.security.jwt.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemporaryResidenceProfileServiceImpl implements TemporaryResidenceProfileService{

    @Autowired
    TemporaryResidenceProfileRepository temporaryResidenceProfileRepository;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public TemporaryResidenceProfile infoTemporaryResidenceProfile(Long id){
        return temporaryResidenceProfileRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));
    }
    @Override
    public List<TemporaryResidenceProfile> listTemporaryResidenceProfiles(){
        return temporaryResidenceProfileRepository.findAllByIsDelete(0);
    }
    @Override
    public List<TemporaryResidenceProfile> listTemporaryResidenceProfilesByIsExpired(Integer isExpired, Long userId){
        return temporaryResidenceProfileRepository.findAllByIsDeleteAndIsExpiredAndUserId(0, isExpired, userId);
    }

    @Override
    public List<TemporaryResidenceProfile> listTemporaryResidenceProfilesByWardCode(String wardCode, Long userId){
        return temporaryResidenceProfileRepository.findAllByIsDeleteAndWardCodeAndUserId(0, wardCode, userId);
    }

    @Override
    public List<TemporaryResidenceProfile> listTemporaryResidenceProfilesByWardCode2(String wardCode){
        return temporaryResidenceProfileRepository.findAllByIsDeleteAndWardCode(0, wardCode);
    }

    @Override
    public Boolean addTemporaryResidenceProfile(TemporaryResidenceProfileRequest temporaryResidenceProfileRequest, Long userId){
        try{
            TemporaryResidenceProfile temporaryResidenceProfile = TemporaryResidenceProfile.builder()
                    .isExpired(temporaryResidenceProfileRequest.getIsExpired())
                    .isDelete(temporaryResidenceProfileRequest.getIsDelete())
                    .user(userRepository.findById(userId).orElse(null))
                    .ward(wardRepository.
                            findByCodeAndIsDelete(temporaryResidenceProfileRequest.getWardId(), 0)
                            .orElse(null))
                    .build();
            temporaryResidenceProfileRepository.save(temporaryResidenceProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean updateTemporaryResidenceProfile(Long id, TemporaryResidenceProfileRequest temporaryResidenceProfileRequest){
        try{
            System.out.println(temporaryResidenceProfileRequest);

           TemporaryResidenceProfile temporaryResidenceProfile = temporaryResidenceProfileRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));
            if(temporaryResidenceProfileRequest.getWardId() != null)
                temporaryResidenceProfile.setWard(wardRepository.
                        findByCodeAndIsDelete(temporaryResidenceProfileRequest.getWardId(), 0)
                        .orElse(null));
           if(temporaryResidenceProfileRequest.getIsExpired() != null)
                temporaryResidenceProfile.setIsExpired(temporaryResidenceProfileRequest.getIsExpired());
            if(temporaryResidenceProfileRequest.getName() != null)
                temporaryResidenceProfile.setName(temporaryResidenceProfileRequest.getName());
            if(temporaryResidenceProfileRequest.getBirthday() != null)
                temporaryResidenceProfile.setBirthday(temporaryResidenceProfileRequest.getBirthday());
            if(temporaryResidenceProfileRequest.getCmndCccd() != null)
                temporaryResidenceProfile.setCmndCccd(temporaryResidenceProfileRequest.getCmndCccd());
            if(temporaryResidenceProfileRequest.getGender() != null)
                temporaryResidenceProfile.setGender(temporaryResidenceProfileRequest.getGender());
            if(temporaryResidenceProfileRequest.getPhone() != null)
                temporaryResidenceProfile.setPhone(temporaryResidenceProfileRequest.getPhone());
            if(temporaryResidenceProfileRequest.getEmail() != null)
                temporaryResidenceProfile.setEmail(temporaryResidenceProfileRequest.getEmail());
            if(temporaryResidenceProfileRequest.getPermanentAddress() != null)
                temporaryResidenceProfile.setPermanentAddress(temporaryResidenceProfileRequest.getPermanentAddress());
            if(temporaryResidenceProfileRequest.getCurrentAddress() != null)
                temporaryResidenceProfile.setCurrentAddress(temporaryResidenceProfileRequest.getCurrentAddress());
            if(temporaryResidenceProfileRequest.getJob() != null)
                temporaryResidenceProfile.setJob(temporaryResidenceProfileRequest.getJob());
            if(temporaryResidenceProfileRequest.getWorkspace() != null)
                temporaryResidenceProfile.setWorkspace(temporaryResidenceProfileRequest.getWorkspace());
            if(temporaryResidenceProfileRequest.getTemporaryAddress() != null)
                temporaryResidenceProfile.setTemperaryAddress(temporaryResidenceProfileRequest.getTemporaryAddress());
            if(temporaryResidenceProfileRequest.getTemporaryResidenceExpiration() != null)
                temporaryResidenceProfile.setTemporaryResidenceExpiration(temporaryResidenceProfileRequest.getTemporaryResidenceExpiration());
            if(temporaryResidenceProfileRequest.getTemporaryDigitalAddress() != null)
                temporaryResidenceProfile.setTemporaryDigitalAddress(temporaryResidenceProfileRequest.getTemporaryDigitalAddress());
            if(temporaryResidenceProfileRequest.getTemporaryLatitude() != null)
                temporaryResidenceProfile.setTemporaryLatitude(temporaryResidenceProfileRequest.getTemporaryLatitude());
            if(temporaryResidenceProfileRequest.getTemporaryLongitude() != null)
                temporaryResidenceProfile.setTemporaryLongitude(temporaryResidenceProfileRequest.getTemporaryLongitude());
            if(temporaryResidenceProfileRequest.getHostName() != null)
                temporaryResidenceProfile.setHostName(temporaryResidenceProfileRequest.getHostName());
            if(temporaryResidenceProfileRequest.getCmndCccdHost() != null)
                temporaryResidenceProfile.setCmndCccdHost(temporaryResidenceProfileRequest.getCmndCccdHost());
            if(temporaryResidenceProfileRequest.getRelationshipHost() != null)
                temporaryResidenceProfile.setRelationshipHost(temporaryResidenceProfileRequest.getRelationshipHost());
            if(temporaryResidenceProfileRequest.getRelationshipDeclarent() != null)
                temporaryResidenceProfile.setRelationshipDeclarent(temporaryResidenceProfileRequest.getRelationshipDeclarent());
            temporaryResidenceProfileRepository.save(temporaryResidenceProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean deleteTemporaryResidenceProfile(Long id){
        try{
            TemporaryResidenceProfile temporaryResidenceProfile = temporaryResidenceProfileRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));
            temporaryResidenceProfile.setIsDelete(1);
            temporaryResidenceProfileRepository.save(temporaryResidenceProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
