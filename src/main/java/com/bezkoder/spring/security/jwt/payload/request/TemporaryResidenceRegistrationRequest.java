package com.bezkoder.spring.security.jwt.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemporaryResidenceRegistrationRequest {
    private String wardId;
    //Thuộc tính người đăng ký tạm trú
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
    //Thuộc tính tạm trú
    private String temperaryAddress;
    private Date temporaryResidenceExpiration;
    private String temporaryDigitalAddress;
    private Double temporaryLatitude;
    private Double temporaryLongitude;
    private String hostName;
    private String cmndCccdHost;
    private String relationshipHost;
    private String relationshipDeclarent;
    private String noteDeclarent;
    //---------khoá ngoại
    private Integer typeProfileId;
    private String caseProfile;
    private Integer receiveResultId;
    private Integer typeNotificationId;
    //-------mảng dữ liệu
    private List<MemberChangeTogetherRequest> memberChangeTogethers;
    private List<AttachedProfileRequest2> attachProfiles;
    private MultipartFile[] files;
}
