package com.yatranepal.api.service;

import com.yatranepal.api.model.Reservation;
import com.yatranepal.api.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public List<Reservation> getReservationsByHotelId(Long hotelId) {
        return reservationRepository.findByHotelId(hotelId);
    }

    public List<Reservation> getReservationsByStatus(Reservation.ReservationStatus status) {
        return reservationRepository.findByStatus(status);
    }

    public List<Reservation> getReservationsByPaymentStatus(Reservation.PaymentStatus paymentStatus) {
        return reservationRepository.findByPaymentStatus(paymentStatus);
    }

    public Optional<Reservation> getReservationByTransactionId(String transactionId) {
        return reservationRepository.findByTransactionId(transactionId);
    }

    public Optional<Reservation> getReservationByPidx(String pidx) {
        return reservationRepository.findByPidx(pidx);
    }

    public Optional<Reservation> getReservationByProductCode(String productCode) {
        return reservationRepository.findByProductCode(productCode);
    }

    public List<Reservation> getUserReservationsByStatus(Long userId, Reservation.ReservationStatus status) {
        return reservationRepository.findByUserIdAndStatus(userId, status);
    }

    public List<Reservation> getHotelReservationsByStatus(Long hotelId, Reservation.ReservationStatus status) {
        return reservationRepository.findByHotelIdAndStatus(hotelId, status);
    }

    public List<Reservation> getReservationsByHotelAndDate(Long hotelId, LocalDate date) {
        return reservationRepository.findByHotelIdAndDate(hotelId, date);
    }

    public List<Reservation> getCancellationRequests() {
        return reservationRepository.findCancellationRequests();
    }

    public Reservation confirmReservation(Long id) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public Reservation cancelReservation(Long id) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setStatus(Reservation.ReservationStatus.CANCELLED);
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public Reservation requestCancellation(Long id) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setStatus(Reservation.ReservationStatus.CANCEL_REQUESTED);
            reservation.setCancellationRequestedAt(LocalDateTime.now());
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public Reservation updatePaymentStatus(Long id, Reservation.PaymentStatus paymentStatus) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setPaymentStatus(paymentStatus);
            return reservationRepository.save(reservation);
        }
        return null;
    }
}
