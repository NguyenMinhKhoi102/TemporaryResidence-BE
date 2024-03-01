package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.GeneralProfile;
import com.bezkoder.spring.security.jwt.payload.request.GeneralProfileRequest;
import com.bezkoder.spring.security.jwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GeneralProfileServiceImpl implements GeneralProfileService {
    @Autowired
    GeneralProfileRepository generalProfileRepository;

    @Autowired
    TypeProfileRepository typeProfileRepository;

    @Autowired
    TypeNotificationRepository typeNotificationRepository;

    @Autowired
    ReceiveResultsRepository receiveResultsRepository;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public GeneralProfile infoGeneralProfile(Long id){
        return generalProfileRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));
    }
    @Override
    public List<GeneralProfile> listGeneralProfiles(){
        return generalProfileRepository.findAllByIsDelete(0);
    }

    @Override
    public List<GeneralProfile> listGeneralProfilesByUserIdAndIsCopy(Long userId, Integer isCopy){
        return generalProfileRepository.findAllByIsDeleteAndIsCopyAndUserId(0, isCopy, userId);
    }
    @Override
    public List<GeneralProfile> listGeneralProfilesByUserId(Long userId){
        return generalProfileRepository.findAllByIsDeleteAndUserId(0, userId);
    }
    @Override
    public List<GeneralProfile> listGeneralProfilesByTypeAndByWard(Integer type, String code){
        return generalProfileRepository.findAllByIsDeleteAndTypeProfileIdAndWardCode(0, type, code);
    }
    @Override
    public GeneralProfile addGeneralProfile(GeneralProfileRequest generalProfileRequest, Long userId){
        try{
            GeneralProfile generalProfile = GeneralProfile.builder()
                    .ward(generalProfileRequest.getWardId() == null ? null : wardRepository.findByCodeAndIsDelete(generalProfileRequest.getWardId(), 0).orElse(null))
                    .isCopy(generalProfileRequest.getIsCopy())
                    .isHistory(0)
                    .status(0)
                    .isDelete(0)
                    .name(generalProfileRequest.getName())
                    .birthday(generalProfileRequest.getBirthday())
                    .cmndCccd(generalProfileRequest.getCmndCccd())
                    .gender(generalProfileRequest.getGender())
                    .phone(generalProfileRequest.getPhone())
                    .email(generalProfileRequest.getEmail())
                    .permanentAddress(generalProfileRequest.getPermanentAddress())
                    .currentAddress(generalProfileRequest.getCurrentAddress())
                    .job(generalProfileRequest.getJob())
                    .workspace(generalProfileRequest.getWorkspace())
                    .temporaryAddress(generalProfileRequest.getTemperaryAddress())
                    .temporaryResidenceExpiration(generalProfileRequest.getTemporaryResidenceExpiration())
                    .temporaryDigitalAddress(generalProfileRequest.getTemporaryDigitalAddress())
                    .temporaryLatitude(generalProfileRequest.getTemporaryLatitude())
                    .temporaryLongitude(generalProfileRequest.getTemporaryLongitude())
                    .hostName(generalProfileRequest.getHostName())
                    .cmndCccdHost(generalProfileRequest.getCmndCccdHost())
                    .relationshipHost(generalProfileRequest.getRelationshipHost())
                    .relationshipDeclarent(generalProfileRequest.getRelationshipDeclarent())
                    .noteDeclarent(generalProfileRequest.getNoteDeclarent())
                    .caseProfile(generalProfileRequest.getCaseProfile())
                    .typeProfile(generalProfileRequest.getTypeProfileId() == null ? null : typeProfileRepository.findByIdAndIsDelete(generalProfileRequest.getTypeProfileId(), 0).orElse(null))
                    .typeNotification(generalProfileRequest.getTypeNotificationId() == null ? null : typeNotificationRepository.findByIdAndIsDelete(generalProfileRequest.getTypeNotificationId(), 0).orElse(null))
                    .receiveResult(generalProfileRequest.getReceiveResultId() == null ? null : receiveResultsRepository.findByIdAndIsDelete(generalProfileRequest.getReceiveResultId(), 0).orElse(null))
                    .user(userRepository.findById(userId).orElse(null))
                    .build();
            return generalProfileRepository.save(generalProfile);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    @Override
    public Boolean updateGeneralProfile(Long id, GeneralProfileRequest generalProfileRequest){
        try{
            GeneralProfile generalProfile = generalProfileRepository.findByIdAndIsDelete(id, 0).orElseThrow(()-> new RuntimeException("Không tìm thấy"));
            if(generalProfileRequest.getTypeProfileId() != null)
                generalProfile.setTypeProfile(typeProfileRepository.findByIdAndIsDelete(generalProfileRequest.getTypeProfileId(), 0).orElse(generalProfile.getTypeProfile()));
            if(generalProfileRequest.getTypeNotificationId() != null)
                generalProfile.setTypeNotification(typeNotificationRepository.findByIdAndIsDelete(generalProfileRequest.getTypeNotificationId(), 0).orElse(generalProfile.getTypeNotification()));
            if(generalProfileRequest.getReceiveResultId() != null)
                generalProfile.setReceiveResult(receiveResultsRepository.findByIdAndIsDelete(generalProfileRequest.getReceiveResultId(), 0).orElse(generalProfile.getReceiveResult()));
            if(generalProfileRequest.getWardId() != null)
                generalProfile.setWard(wardRepository.findByCodeAndIsDelete(generalProfileRequest.getWardId() , 0).orElse(generalProfile.getWard()));
            if(generalProfileRequest.getIsCopy() != null)
                generalProfile.setIsCopy(generalProfileRequest.getIsCopy());
            if(generalProfileRequest.getStatus() != null)
                generalProfile.setStatus(generalProfileRequest.getStatus());
            if(generalProfileRequest.getName() != null)
                generalProfile.setName(generalProfileRequest.getName());
            if(generalProfileRequest.getBirthday() != null)
                generalProfile.setBirthday(generalProfileRequest.getBirthday());
            if(generalProfileRequest.getCmndCccd() != null)
                generalProfile.setCmndCccd(generalProfileRequest.getCmndCccd());
            if(generalProfileRequest.getGender() != null)
                generalProfile.setGender(generalProfileRequest.getGender());
            if(generalProfileRequest.getPhone() != null)
                generalProfile.setPhone(generalProfileRequest.getPhone());
            if(generalProfileRequest.getEmail() != null)
                generalProfile.setEmail(generalProfileRequest.getEmail());
            if(generalProfileRequest.getPermanentAddress() != null)
                generalProfile.setPermanentAddress(generalProfileRequest.getPermanentAddress());
            if(generalProfileRequest.getCurrentAddress() != null)
                generalProfile.setCurrentAddress(generalProfileRequest.getCurrentAddress());
            if(generalProfileRequest.getJob() != null)
                generalProfile.setJob(generalProfileRequest.getJob());
            if(generalProfileRequest.getWorkspace() != null)
                generalProfile.setWorkspace(generalProfileRequest.getWorkspace());
            if(generalProfileRequest.getTemperaryAddress() != null)
                generalProfile.setTemporaryAddress(generalProfileRequest.getTemperaryAddress());
            if(generalProfileRequest.getTemporaryResidenceExpiration() != null)
                generalProfile.setTemporaryResidenceExpiration(generalProfileRequest.getTemporaryResidenceExpiration());
            if(generalProfileRequest.getTemporaryDigitalAddress() != null)
                generalProfile.setTemporaryDigitalAddress(generalProfileRequest.getTemporaryDigitalAddress());
            if(generalProfileRequest.getTemporaryLatitude() != null)
                generalProfile.setTemporaryLatitude(generalProfileRequest.getTemporaryLatitude());
            if(generalProfileRequest.getTemporaryLongitude() != null)
                generalProfile.setTemporaryLongitude(generalProfileRequest.getTemporaryLongitude());
            if(generalProfileRequest.getHostName() != null)
                generalProfile.setHostName(generalProfile.getHostName());
            if(generalProfileRequest.getCmndCccdHost() != null)
                generalProfile.setCmndCccdHost(generalProfileRequest.getCmndCccdHost());
            if(generalProfileRequest.getRelationshipHost() != null)
                generalProfile.setRelationshipHost(generalProfileRequest.getRelationshipHost());
            if(generalProfileRequest.getRelationshipDeclarent() != null)
                generalProfile.setRelationshipDeclarent(generalProfileRequest.getRelationshipDeclarent());
            if(generalProfileRequest.getCaseProfile() != null)
                generalProfile.setCaseProfile(generalProfileRequest.getCaseProfile());
            generalProfileRepository.save(generalProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean deleteGeneralProfile(Long id){
        try{
            GeneralProfile generalProfile = generalProfileRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));
            generalProfile.setIsDelete(1);
            generalProfileRepository.save(generalProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
