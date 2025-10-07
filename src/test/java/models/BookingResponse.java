package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    @JsonProperty("bookingid")
    private int bookingId;
    private Booking booking; // Nested Booking object representing the created booking details
}
