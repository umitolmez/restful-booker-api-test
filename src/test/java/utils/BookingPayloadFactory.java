package utils;

import models.BookingDates;
import models.BookingRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class BookingPayloadFactory {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static BookingRequest createRandomBookingPayload() {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);

        LocalDate checkinDate = LocalDate.now().plusDays(1);
        LocalDate checkoutDate = LocalDate.now().plusDays(5);

        return BookingRequest.builder()
                .firstname("John_" + uniqueId)
                .lastname("Doe_" + uniqueId)
                .totalPrice(150)
                .depositPaid(true)
                .bookingDates(BookingDates.builder()
                        .checkin(checkinDate.format(DATE_FORMATTER))
                        .checkout(checkoutDate.format(DATE_FORMATTER))
                        .build())
                .additionalNeeds("Breakfast, WiFi")
                .build();
    }
}
