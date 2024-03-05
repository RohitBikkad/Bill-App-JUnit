package com.example.bill.repository;

import com.example.bill.entity.Bill;
import com.example.bill.entity.Users;
import com.example.bill.service.BillService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


class BillRepositoryTest {

    @Mock
    private BillRepository billRepositoryTest;

    @InjectMocks
    private BillService billServiceTest;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testfindBillsByMonthAndYear() {

        int testMonth = 3;
        int testYear = 2022;

        List<Bill> mockbills = new ArrayList<>();
        List<Bill> billList = new ArrayList<>();

        Users user = new Users(1L,"Rohit", "rohit@gmail.com", billList);

        mockbills.add(new Bill(1L,user, LocalDateTime.of(2022,3,1,0,0),10.5,14.4,"test description"));
        mockbills.add(new Bill(2L,user, LocalDateTime.of(2022,3,1,0,0),10.5,14.4,"test description"));

        when(billRepositoryTest.findBillsByMonthAndYear(testMonth, testYear)).thenReturn(mockbills);

        List<Bill> retriveBills = billServiceTest.getBillsPerMonth(testMonth,testYear);

        verify(billRepositoryTest, times(1)).findBillsByMonthAndYear(testMonth,testYear);

        assertEquals(mockbills.size(),retriveBills.size());
    }
}

