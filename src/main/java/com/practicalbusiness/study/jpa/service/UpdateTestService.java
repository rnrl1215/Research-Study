package com.practicalbusiness.study.jpa.service;

import com.practicalbusiness.study.jpa.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateTestService {
    public final JpaMemberService jpaMemberService;

    public void updateHobby(String hobby) {
        List<Member> all = jpaMemberService.findAll();
        for (Member member : all) {
            jpaMemberService.update(member, hobby);
        }
    }
}
