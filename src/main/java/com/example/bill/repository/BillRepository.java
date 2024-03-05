package com.example.bill.repository;

import com.example.bill.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT b FROM Bill b WHERE MONTH(b.billDate) = :month AND YEAR(b.billDate) = :year")
    List<Bill> findBillsByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
