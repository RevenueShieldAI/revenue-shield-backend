package com.revenueshield.controller;

import com.revenueshield.model.Bill;
import com.revenueshield.service.BillService;
import com.revenueshield.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;
    private final FileStorageService fileStorageService;

    @Autowired
    public BillController(BillService billService, FileStorageService fileStorageService) {
        this.billService = billService;
        this.fileStorageService = fileStorageService;
    }

//    @PostMapping
//    public Bill createBill(@RequestParam String accountId, @RequestParam String fileName) {
//        return billService.saveBill(accountId, fileName);
//    }

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public Optional<Bill> getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return "Bill with ID " + id + " deleted successfully.";
    }

    @PostMapping("/upload")
    public Bill uploadBill(@RequestParam String accountId, @RequestParam("file") MultipartFile file) throws IOException {
        String storagePath = fileStorageService.storeFile(file);
        String fileName = file.getOriginalFilename();
        return billService.saveBill(accountId, fileName, storagePath);
    }
}
