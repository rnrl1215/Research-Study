package com.practicalbusiness.study.jpa.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "Certificates")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    private String name;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ca_id")
    private CA ca;

    public void registerMember(Member member) {
        this.member = member;
        member.addCertificate(this);
    }

    public void registerCA(CA ca){
        this.ca = ca;
        ca.getCertificates().add(this);
    }

    public Certificate(int number,
                       String name) {
        this.number = number;
        this.name = name;
    }

    public static Certificate createCertificate(int number,
                                         String name,
                                         Member member,
                                         CA ca)
    {
        Certificate certificate = new Certificate(number, name);
        certificate.registerCA(ca);
        certificate.registerMember(member);
        return certificate;
    }
}