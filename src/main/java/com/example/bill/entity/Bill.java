package com.example.bill.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long billId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	private LocalDateTime billDate;
	private double amount;
	private double amountWithVat;
	private String description;

	public double setUserId(long l) {
		return l;
	}
}