package com.practicalbusiness.study.jpa.controller;

import com.practicalbusiness.study.jpa.domain.MemberShip;
import com.practicalbusiness.study.jpa.dto.MemberDto;
import com.practicalbusiness.study.jpa.service.JpaMemberService;
import com.practicalbusiness.study.jpa.service.UpdateTestService;
import com.practicalbusiness.study.jpa.domain.Member;
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
    private final JpaMemberService jpaMemberService;

    @PostMapping("/members")
    public void registerMember(@Valid @RequestBody MemberDto memberDto) {

        log.info("memberDto is : {}", memberDto.toString());
        Member member = Member.builder()
                .age(memberDto.getAge())
                .hobby(memberDto.getHobby())
                .name(memberDto.getName())
                .memberShip(memberDto.getMemberShip())
                .build();

        jpaMemberService.save(member);
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
        List<Member> all = jpaMemberService.findAll();
        for (Member member : all) {
            log.info("MemberShip is : {}", member.getMemberShip());
            if (member.getMemberShip() == MemberShip.GOLD) {
                log.info("membership is gold!!!!!");
            }
        }
        return new ResponseEntity<List<Member>>(all, HttpStatus.OK);
    }
}
