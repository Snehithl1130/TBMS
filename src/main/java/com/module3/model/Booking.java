package com.module3.model;
import jakarta.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long userId;
    private Long packageId;
    private String startDate;
    private String endDate;
    private String status;
    private Long paymentId;
    // Getters and Setters
}