package com.example.ecommerce.services;

import com.example.ecommerce.models.Brand;
import com.example.ecommerce.models.Phone;
import com.example.ecommerce.models.PhoneDetail;
import com.example.ecommerce.repositories.PhoneDetailRepository;
import com.example.ecommerce.repositories.PhonePagingAndSortingRepository;
import com.example.ecommerce.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PhoneService {
    private final PhoneRepository phoneRepository;
    private final PhoneDetailRepository phoneDetailRepository;
    private final PhonePagingAndSortingRepository phonePageAndSort;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository,
                        PhoneDetailRepository phoneDetailRepository,
                        PhonePagingAndSortingRepository phonePageAndSort){
        this.phoneRepository = phoneRepository;
        this.phoneDetailRepository = phoneDetailRepository;
        this.phonePageAndSort = phonePageAndSort;
    }

    public Phone getPhoneById(String id){
        return phoneRepository.findById(id).orElseThrow();
    }

    public List<Phone> getPhonesByPage(int numberPage){
        Pageable page = PageRequest.of(numberPage, 20);
        return phoneRepository.findAll(page).getContent();
    }

//    public Phone getPhoneBy

    public List<Phone> getPhonesByBrand(Brand brand, int numberPage){
        Pageable page = PageRequest.of(numberPage, 10);
        return phonePageAndSort.findAllByBrand(brand, page);
    }

    public List<Phone> getPhonesByPhoneDetails(int page, int number){
        Pageable pageable = PageRequest.of(page, number, Sort.by("phoneDetails.createdAt").ascending());
        return phonePageAndSort.findAll(pageable).getContent();
    }

    public List<Phone> getPhonesBySelling(int page, int number){
        Pageable pageable = PageRequest.of(page, number, Sort.by("orderDetails.quantity"));
        return phonePageAndSort.findAll(pageable).getContent();
    }

    public void addPhone(Phone phone){
        phoneRepository.save(phone);
    }

}
