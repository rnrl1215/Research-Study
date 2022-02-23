package com.practicalbusiness.jpastudy.study.service;

import com.practicalbusiness.jpastudy.study.domain.Member;
import com.practicalbusiness.jpastudy.study.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(Member member) {
        log.info("save member info : {}", member.toString());
        memberRepository.save(member);
    }

    public void update(Member member, String hobby) {
        memberRepository.upadate(member, hobby);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}