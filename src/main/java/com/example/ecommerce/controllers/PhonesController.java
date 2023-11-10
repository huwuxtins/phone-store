package com.example.ecommerce.controllers;

import com.example.ecommerce.models.*;
import com.example.ecommerce.models.pk.PhoneKey;
import com.example.ecommerce.services.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("collections")
public class PhonesController {
    private final PhoneService phoneService;
    private final BrandService brandService;
    private final ColorService colorService;
    private final CapacityService capacityService;
    private final PhoneDetailService phoneDetailService;

    @Autowired
    public PhonesController(PhoneService phoneService,
                           BrandService brandService,
                           ColorService colorService,
                           CapacityService capacityService,
                           PhoneDetailService phoneDetailService){
        this.phoneService = phoneService;
        this.brandService = brandService;
        this.colorService = colorService;
        this.capacityService = capacityService;
        this.phoneDetailService = phoneDetailService;
    }
    @GetMapping("")
    public ResponseEntity<List<Phone>> getPhonesByBrand(@RequestParam Map<String, String> json){
        Brand brand = brandService.getBrandByName(json.get("nameBrand"));
        List<Phone> phones = phoneService.getPhonesByBrand(brand, Integer.parseInt(json.get("page")));
        System.out.println(phones);
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }
    @GetMapping("brands")
    public ResponseEntity<List<Brand>> getAllBrands(){
        return new ResponseEntity<>(brandService.getAll(), HttpStatus.OK);
    }

    @GetMapping("newest")
    public ResponseEntity<List<Phone>> getNewestPhones(
            @RequestParam(name="page",  defaultValue="0") int page,
            @RequestParam(name="number", defaultValue="20") int number 
    ){
        return new ResponseEntity<>(phoneService.getPhonesByPhoneDetails(page, number), HttpStatus.OK);
    }

    @GetMapping("best-selling")
    public ResponseEntity<List<Phone>> getBestSellingPhones(
            @RequestParam(name="page",  defaultValue="0") int page,
            @RequestParam(name="number", defaultValue="20") int number 
    ){
        return new ResponseEntity<>(phoneService.getPhonesBySelling(page, number), HttpStatus.OK);
    }

    @PostMapping("/add-phone")
    public ResponseEntity<String> addPhone(@RequestBody Map<String, Object> json){
        Map<String, Object> phoneData = (Map<String, Object>) json.get("phone");
        List<Map<String, Object>> phoneDetailDatas = (List<Map<String, Object>>) json.get("phone_details");

        Brand brand = brandService.getBrandByName((String) phoneData.get("brand"));
        if(brand == null){
            brand = brandService.addBrand(new Brand((String) phoneData.get("brand")));
        }
        String idPhone = UUID.randomUUID().toString();
        Phone phone = new Phone(idPhone, (String) phoneData.get("name"),
                (String) phoneData.get("description"),
                brand,
                null, null, null, null, null);
        phoneService.addPhone(phone);


        List<PhoneDetail> phoneDetails = new ArrayList<>();
        for(Map<String, Object> phoneDetailData: phoneDetailDatas){
            Map<String, String> idPhoneDetail = (Map<String, String>) phoneDetailData.get("id");

            String colorName = idPhoneDetail.get("color");
            Color color = colorService.getColorByName(colorName);
            if(color == null){
                color = colorService.addColor(colorName);
            }

            int capacitySize = Integer.parseInt(idPhoneDetail.get("capacity"));
            Capacity capacity = capacityService.getCapacityBySize(capacitySize);
            if(capacity == null){
                capacity = capacityService.addCapacity(capacitySize);
            }

            PhoneDetail phoneDetail = new PhoneDetail(new PhoneKey(idPhone, color.getId(),
                    capacity.getId()),
                    (String) phoneDetailData.get("img"),
                    Double.parseDouble((String) phoneDetailData.get("price")));
            phoneDetail.setCreatedAt(Date.valueOf(LocalDate.now()));
            phoneDetails.add(phoneDetail);
            phoneDetailService.addPhoneDetail(phoneDetail);
        }
        return new ResponseEntity<>("Add phone successfully", HttpStatus.CREATED);
    }
}
