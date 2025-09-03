package com.yatranepal.api.repository;

import com.yatranepal.api.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    List<Reservation> findByUserId(Long userId);
    
    List<Reservation> findByHotelId(Long hotelId);
    
    List<Reservation> findByStatus(Reservation.ReservationStatus status);
    
    List<Reservation> findByPaymentStatus(Reservation.PaymentStatus paymentStatus);
    
    Optional<Reservation> findByTransactionId(String transactionId);
    
    Optional<Reservation> findByPidx(String pidx);
    
    Optional<Reservation> findByProductCode(String productCode);
    
    @Query("SELECT r FROM Reservation r WHERE r.userId = :userId AND r.status = :status")
    List<Reservation> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Reservation.ReservationStatus status);
    
    @Query("SELECT r FROM Reservation r WHERE r.hotelId = :hotelId AND r.status = :status")
    List<Reservation> findByHotelIdAndStatus(@Param("hotelId") Long hotelId, @Param("status") Reservation.ReservationStatus status);
    
    @Query("SELECT r FROM Reservation r JOIN r.dates d WHERE d = :date AND r.hotelId = :hotelId")
    List<Reservation> findByHotelIdAndDate(@Param("hotelId") Long hotelId, @Param("date") LocalDate date);
    
    @Query("SELECT r FROM Reservation r WHERE r.status = 'CANCEL_REQUESTED' ORDER BY r.cancellationRequestedAt ASC")
    List<Reservation> findCancellationRequests();
}
