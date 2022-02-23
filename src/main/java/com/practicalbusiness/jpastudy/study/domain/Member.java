package com.practicalbusiness.jpastudy.study.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity // entity는 primary key가 꼭 필요하다. (@Id로 지정)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotBlank(message = "이름은 공백이 될 수 없습니다.")
    private String name;

    @NotNull(message = "나이는 필수 입니다.")
    private int age;
    private String hobby;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Certificate> certificates = new ArrayList<>();


    @Convert(converter = MemberShipConvert.class)
    private MemberShip memberShip = MemberShip.SILVER;

    @Builder
    public Member(String name, int age, String hobby, MemberShip memberShip) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.memberShip = memberShip;
    }

    public void changeHobby(String hobby) {
        this.hobby = hobby;
    }

    public void addCertificate(Certificate certificate) {
        certificates.add(certificate);
    }
}
