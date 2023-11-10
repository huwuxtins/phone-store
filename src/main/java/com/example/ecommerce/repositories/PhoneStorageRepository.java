package com.example.ecommerce.repositories;

import com.example.ecommerce.models.PhoneStorage;
import com.example.ecommerce.models.pk.PhoneKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneStorageRepository extends JpaRepository<PhoneStorage, PhoneKey> {
}
