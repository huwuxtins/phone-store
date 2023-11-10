package com.example.ecommerce.services;

import com.example.ecommerce.models.Capacity;
import com.example.ecommerce.repositories.CapacityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapacityService {
    private final CapacityRepository capacityRepository;

    @Autowired
    public CapacityService(CapacityRepository capacityRepository){
        this.capacityRepository = capacityRepository;
    }

    public Capacity getCapacityBySize(int size){
        return capacityRepository.findBySizeInGB(size);
    }

    public Capacity addCapacity(int size){
        capacityRepository.save(new Capacity(size));
        return getCapacityBySize(size);
    }
}
