package cloud.jordaan.juan.books.domain.error;

import lombok.Getter;

@Getter
public class InternalApplicationException extends RuntimeException {
    private final ErrorDto errorDto;
    public InternalApplicationException(ErrorDto errorDto) {
        super(errorDto.message);
        this.errorDto = errorDto;
    }
}