package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional
@Service("productService")
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    ReservationRepository reservationRepository;

    public ReservationRepository getReservationRepository() {
        return reservationRepository;
    }

    @Override
    public Reservation update(long id, int status) {
        Reservation reservation = this.reservationRepository.findById(id).orElseThrow(RuntimeException::new);
        reservation.setStatus(status);
        return this.reservationRepository.save(reservation);
    }

    public Reservation getById(long id) {
        return reservationRepository.findById(id).orElseThrow(RuntimeException::new);
    }

}
