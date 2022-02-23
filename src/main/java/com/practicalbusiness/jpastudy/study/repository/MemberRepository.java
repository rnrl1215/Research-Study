package com.practicalbusiness.jpastudy.study.repository;

import com.practicalbusiness.jpastudy.study.domain.*;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public void upadate(Member member, String hobby) {
        member.changeHobby(hobby);
    }

    public List<Member> findAll() {
        JPAQuery<Member> query = new JPAQuery<>(em);
        QMember qMember = QMember.member;

        List<Member> members = query.select(qMember)
                .from(qMember)
                .fetch();

        return members;
    }
}
