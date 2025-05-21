package com.module3.model;
import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Long userId;
    private Long bookingId;
    private double amount;
    private String status;
    private String paymentMethod;
    // Getters and Setters
}