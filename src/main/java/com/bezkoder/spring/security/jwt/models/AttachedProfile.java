package com.bezkoder.spring.security.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "attached_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachedProfile extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String path;
    private String note;
    private Integer isCopy;
    private Integer isDelete;

    @ManyToOne
    @JoinColumn(name = "general_profile_id")
    private GeneralProfile generalProfile;

    @ManyToOne
    @JoinColumn(name = "temporary_residence_profile_id")
    private TemporaryResidenceProfile temporaryResidenceProfile;

    public AttachedProfile(String name, String path, String note, Integer isCopy){
        this.name = name;
        this.path = path;
        this.note = note;
        this.isCopy = isCopy;
        this.isDelete = 0;
    }

}
