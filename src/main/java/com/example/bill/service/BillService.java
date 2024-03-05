package com.example.bill.service;

import com.example.bill.entity.Bill;
import com.example.bill.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill addBill(Bill bill) {
        bill.setBillDate(LocalDateTime.now());

        double vat = 0.20 * bill.getAmount() + 1.8;
        bill.setAmountWithVat(bill.getAmount() + vat);

        return billRepository.save(bill);
    }

    public void deleteBill(Long billId) {
        billRepository.deleteById(billId);
    }

    public List<Bill> getBillsPerMonth(int month, int year) {
        return billRepository.findBillsByMonthAndYear(month, year);
    }

    public Optional<Bill> getBillById(Long billId) {
        return billRepository.findById(billId);
    }


    public void updateBillAmount(Long billId, double newAmount) {

        Optional<Bill> optionalBill = billRepository.findById(billId);

        if(optionalBill.isPresent()){
            Bill bill = optionalBill.get();
            bill.setAmount(newAmount);

            double vat = 0.20 * newAmount + 1.8;
            bill.setAmountWithVat(newAmount + vat);

            billRepository.save(bill);
        }
    }
}