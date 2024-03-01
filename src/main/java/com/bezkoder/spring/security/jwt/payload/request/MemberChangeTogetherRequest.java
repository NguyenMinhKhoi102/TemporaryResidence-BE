package com.bezkoder.spring.security.jwt.payload.request;

import com.bezkoder.spring.security.jwt.models.GeneralProfile;
import com.bezkoder.spring.security.jwt.models.TemporaryResidenceProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberChangeTogetherRequest {
    private String name;
    private Date birthday;
    private String cmndCccd;
    private Integer gender;
    private String phone;
    private String email;
    private String permanentAddress;
    private String currentAddress;
    private String job;
    private String workspace;
    private String relationshipHost;
    private String relationshipDeclarent;
    private Long generalProfileId;
    private Long temporaryResidenceProfileId;
}
