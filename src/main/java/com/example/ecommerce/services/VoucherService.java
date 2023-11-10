package com.example.ecommerce.services;

import com.example.ecommerce.models.Voucher;
import com.example.ecommerce.repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoucherService {
    private final VoucherRepository voucherRepository;

    @Autowired
    public VoucherService(VoucherRepository voucherRepository){
        this.voucherRepository = voucherRepository;
    }

    public void addVoucher(Voucher voucher){
        voucherRepository.save(voucher);
    }
}
