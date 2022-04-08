package com.practicalbusiness.study.jpa.dto;

import com.practicalbusiness.study.jpa.domain.MemberShip;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
public class MemberDto {
    @NotBlank(message = "이름은 공백이 될 수 없습니다.")
    private String name;

    @NotNull(message = "나이는 필수 입니다.")
    private int age;

    private String hobby;

    @NotNull(message = "멤버십은 필수 입니다.")
    private MemberShip memberShip;
}
