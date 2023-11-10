package com.example.ecommerce.services;

import com.example.ecommerce.models.Color;
import com.example.ecommerce.repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorService {
    private final ColorRepository colorRepository;

    @Autowired
    public ColorService(ColorRepository colorRepository){
        this.colorRepository = colorRepository;
    }

    public Color getColorByName(String name){
        return colorRepository.findByName(name);
    }

    public Color addColor(String name){
        colorRepository.save(new Color(name));
        return getColorByName(name);
    }
}
