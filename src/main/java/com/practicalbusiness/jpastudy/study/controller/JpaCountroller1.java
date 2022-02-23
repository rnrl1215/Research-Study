package com.practicalbusiness.jpastudy.study.controller;

import com.practicalbusiness.jpastudy.study.domain.MemberShip;
import com.practicalbusiness.jpastudy.study.dto.MemberDto;
import com.practicalbusiness.jpastudy.study.service.MemberService;
import com.practicalbusiness.jpastudy.study.service.UpdateTestService;
import com.practicalbusiness.jpastudy.study.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jpa-study")
@RequiredArgsConstructor
@Slf4j
public class JpaCountroller1 {

    private final UpdateTestService updateTestService;
    private final MemberService memberService;

    @PostMapping("/members")
    public void registerMember(@Valid @RequestBody MemberDto memberDto) {

        log.info("memberDto is : {}", memberDto.toString());
        Member member = Member.builder()
                .age(memberDto.getAge())
                .hobby(memberDto.getHobby())
                .name(memberDto.getName())
                .memberShip(memberDto.getMemberShip())
                .build();

        memberService.save(member);
    }

    @PutMapping("members/hobby")
    public void updateHobbyOfMembers(
            @RequestParam String hobby
    ) {
        log.info("hobby is : {}", hobby);
        updateTestService.updateHobby(hobby);
    }

    @GetMapping("members")
    public ResponseEntity getAllMember() {
        List<Member> all = memberService.findAll();
        for (Member member : all) {
            log.info("MemberShip is : {}", member.getMemberShip());
            if (member.getMemberShip() == MemberShip.GOLD) {
                log.info("membership is gold!!!!!");
            }
        }
        return new ResponseEntity<List<Member>>(all, HttpStatus.OK);
    }
}
