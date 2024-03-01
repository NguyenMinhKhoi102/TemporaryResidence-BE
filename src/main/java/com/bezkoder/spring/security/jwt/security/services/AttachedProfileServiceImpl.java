package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.FileHandle.FileService;
import com.bezkoder.spring.security.jwt.models.AttachedProfile;
import com.bezkoder.spring.security.jwt.payload.request.AttachedProfileRequest;
import com.bezkoder.spring.security.jwt.repository.AttachedProfileRepository;
import com.bezkoder.spring.security.jwt.repository.GeneralProfileRepository;
import com.bezkoder.spring.security.jwt.repository.TemporaryResidenceProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AttachedProfileServiceImpl implements AttachedProfileService{
    @Autowired
    AttachedProfileRepository attachedProfileRepository;

    @Autowired
    GeneralProfileRepository generalProfileRepository;

    @Autowired
    TemporaryResidenceProfileRepository temporaryResidenceProfileRepository;

    @Autowired
    FileService fileService;

    @Override
    public AttachedProfile infoAttachedProfile(Long id){
        return attachedProfileRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));
    }
    @Override
    public List<AttachedProfile> listAttachedProfile(){
        return attachedProfileRepository.findAllByIsDelete(0);
    }
    @Override
    public List<AttachedProfile> listAttachedProfileByTemporaryResidenceProfileId(Long id){
        return attachedProfileRepository.findAllByTemporaryResidenceProfileIdAndIsDelete(id, 0);
    }
    @Override
    public List<AttachedProfile> listAttachedProfileByGeneralProfileId(Long id){
        return attachedProfileRepository.findAllByGeneralProfileIdAndIsDelete(id, 0);
    }
    @Override
    public Boolean addAttachedProfile(AttachedProfileRequest attachedProfileRequest){
        try {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestamp = dateFormat.format(now);
            String path = timestamp + "_" + attachedProfileRequest.getFile().getOriginalFilename();

            if(!fileService.uploadAttachedFile(attachedProfileRequest.getFile(), path)){
                System.out.println("Thêm ảnh thất bại");
                return false;
            }

            AttachedProfile attachedProfile = AttachedProfile.builder()
                    .name(attachedProfileRequest.getName())
                    .note(attachedProfileRequest.getNote())
                    .path(path)
                    .isCopy(attachedProfileRequest.getIsCopy())
                    .isDelete(0)
                    .generalProfile(attachedProfileRequest.getGeneralProfileId() == null ? null : generalProfileRepository.findByIdAndIsDelete(attachedProfileRequest.getGeneralProfileId(), 0).orElse(null))
                    .temporaryResidenceProfile(attachedProfileRequest.getTemporaryResidenceProfileId() == null ? null : temporaryResidenceProfileRepository.findByIdAndIsDelete(attachedProfileRequest.getTemporaryResidenceProfileId(), 0).orElse(null))
                    .build();
            attachedProfileRepository.save(attachedProfile);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean updateAttachedProfile(Long id, AttachedProfileRequest attachedProfileRequest){
        try {
            AttachedProfile attachedProfile = attachedProfileRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));

            attachedProfile.setGeneralProfile(
                    attachedProfileRequest.getGeneralProfileId() == null ?
                            attachedProfile.getGeneralProfile() :
                            generalProfileRepository
                                    .findByIdAndIsDelete(attachedProfileRequest.getGeneralProfileId(), 0).orElse(attachedProfile.getGeneralProfile())
            );

            attachedProfile.setTemporaryResidenceProfile(
                    attachedProfileRequest.getTemporaryResidenceProfileId() == null ?
                            attachedProfile.getTemporaryResidenceProfile() :
                            temporaryResidenceProfileRepository
                                    .findByIdAndIsDelete(attachedProfileRequest.getTemporaryResidenceProfileId(), 0).orElse(attachedProfile.getTemporaryResidenceProfile())
            );

            attachedProfile.setName(attachedProfileRequest.getName() == null ? attachedProfile.getName() : attachedProfileRequest.getName());
            attachedProfile.setNote(attachedProfileRequest.getNote() == null ? attachedProfile.getNote() : attachedProfileRequest.getNote());
//            attachedProfile.setPath(attachedProfileRequest.getPath() == null ? attachedProfile.getPath() : attachedProfileRequest.getPath());


            attachedProfileRepository.save(attachedProfile);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean deleteAttachedProfile(Long id){
        try {
            AttachedProfile attachedProfile = attachedProfileRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));
            attachedProfile.setIsDelete(1);
            attachedProfileRepository.save(attachedProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
