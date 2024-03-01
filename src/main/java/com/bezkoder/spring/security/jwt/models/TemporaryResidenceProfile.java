package com.bezkoder.spring.security.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "temporary_residence_profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemporaryResidenceProfile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Thuộc tính chung
    @ManyToOne
    @JoinColumn(name = "ward_code")
    private Ward ward;

    private Integer isExpired;

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
    private String temperaryAddress;

    @Temporal(TemporalType.DATE)
    private Date temporaryResidenceExpiration;

    private String temporaryDigitalAddress;
    private Double temporaryLatitude;
    private Double temporaryLongitude;

    @Size(max = 50)
    private String hostName;

    @Size(min = 9, max = 12)
    private String cmndCccdHost;

    @Size(max = 50)
    private String relationshipHost;

    @Size(max = 50)
    private String relationshipDeclarent;

    //Thuộc tính các khoá ngoại
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;




}
