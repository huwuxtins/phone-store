package com.example.ecommerce.services;

import com.example.ecommerce.models.Phone;
import com.example.ecommerce.models.PhoneDetail;
import com.example.ecommerce.repositories.PhoneDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneDetailService {
    private final PhoneDetailRepository phoneDetailRepository;

    @Autowired
    public PhoneDetailService(PhoneDetailRepository phoneDetailRepository){
        this.phoneDetailRepository = phoneDetailRepository;
    }

    public List<PhoneDetail> getPhoneDetailsByPhone(Phone phone){
        return phoneDetailRepository.findAllByPhone(phone);
    }

    public void addPhoneDetail(PhoneDetail phoneDetail){
        phoneDetailRepository.save(phoneDetail);
    }
}
