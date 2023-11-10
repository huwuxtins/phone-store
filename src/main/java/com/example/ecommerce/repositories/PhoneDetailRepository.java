package com.example.ecommerce.repositories;

import com.example.ecommerce.models.Phone;
import com.example.ecommerce.models.PhoneDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneDetailRepository extends JpaRepository<PhoneDetail, Phone> {
    List<PhoneDetail> findFirst5ByOrderByCreatedAtDesc();

    List<PhoneDetail> findAllByPhone(Phone phone);
}
