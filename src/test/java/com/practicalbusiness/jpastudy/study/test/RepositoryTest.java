package com.practicalbusiness.jpastudy.study.test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles(Profile.Test)
public class RepositoryTest {


}