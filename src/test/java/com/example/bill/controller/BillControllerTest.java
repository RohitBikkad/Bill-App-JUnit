package com.example.bill.controller;

import com.example.bill.controller.BillController;
import com.example.bill.entity.Bill;
import com.example.bill.service.BillService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doAnswer;

@WebMvcTest(BillController.class)
public class BillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BillService billService;

    @Test
    public void testGetAllBills() throws Exception {
        List<Bill> mockBills = new ArrayList<>();
        mockBills.add(new Bill(1L, null, LocalDateTime.now(), 100.0, 120.0,"some description"));

        Mockito.when(billService.getAllBills()).thenReturn(mockBills);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bills")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].billId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].amount").value(100.0));

        Mockito.verify(billService, Mockito.times(1)).getAllBills();
    }

    @Test
    public void testAddBill() throws Exception {
        Bill newBill = new Bill(null, null, null, 150.0, 180.0, "New Description");
        Mockito.when(billService.addBill(Mockito.any(Bill.class))).thenReturn(newBill);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/bills")
                        .content("{\"amount\": 150.0, \"description\": \"New Description\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(150.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("New Description"));

        Mockito.verify(billService, Mockito.times(1)).addBill(Mockito.any(Bill.class));
    }

    @Test
    public void testDeleteBill() throws Exception {
        Mockito.doNothing().when(billService).deleteBill(Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/bills/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(billService, Mockito.times(1)).deleteBill(Mockito.anyLong());
    }




}