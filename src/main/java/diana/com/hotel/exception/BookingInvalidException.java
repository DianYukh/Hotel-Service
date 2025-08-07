package diana.com.hotel.exception;

public class BookingInvalidException extends RuntimeException {
    public BookingInvalidException(String message) {
        super(message);
    }
}
