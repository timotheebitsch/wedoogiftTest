package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USERS") // 'USER' reserved by spring boot
public class UserEntity
{
    @Id
    private long id;

    @Column(name = "name")
    private String userName;

    @OneToMany(mappedBy = "user")
    private List<DistributionEntity> distributions;
}
