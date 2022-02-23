package com.practicalbusiness.jpastudy.study.repository;

import com.practicalbusiness.jpastudy.study.domain.*;
import com.practicalbusiness.jpastudy.study.dto.TotalDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CertificateAuthorityRepository {
    private final EntityManager em;

    public void save(CA ca) {
        em.persist(ca);
    }

    // group by, projections 적용
    public List<TotalDto> countCertificate() {
        JPAQuery<Member> query = new JPAQuery<>(em);

        QCertificate qCertificate = QCertificate.certificate;
        QCA qca = QCA.cA;

        // projection.construct 도 있지만 생성자의 파라미터 위치 변경되면 안되기 때문에 비추
        return query.select(Projections.fields(TotalDto.class, qca.name, qca.id.count().as("count")))
                .from(qca)
                .join(qca.certificates, qCertificate)
                .groupBy(qca.name)
                .fetch();
    }

    public List<TotalDto> countCertificate2() {
        JPAQuery<Member> query = new JPAQuery<>(em);

        QCertificate qCertificate = QCertificate.certificate;
        QCA qca = QCA.cA;

        // projection.construct 도 있지만 생성자의 파라미터 위치 변경되면 안되기 때문에 비추
        return query.select(Projections.fields(TotalDto.class, qca.name, qca.id.count().as("count")))
                .from(qca)
                .leftJoin(qca.certificates, qCertificate).on(qCertificate.name.eq("JPA"))
                .groupBy(qca.name)
                .fetch();
    }


}
