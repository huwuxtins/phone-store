package com.example.ecommerce.controllers;

import com.example.ecommerce.models.Phone;
import com.example.ecommerce.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("phone")
public class PhoneController {
    private final PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService){
        this.phoneService = phoneService;
    }

    @GetMapping
    public String phonePage(@RequestParam("id") String id, Model model){
        Phone phone = phoneService.getPhoneById(id);
        model.addAttribute("phone", phone);
        model.addAttribute("phoneDetails", phone.getPhoneDetails());
        return "product";
    }

}
