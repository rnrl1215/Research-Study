package com.practicalbusiness.study.jpa.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "ca")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ca_id",nullable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "ca")
    private List<Certificate> certificates = new ArrayList<>();

    public CA(String name) {
        this.name = name;
    }
}
