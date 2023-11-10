package com.example.ecommerce.controllers;

import com.example.ecommerce.models.Voucher;
import com.example.ecommerce.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;

@RestController
@RequestMapping("vouchers")
public class VoucherController {
    private final VoucherService voucherService;

    @Autowired
    public VoucherController(VoucherService voucherService){
        this.voucherService = voucherService;
    }

    @PostMapping("/add-voucher")
    public ResponseEntity<Voucher> addVoucher(@RequestBody Map<String, String> json){
        Voucher voucher = new Voucher(Double.parseDouble(json.get("percent-discount")), Date.valueOf(json.get("date-expired")), false);
        voucherService.addVoucher(voucher);
        return new ResponseEntity<>(voucher, HttpStatus.CREATED);
    }
}
