package com.practicalbusiness.study.multiplesource.dao;

import com.practicalbusiness.study.multiplesource.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private EntityManager em;

    public void findById(Long id) {
        em.find(Book.class,id);
    }
}
