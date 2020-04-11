package com.example.demo.service;

import com.example.demo.model.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReservationService {
    Reservation update(long id, int status);
    public Reservation getById(long id);
}
