package com.practicalbusiness.study.jpa.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
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


    @Convert(converter = MemberShipConverter.class)
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
