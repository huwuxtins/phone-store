package com.example.ecommerce.services;

import com.example.ecommerce.models.Brand;
import com.example.ecommerce.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAll(){
        return brandRepository.findAll();
    }

    public Brand getBrandByName(String name){
        return brandRepository.findBrandByName(name);
    }

    public Brand addBrand(Brand brand){
        brandRepository.save(brand);
        return getBrandByName(brand.getName());
    }
}
