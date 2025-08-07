package diana.com.hotel.exception;

import diana.com.hotel.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String DATE_MISMATCH_MESSAGE = "Check in date must come before check out date";

    @ExceptionHandler({UserNotFoundException.class, RoomNotFoundException.class})
    public ResponseEntity<ErrorResponse> handlerNotFoundExceptions(RuntimeException exception) {
        ErrorResponse error = getErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handlerUserIllegalArgumentException(UserIllegalArgumentException exception) {
        ErrorResponse error = getErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingInvalidException.class)
    public ResponseEntity<ErrorResponse> handlerBookingInvalidException(BookingInvalidException exception) {
        ErrorResponse error = getErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse getErrorResponse(HttpStatus badRequest, String message) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(badRequest.value());
        error.setMessage(message);
        error.setTimestamp(LocalDateTime.now());
        return error;
    }
}
