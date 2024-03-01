package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.MemberChangeTogether;
import com.bezkoder.spring.security.jwt.payload.request.MemberChangeTogetherRequest;
import com.bezkoder.spring.security.jwt.repository.GeneralProfileRepository;
import com.bezkoder.spring.security.jwt.repository.MemberChangeTogetherRepository;
import com.bezkoder.spring.security.jwt.repository.TemporaryResidenceProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberChangeTogetherServiceImpl implements MemberChangeTogetherService {
    @Autowired
    MemberChangeTogetherRepository memberChangeTogetherRepository;

    @Autowired
    GeneralProfileRepository generalProfileRepository;

    @Autowired
    TemporaryResidenceProfileRepository temporaryResidenceProfileRepository;
    @Override
    public MemberChangeTogether infoMemberChangeTogether(Long id){
        return memberChangeTogetherRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));
    }
    @Override
    public List<MemberChangeTogether> listMembersChangeTogether(){
        return memberChangeTogetherRepository.findAllByIsDelete(0);
    }

    @Override
    public List<MemberChangeTogether> listMembersChangeTogetherByGeneralProfileId(Long id){
        return memberChangeTogetherRepository.findAllByGeneralProfileIdAndIsDelete(id, 0);
    }
    @Override
    public Boolean addMemberChangeTogether(MemberChangeTogetherRequest memberChangeTogetherRequest){
        try{
            MemberChangeTogether memberChangeTogether = MemberChangeTogether.builder()
                    .isDelete(0)
                    .name(memberChangeTogetherRequest.getName())
                    .birthday(memberChangeTogetherRequest.getBirthday())
                    .cmndCccd(memberChangeTogetherRequest.getCmndCccd())
                    .gender(memberChangeTogetherRequest.getGender())
                    .phone(memberChangeTogetherRequest.getPhone())
                    .email(memberChangeTogetherRequest.getEmail())
                    .permanentAddress(memberChangeTogetherRequest.getPermanentAddress())
                    .currentAddress(memberChangeTogetherRequest.getCurrentAddress())
                    .job(memberChangeTogetherRequest.getJob())
                    .workspace(memberChangeTogetherRequest.getWorkspace())
                    .relationshipHost(memberChangeTogetherRequest.getRelationshipHost())
                    .relationshipDeclarent(memberChangeTogetherRequest.getRelationshipDeclarent())
                    .generalProfile(memberChangeTogetherRequest.getGeneralProfileId() == null ?
                            null :
                            generalProfileRepository.findByIdAndIsDelete(memberChangeTogetherRequest.getGeneralProfileId(), 0).orElse(null))
                    .temporaryResidenceProfile(memberChangeTogetherRequest.getTemporaryResidenceProfileId() == null ?
                            null :
                            temporaryResidenceProfileRepository.findByIdAndIsDelete(memberChangeTogetherRequest.getTemporaryResidenceProfileId(), 0).orElse(null))
                    .build();
            memberChangeTogetherRepository.save(memberChangeTogether);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean updateMemberChangeTogether(Long id, MemberChangeTogetherRequest memberChangeTogetherRequest){
        try{
            MemberChangeTogether memberChangeTogether = memberChangeTogetherRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));
            memberChangeTogether.setGeneralProfile(
                    memberChangeTogetherRequest.getGeneralProfileId() == null ?
                            memberChangeTogether.getGeneralProfile() :
                            generalProfileRepository.findById(memberChangeTogetherRequest.getGeneralProfileId()).orElse(memberChangeTogether.getGeneralProfile())
            );

            memberChangeTogether.setTemporaryResidenceProfile(
                    memberChangeTogetherRequest.getTemporaryResidenceProfileId() == null ?
                            memberChangeTogether.getTemporaryResidenceProfile() :
                            temporaryResidenceProfileRepository.findById(memberChangeTogetherRequest.getTemporaryResidenceProfileId()).orElse(memberChangeTogether.getTemporaryResidenceProfile())
            );

            memberChangeTogether.setName(memberChangeTogetherRequest.getName() == null ? memberChangeTogether.getName() : memberChangeTogetherRequest.getName());
            memberChangeTogether.setBirthday(memberChangeTogetherRequest.getBirthday() == null ? memberChangeTogether.getBirthday() : memberChangeTogetherRequest.getBirthday());
            memberChangeTogether.setCmndCccd(memberChangeTogether.getCmndCccd() == null ? memberChangeTogether.getCmndCccd() : memberChangeTogetherRequest.getCmndCccd());
            memberChangeTogether.setGender(memberChangeTogether.getGender() == null ? memberChangeTogether.getGender() : memberChangeTogetherRequest.getGender());
            memberChangeTogether.setPhone(memberChangeTogether.getPhone() == null ? memberChangeTogether.getPhone() : memberChangeTogetherRequest.getPhone());
            memberChangeTogether.setEmail(memberChangeTogether.getEmail() == null ? memberChangeTogether.getEmail() : memberChangeTogetherRequest.getEmail());
            memberChangeTogether.setPermanentAddress(memberChangeTogether.getPermanentAddress() == null ? memberChangeTogether.getPermanentAddress() : memberChangeTogetherRequest.getPermanentAddress());
            memberChangeTogether.setCurrentAddress(memberChangeTogether.getCurrentAddress() == null ? memberChangeTogether.getCurrentAddress() : memberChangeTogetherRequest.getCurrentAddress());
            memberChangeTogether.setJob(memberChangeTogether.getJob() == null ? memberChangeTogether.getJob() : memberChangeTogetherRequest.getJob());
            memberChangeTogether.setWorkspace(memberChangeTogether.getWorkspace() == null ? memberChangeTogether.getWorkspace() : memberChangeTogetherRequest.getWorkspace());
            memberChangeTogether.setRelationshipDeclarent(memberChangeTogetherRequest.getRelationshipDeclarent() == null ? memberChangeTogether.getRelationshipDeclarent() : memberChangeTogetherRequest.getRelationshipDeclarent());
            memberChangeTogether.setRelationshipHost(memberChangeTogetherRequest.getRelationshipHost() == null ? memberChangeTogether.getRelationshipHost() : memberChangeTogetherRequest.getRelationshipHost());

            memberChangeTogetherRepository.save(memberChangeTogether);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean deleteMemberChangeTogether(Long id) {
        try {
            MemberChangeTogether memberChangeTogether = memberChangeTogetherRepository.findByIdAndIsDelete(id, 0).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            memberChangeTogether.setIsDelete(1);
            memberChangeTogetherRepository.save(memberChangeTogether);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
