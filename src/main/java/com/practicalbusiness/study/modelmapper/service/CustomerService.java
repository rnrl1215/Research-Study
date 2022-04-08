package com.practicalbusiness.study.modelmapper.service;


import com.practicalbusiness.study.modelmapper.domain.Customer;
import com.practicalbusiness.study.modelmapper.dto.CustomerDto;
import com.practicalbusiness.study.modelmapper.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerDto findById(long id) {
        Customer findCustomer = customerRepository.findOne(id);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        CustomerDto customerDto = modelMapper.map(findCustomer, CustomerDto.class);
        return customerDto;
    }

    @Transactional
    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
