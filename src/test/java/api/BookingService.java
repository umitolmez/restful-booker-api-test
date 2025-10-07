package api;

import config.ConfigReader;
import models.Booking;
import models.BookingRequest;
import models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BookingService {

    private static final String BOOKING_ENDPOINT = "/booking";

    // --- GET ---
    public Response getBooking(int bookingId) {
        return given()
                .accept(ContentType.JSON) // API doc suggests Accept header for GetBooking
                .pathParam("id", bookingId)
                .when()
                .get(ConfigReader.getBaseUrl() + BOOKING_ENDPOINT + "/{id}");
    }

    // --- CREATE ---
    public Response createBooking(BookingRequest bookingPayload) {
        return given()
                .contentType(ContentType.JSON)
                .body(bookingPayload)
                .when()
                .post(ConfigReader.getBaseUrl() + BOOKING_ENDPOINT);
    }

    public BookingResponse createBookingAndExtract(BookingRequest bookingPayload) {
        return createBooking(bookingPayload)
                .then()
                .statusCode(200) // Status check may be removed for negative tests
                .extract()
                .as(BookingResponse.class);
    }

    // --- UPDATE (PUT) ---
    public Response updateBooking(int bookingId, BookingRequest updatePayload, String token) {
        return given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .body(updatePayload)
                .when()
                .put(ConfigReader.getBaseUrl() + BOOKING_ENDPOINT + "/{id}");
    }

    public Booking updateBookingAndExtract(int bookingId, BookingRequest updatePayload, String token) {
        return updateBooking(bookingId, updatePayload, token)
                .then()
                .statusCode(200) // Status check may be removed for negative tests
                .extract()
                .as(Booking.class);
    }

    // --- DELETE ---
    public Response deleteBooking(int bookingId, String token) {
        return given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .when()
                .delete(ConfigReader.getBaseUrl() + BOOKING_ENDPOINT + "/{id}");
    }

}
