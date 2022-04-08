package com.practicalbusiness.study.modelmapper.service;

import com.practicalbusiness.study.modelmapper.domain.Customer;
import com.practicalbusiness.study.modelmapper.dto.CustomerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerServiceTest {

    @Test
    void ModelMapperTest() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        Customer customer = new Customer("이름", 33, "주소", "번호");
        CustomerDto dto1 = modelMapper.map(customer, CustomerDto.class);

        System.out.println(dto1.toString());
        Assertions.assertThat(dto1.getName()).isNotNull();
    }

}