package com.example.bill.service;

import com.example.bill.entity.Bill;
import com.example.bill.entity.Users;
import com.example.bill.repository.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillServiceTest {

    @Mock
    BillRepository billRepositoryTest;

    @InjectMocks
    BillService billServiceTest;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testGetAllBills(){
        List<Bill> mockBill = new ArrayList<>();
        List<Bill> billList = new ArrayList<>();

        Users user = new Users(1L,"Rohit", "rohit@gmail.com", billList);

        mockBill.add(new Bill(1L,user, LocalDateTime.of(2022,3,1,0,0),10.5,14.4,"test description"));
        mockBill.add(new Bill(2L,user, LocalDateTime.of(2022,3,1,0,0),10.5,14.4,"test description"));

        when(billRepositoryTest.findAll()).thenReturn(mockBill);

        List<Bill> retrievedBill = billServiceTest.getAllBills();

        verify(billRepositoryTest,times(1)).findAll();
        assertEquals(mockBill,retrievedBill);
    }

    @Test
    public void testDeleteBill(){
        Long testBillId = 1L;
        doNothing().when(billRepositoryTest).deleteById(testBillId);

        billServiceTest.deleteBill(testBillId);

        verify(billRepositoryTest,times(1)).deleteById(testBillId);

    }





}