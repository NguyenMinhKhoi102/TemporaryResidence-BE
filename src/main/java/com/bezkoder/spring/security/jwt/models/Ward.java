package com.bezkoder.spring.security.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "wards")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ward extends BaseEntity{
    @Id
    private String code;

    private String name;
    private String nameEn;
    private String fullName;
    private String fullNameEn;
    private String codeName;
    private Integer isDelete;

    @ManyToOne
    @JoinColumn(name = "district_code")
    private District district;
}
