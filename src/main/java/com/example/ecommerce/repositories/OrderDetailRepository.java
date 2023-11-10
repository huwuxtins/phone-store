package com.example.ecommerce.repositories;

import com.example.ecommerce.models.OrderDetail;
import com.example.ecommerce.models.pk.OrderDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {
}
