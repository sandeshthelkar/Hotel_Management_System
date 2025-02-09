package com.codeWithProject.HotelServer.entity;

import com.codeWithProject.HotelServer.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ReservationRequest {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime reservationDateTime;

    @Enumerated(EnumType.STRING) //Store as a string in DB
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

}
