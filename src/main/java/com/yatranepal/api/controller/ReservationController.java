package com.yatranepal.api.controller;

import com.yatranepal.api.model.Reservation;
import com.yatranepal.api.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@Tag(name = "Reservation Management", description = "APIs for managing hotel reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    @Operation(summary = "Get all reservations", description = "Retrieve all reservations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservations retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get reservation by ID", description = "Retrieve a specific reservation by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation found"),
        @ApiResponse(responseCode = "404", description = "Reservation not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        return reservation.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new reservation", description = "Create a new hotel reservation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody Reservation reservation) {
        Reservation createdReservation = reservationService.createReservation(reservation);
        return ResponseEntity.ok(createdReservation);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update reservation", description = "Update an existing reservation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation updated successfully"),
        @ApiResponse(responseCode = "404", description = "Reservation not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @Valid @RequestBody Reservation reservation) {
        if (!reservationService.getReservationById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete reservation", description = "Delete a reservation by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Reservation not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (!reservationService.getReservationById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get reservations by user", description = "Retrieve all reservations for a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservations retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable Long userId) {
        List<Reservation> reservations = reservationService.getReservationsByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/hotel/{hotelId}")
    @Operation(summary = "Get reservations by hotel", description = "Retrieve all reservations for a specific hotel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservations retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Reservation>> getReservationsByHotelId(@PathVariable Long hotelId) {
        List<Reservation> reservations = reservationService.getReservationsByHotelId(hotelId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get reservations by status", description = "Retrieve reservations by status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservations retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Reservation>> getReservationsByStatus(@PathVariable Reservation.ReservationStatus status) {
        List<Reservation> reservations = reservationService.getReservationsByStatus(status);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/payment-status/{paymentStatus}")
    @Operation(summary = "Get reservations by payment status", description = "Retrieve reservations by payment status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservations retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Reservation>> getReservationsByPaymentStatus(@PathVariable Reservation.PaymentStatus paymentStatus) {
        List<Reservation> reservations = reservationService.getReservationsByPaymentStatus(paymentStatus);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/transaction/{transactionId}")
    @Operation(summary = "Get reservation by transaction ID", description = "Retrieve reservation by transaction ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation found"),
        @ApiResponse(responseCode = "404", description = "Reservation not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Reservation> getReservationByTransactionId(@PathVariable String transactionId) {
        Optional<Reservation> reservation = reservationService.getReservationByTransactionId(transactionId);
        return reservation.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hotel/{hotelId}/date/{date}")
    @Operation(summary = "Get reservations by hotel and date", description = "Retrieve reservations for a specific hotel on a specific date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservations retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Reservation>> getReservationsByHotelAndDate(
            @PathVariable Long hotelId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Reservation> reservations = reservationService.getReservationsByHotelAndDate(hotelId, date);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/cancellation-requests")
    @Operation(summary = "Get cancellation requests", description = "Retrieve all reservation cancellation requests")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cancellation requests retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Reservation>> getCancellationRequests() {
        List<Reservation> reservations = reservationService.getCancellationRequests();
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}/confirm")
    @Operation(summary = "Confirm reservation", description = "Confirm a pending reservation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation confirmed successfully"),
        @ApiResponse(responseCode = "404", description = "Reservation not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Reservation> confirmReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.confirmReservation(id);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/cancel")
    @Operation(summary = "Cancel reservation", description = "Cancel a reservation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation cancelled successfully"),
        @ApiResponse(responseCode = "404", description = "Reservation not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.cancelReservation(id);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/request-cancellation")
    @Operation(summary = "Request cancellation", description = "Request cancellation of a reservation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cancellation requested successfully"),
        @ApiResponse(responseCode = "404", description = "Reservation not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Reservation> requestCancellation(@PathVariable Long id) {
        Reservation reservation = reservationService.requestCancellation(id);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/payment-status")
    @Operation(summary = "Update payment status", description = "Update payment status of a reservation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment status updated successfully"),
        @ApiResponse(responseCode = "404", description = "Reservation not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Reservation> updatePaymentStatus(@PathVariable Long id, @RequestParam Reservation.PaymentStatus paymentStatus) {
        Reservation reservation = reservationService.updatePaymentStatus(id, paymentStatus);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        }
        return ResponseEntity.notFound().build();
    }
}
