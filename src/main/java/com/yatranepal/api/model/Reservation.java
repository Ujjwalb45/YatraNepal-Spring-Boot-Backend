package com.yatranepal.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservations")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Hotel reservation entity")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the reservation", example = "1")
    private Long id;

    @NotNull(message = "User ID is required")
    @Column(name = "user_id", nullable = false)
    @Schema(description = "ID of the user making the reservation", example = "1")
    private Long userId;

    @NotNull(message = "Hotel ID is required")
    @Column(name = "hotel_id", nullable = false)
    @Schema(description = "ID of the hotel being reserved", example = "1")
    private Long hotelId;

    @ElementCollection
    @CollectionTable(name = "reservation_room_ids", joinColumns = @JoinColumn(name = "reservation_id"))
    @Column(name = "room_id")
    @NotEmpty(message = "At least one room ID is required")
    @Schema(description = "List of room IDs being reserved")
    private List<Long> roomIds;

    @ElementCollection
    @CollectionTable(name = "reservation_room_details", joinColumns = @JoinColumn(name = "reservation_id"))
    @Schema(description = "Details of reserved rooms")
    private List<RoomDetail> roomDetails;

    @ElementCollection
    @CollectionTable(name = "reservation_dates", joinColumns = @JoinColumn(name = "reservation_id"))
    @Column(name = "reservation_date")
    @NotEmpty(message = "At least one reservation date is required")
    @Schema(description = "List of reservation dates")
    private List<LocalDate> dates;

    @NotNull(message = "Total price is required")
    @Min(value = 0, message = "Total price cannot be negative")
    @Column(name = "total_price", nullable = false)
    @Schema(description = "Total price of the reservation", example = "5000.0")
    private Double totalPrice;

    @Column(name = "transaction_id", unique = true)
    @Schema(description = "Payment transaction ID")
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    @Schema(description = "Payment method used", example = "esewa")
    private PaymentMethod paymentMethod = PaymentMethod.CASH;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    @Schema(description = "Payment status", example = "pending")
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Schema(description = "Reservation status", example = "pending")
    private ReservationStatus status = ReservationStatus.PENDING;

    @Column(name = "cancellation_requested_at")
    @Schema(description = "Timestamp when cancellation was requested")
    private LocalDateTime cancellationRequestedAt;

    @Column(name = "pidx")
    @Schema(description = "Khalti transaction reference")
    private String pidx;

    @Column(name = "product_code")
    @Schema(description = "eSewa product code reference")
    private String productCode;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Schema(description = "Reservation creation timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Schema(description = "Reservation last update timestamp")
    private LocalDateTime updatedAt;

    // Enums
    public enum PaymentMethod {
        ESEWA, KHALTI, CASH
    }

    public enum PaymentStatus {
        PENDING, SUCCESS, FAILED
    }

    public enum ReservationStatus {
        PENDING, CONFIRMED, CANCELLED, CANCEL_REQUESTED
    }

    // Embedded class for room details
    @Embeddable
    public static class RoomDetail {
        @Column(name = "room_id")
        private Long roomId;

        @Column(name = "room_number")
        private Integer number;

        @Column(name = "room_title")
        private String title;

        // Constructors
        public RoomDetail() {}

        public RoomDetail(Long roomId, Integer number, String title) {
            this.roomId = roomId;
            this.number = number;
            this.title = title;
        }

        // Getters and Setters
        public Long getRoomId() {
            return roomId;
        }

        public void setRoomId(Long roomId) {
            this.roomId = roomId;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    // Constructors
    public Reservation() {}

    public Reservation(Long userId, Long hotelId, List<Long> roomIds, List<LocalDate> dates, Double totalPrice) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.roomIds = roomIds;
        this.dates = dates;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public List<Long> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(List<Long> roomIds) {
        this.roomIds = roomIds;
    }

    public List<RoomDetail> getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(List<RoomDetail> roomDetails) {
        this.roomDetails = roomDetails;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCancellationRequestedAt() {
        return cancellationRequestedAt;
    }

    public void setCancellationRequestedAt(LocalDateTime cancellationRequestedAt) {
        this.cancellationRequestedAt = cancellationRequestedAt;
    }

    public String getPidx() {
        return pidx;
    }

    public void setPidx(String pidx) {
        this.pidx = pidx;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
