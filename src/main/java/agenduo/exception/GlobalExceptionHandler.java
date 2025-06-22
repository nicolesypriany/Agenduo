package agenduo.exception;

import agenduo.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var firstError = ex.getBindingResult().getFieldErrors().get(0);
        var message = firstError.getField() + ": " + firstError.getDefaultMessage();

        var error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                message,
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        var httpStatus = ex.getStatusCode();

        var error = new ErrorResponse(
                ex.getStatusCode().value(),
                HttpStatus.valueOf(httpStatus.value()).getReasonPhrase(),
                ex.getReason(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }
}