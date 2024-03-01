package com.bezkoder.spring.security.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "districts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class District extends BaseEntity{
    @Id
    private String code;

    private String name;
    private String nameEn;
    private String fullName;
    private String fullNameEn;
    private String codeName;
    private Integer isDelete;

    @ManyToOne
    @JoinColumn(name = "province_code")
    private Province province;

}
