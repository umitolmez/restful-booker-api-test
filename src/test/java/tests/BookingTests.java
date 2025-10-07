package tests;

import api.AuthService;
import api.BookingService;
import io.restassured.response.Response;
import models.Booking;
import models.BookingRequest;
import models.BookingResponse;
import utils.BookingPayloadFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Booking API Tests")
public class BookingTests extends BaseTest {

    private final BookingService bookingService = new BookingService();
    private final AuthService authService = new AuthService();

    @Test
    @DisplayName("Create Booking: A new booking should be created.")
    void createBookingSuccessfully() {
        // 1 Prepare test data
        BookingRequest requestPayload = BookingPayloadFactory.createRandomBookingPayload();

        // 2. Api call
        BookingResponse bookingResponse = bookingService.createBookingAndExtract(requestPayload);

        // 3. Assert
        assertThat(bookingResponse).as("Booking response should not be null").isNotNull();
        assertThat(bookingResponse.getBookingId()).as("Booking ID should be positive").isPositive();

        Booking createdBooking = bookingResponse.getBooking();
        assertThat(createdBooking).as("Created booking details should not be null").isNotNull();
        assertThat(createdBooking.getFirstname()).as("Firstname should match request").isEqualTo(requestPayload.getFirstname());
        assertThat(createdBooking.getLastname()).as("Lastname should match request").isEqualTo(requestPayload.getLastname());
        assertThat(createdBooking.getTotalPrice()).as("Total price should match request").isEqualTo(requestPayload.getTotalPrice());
        assertThat(createdBooking.isDepositPaid()).as("Deposit paid status should match request").isEqualTo(requestPayload.isDepositPaid());
        assertThat(createdBooking.getAdditionalNeeds()).as("Additional needs should match request").isEqualTo(requestPayload.getAdditionalNeeds());

        assertThat(createdBooking.getBookingDates()).as("Booking dates should not be null").isNotNull();
        assertThat(createdBooking.getBookingDates().getCheckin()).as("Checkin date should match request").isEqualTo(requestPayload.getBookingDates().getCheckin());
        assertThat(createdBooking.getBookingDates().getCheckout()).as("Checkout date should match request").isEqualTo(requestPayload.getBookingDates().getCheckout());
    }

    @Test
    @DisplayName("Update Booking: Existing booking should be updated.")
    void testUpdateBookingSuccessfully() {
        // --- ARRANGE ---

        // 1. Get Token
        String token = authService.getAuthToken();
        assertThat(token).as("Auth token should not be null or empty.").isNotNull().isNotEmpty();

        // 2. Create Booking
        BookingRequest initialPayload = BookingPayloadFactory.createRandomBookingPayload();
        BookingResponse createdBooking = bookingService.createBookingAndExtract(initialPayload);
        int bookingIdToUpdate = createdBooking.getBookingId();

        // 3. Create new payload to update
        BookingRequest updatePayload = BookingPayloadFactory.createRandomBookingPayload();
        // To make sure its updated
        updatePayload.setFirstname("UPDATED_" + updatePayload.getFirstname());
        updatePayload.setTotalPrice(999);

        // --- ACT ---
        Booking updatedBookingResponse = bookingService.updateBookingAndExtract(bookingIdToUpdate, updatePayload, token);

        // --- ASSERT ---
        assertThat(updatedBookingResponse).as("Update response should not be null").isNotNull();

        assertThat(updatedBookingResponse.getFirstname())
                .as("Firstname should be updated")
                .isEqualTo(updatePayload.getFirstname())
                .isNotEqualTo(initialPayload.getFirstname());

        assertThat(updatedBookingResponse.getTotalPrice())
                .as("Total price should be updated")
                .isEqualTo(999);

        // Nested object control
        assertThat(updatedBookingResponse.getBookingDates().getCheckin())
                .isEqualTo(updatePayload.getBookingDates().getCheckin());
    }

    @Test
    @DisplayName("Delete Booking: An existing booking should be deleted.")
    void testDeleteBookingSuccessfully() {
        // --- ARRANGE ---
        String token = authService.getAuthToken();
        assertThat(token).as("Auth token should not be null or empty").isNotNull().isNotEmpty();

        // Create a booking to be deleted
        BookingRequest initialPayload = BookingPayloadFactory.createRandomBookingPayload();
        BookingResponse createdBooking = bookingService.createBookingAndExtract(initialPayload);
        int bookingIdToDelete = createdBooking.getBookingId();
        assertThat(bookingIdToDelete).as("Created booking ID should be positive").isPositive();

        // --- ACT ---
        // Perform the delete operation
        Response deleteResponse = bookingService.deleteBooking(bookingIdToDelete, token);

        // --- ASSERT ---
        deleteResponse.then().statusCode(201);

        // Attempt to retrieve the deleted booking to confirm it no longer exists
        Response getAfterDeleteResponse = bookingService.getBooking(bookingIdToDelete);
        getAfterDeleteResponse.then().statusCode(404);
    }


    // Other test scenarios (negative scenarios, filtering etc.)
    // Example: testCreateBookingWithMissingFields, testCreateBookingWithInvalidDates, etc.
}