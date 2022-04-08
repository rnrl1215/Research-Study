package com.practicalbusiness.study.jpa.repository;

import com.practicalbusiness.study.jpa.domain.*;
import com.practicalbusiness.study.jpa.dto.TotalDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class QdslTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CertificateAuthorityRepository caRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @BeforeEach
    public void insertMember() {

        Member member = new Member("jin", 22, "drum", MemberShip.GOLD);
        CA ca1 = new CA("QNET");
        caRepository.save(ca1);

        Certificate certificate = Certificate.createCertificate(1,"JPA",  member, ca1);
        memberRepository.save(member);
        certificateRepository.save(certificate);


        Member member1 = new Member("tom", 24, "game", MemberShip.SILVER);
        Certificate certificate1 = Certificate.createCertificate(1,"SQLD", member1, ca1);
        memberRepository.save(member1);
        certificateRepository.save(certificate1);

   }

    // N+1 문제 발생
    // 1. 첫번째 모든 certificate 조회
    // 2. 연관관계 걸려있는 ca 조회 (영속성 컨텍스트에 올라가게 되고 1차 캐시만 접근되어 조회 안함)
    // 3. 연관관계 걸려있는 member 조회 (첫번째 certificate)
    // 4. 연관관계 걸려 있는 member 조회(두번쨰 certificate)
    @Test
    public void Given_InsertMember_When_FindByAll_Then_Success() {
        em.clear();
        List<Certificate> qnetCertificates = certificateRepository.findAll();
        for(Certificate c : qnetCertificates) {
            c.getMember().getName();
        }
    }

    //Fetch join
    @Test
    public void Given_InsertMember_When_FindByAllFetchJoin_Then_Success() {
        em.clear();
        List<Certificate> qnetCertificates = certificateRepository.findByAllFetchJoin();
        for(Certificate c : qnetCertificates) {
            c.getMember().getName();
        }
    }


    // projection, group by 예제
    @Test
    public void Given_InsertMember_When_CountCertificate_Then_Success() {
        List<TotalDto> totalDtos = caRepository.countCertificate();
        assertThat(totalDtos.size())
                .isEqualTo(1);
    }

    // left join 예제
    @Test
    public void Given_InsertMember_When_CountCertificate2_Then_Success() {
        List<TotalDto> totalDtos = caRepository.countCertificate2();
        assertThat(totalDtos.size())
                .isEqualTo(1);
    }

}