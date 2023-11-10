package com.example.ecommerce.repositories;

import com.example.ecommerce.models.Brand;
import com.example.ecommerce.models.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PhonePagingAndSortingRepository extends PagingAndSortingRepository<Phone, String> {
    List<Phone> findAllByBrand(Brand brand, Pageable pageable);
    Page<Phone> findAll(Pageable pageable);
}
