package com.practicalbusiness.jpastudy.study.service;

import com.practicalbusiness.jpastudy.study.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateTestService {
    public final MemberService memberService;

    public void updateHobby(String hobby) {
        List<Member> all = memberService.findAll();
        for (Member member : all) {
            memberService.update(member, hobby);
        }
    }
}
