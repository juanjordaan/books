package cloud.jordaan.juan.books.interfaces.rest;

import cloud.jordaan.juan.books.domain.error.ClientApplicationException;
import cloud.jordaan.juan.books.domain.error.ErrorCodes;
import cloud.jordaan.juan.books.domain.error.ErrorDto;
import cloud.jordaan.juan.books.domain.error.InternalApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestErrorHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity(ErrorDto.INSTANCE(ErrorCodes.VALIDATION_ERRORS, errors, ""), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClientApplicationException.class)
    public ResponseEntity<ErrorDto> handleClientApplicationException(ClientApplicationException e) {
        return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalApplicationException.class)
    public ResponseEntity<ErrorDto> handleInternalApplicationException(InternalApplicationException e) {
        return new ResponseEntity<>(e.getErrorDto(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDto> handleThrowable(Throwable e) {
        return new ResponseEntity<>(ErrorDto.INSTANCE(ErrorCodes.UNKNOWN_ERROR, e.getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}