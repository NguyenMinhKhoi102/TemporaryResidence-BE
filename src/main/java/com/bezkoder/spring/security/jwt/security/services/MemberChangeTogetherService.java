package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.MemberChangeTogether;
import com.bezkoder.spring.security.jwt.payload.request.MemberChangeTogetherRequest;
import com.bezkoder.spring.security.jwt.repository.GeneralProfileRepository;
import com.bezkoder.spring.security.jwt.repository.MemberChangeTogetherRepository;
import com.bezkoder.spring.security.jwt.repository.TemporaryResidenceProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MemberChangeTogetherService {
    MemberChangeTogether infoMemberChangeTogether(Long id);
    List<MemberChangeTogether> listMembersChangeTogether();
    List<MemberChangeTogether> listMembersChangeTogetherByGeneralProfileId(Long id);
    Boolean addMemberChangeTogether(MemberChangeTogetherRequest memberChangeTogetherRequest);
    Boolean updateMemberChangeTogether(Long id, MemberChangeTogetherRequest memberChangeTogetherRequest);
    Boolean deleteMemberChangeTogether(Long id);
}
