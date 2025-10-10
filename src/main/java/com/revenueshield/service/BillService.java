package com.revenueshield.service;

import com.revenueshield.model.Bill;
import com.revenueshield.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

//    public Bill saveBill(String accountId, String fileName) {
//        Bill bill = new Bill(accountId, fileName, LocalDateTime.now());
//        return billRepository.save(bill);
//    }

    public Bill saveBill(String accountId, String fileName, String storagePath) {
        Bill bill = new Bill(accountId, fileName, LocalDateTime.now());
        bill.setStoragePath(storagePath);
        return billRepository.save(bill);
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }
}
