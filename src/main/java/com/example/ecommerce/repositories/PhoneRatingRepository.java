package com.example.ecommerce.repositories;

import com.example.ecommerce.models.PhoneRating;
import com.example.ecommerce.models.pk.PhoneRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRatingRepository extends JpaRepository<PhoneRating, PhoneRatingKey> {
}
