package cloud.jordaan.juan.books.domain.error;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ErrorDto {
    String code;
    String message;
    String developerMessage;
    Map<String, String> validationErrors;

    public static ErrorDto INSTANCE(String code, Map<String, String> validationErrors, String developerMessage) {
        ErrorDto dto = new ErrorDto();
        dto.code = code;
        dto.validationErrors = validationErrors;
        dto.developerMessage = developerMessage;

        return dto;
    }

    public static ErrorDto INSTANCE(String code, String message, String developerMessage) {
        ErrorDto dto = new ErrorDto();
        dto.code = code;
        dto.message = message;
        dto.developerMessage = developerMessage;

        return dto;
    }
}