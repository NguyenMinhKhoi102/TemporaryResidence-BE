package com.bezkoder.spring.security.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "receive_results",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiveResult extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 50)
    private String name;
    private Integer isDelete;
    private Integer isActive;

    public ReceiveResult(String name){
        super();
        this.name = name;
        this.isDelete = 0;
        this.isActive = 1;
    }
}
