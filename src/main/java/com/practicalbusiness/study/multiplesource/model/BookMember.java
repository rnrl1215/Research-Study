package com.practicalbusiness.study.multiplesource.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "book_member")
public class BookMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
