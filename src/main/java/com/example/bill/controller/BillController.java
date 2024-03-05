package com.example.bill.controller;

import com.example.bill.entity.Bill;
import com.example.bill.entity.Users;
import com.example.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> bills = billService.getAllBills();
        return ResponseEntity.ok(bills);
    }

    @PostMapping
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill) {
        Bill addedBill = billService.addBill(bill);
        return ResponseEntity.ok(addedBill);
    }

    @DeleteMapping("/{billId}")
    public ResponseEntity<String> deleteBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
        return ResponseEntity.ok("Bill deleted successfully");
    }

    @GetMapping("/per-month")
    public ResponseEntity<List<Bill>> getBillsPerMonth(@RequestParam int month, @RequestParam int year) {
        List<Bill> bills = billService.getBillsPerMonth(month, year);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billId) {
        Optional<Bill> bill = billService.getBillById(billId);
        return bill.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{billId}")
    public ResponseEntity<String> updateBillAmount(@PathVariable Long billId, @RequestParam double newAmount){
        billService.updateBillAmount(billId, newAmount);
        return ResponseEntity.ok("Bill Updated");
    }


}