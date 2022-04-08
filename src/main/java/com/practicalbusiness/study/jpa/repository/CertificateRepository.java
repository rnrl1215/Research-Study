package com.practicalbusiness.study.jpa.repository;

import com.practicalbusiness.study.jpa.domain.*;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CertificateRepository {
    private final EntityManager em;

    public void save(Certificate certificate) {
        em.persist(certificate);
    }


    public List<Certificate> findAll() {
        QCertificate qCertificate = QCertificate.certificate;
        QCA qCA = QCA.cA;
        QMember qMember = QMember.member;


        JPAQuery<Certificate> query = new JPAQuery<>(em);

        List<Certificate> fetch = query.select(qCertificate)
                .from(qCertificate)
                .join(qCertificate.ca, qCA)
                .join(qCertificate.member,qMember)
                .fetch();

        return  fetch;
    }


    public List<Certificate> findByAllFetchJoin() {
        QCertificate qCertificate = QCertificate.certificate;
        QCA qCA = QCA.cA;
        QMember qMember = QMember.member;


        JPAQuery<Certificate> query = new JPAQuery<>(em);

        List<Certificate> fetch = query.select(qCertificate)
                .from(qCertificate)
                .join(qCertificate.ca, qCA)
                .fetchJoin()
                .join(qCertificate.member,qMember)
                .fetchJoin()
                .fetch();

        return  fetch;
    }
}
