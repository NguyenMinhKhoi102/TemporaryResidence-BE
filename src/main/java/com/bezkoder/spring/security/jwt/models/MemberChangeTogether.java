package com.bezkoder.spring.security.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "members_change_together")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberChangeTogether extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer isDelete;

    //Thuộc tính người đăng ký tạm trú
    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Size(min = 9, max = 12)
    private String cmndCccd;

    private Integer gender;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại không hợp lệ")
    private String phone;

    @Email
    private String email;

    private String permanentAddress;

    private String currentAddress;

    private String job;

    private String workspace;

    //Thuộc tính tạm trú
    @Size(max = 50)
    private String relationshipHost;

    @Size(max = 50)
    private String relationshipDeclarent;

    //-----Khoá ngoại

    @ManyToOne
    @JoinColumn(name = "general_profile_id")
    private GeneralProfile generalProfile;

    @ManyToOne
    @JoinColumn(name = "temporary_residence_profile_id")
    private TemporaryResidenceProfile temporaryResidenceProfile;



}
