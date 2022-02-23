package com.practicalbusiness.jpastudy.study.domain;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Slf4j
@Converter(autoApply = true)
public class MemberShipConvert implements AttributeConverter<MemberShip,String> {
    @Override
    public String convertToDatabaseColumn(MemberShip attribute) {
        log.info("attribute is : {} ", attribute.getCode());
        return attribute.getCode();
    }

    @Override
    public MemberShip convertToEntityAttribute(String dbData) {
        log.info("attribute is : {} ", dbData);
        return Stream.of(MemberShip.values())
                .filter(m -> m.getCode().equals(dbData))
                .findFirst()
                .orElse(null);
    }
}
