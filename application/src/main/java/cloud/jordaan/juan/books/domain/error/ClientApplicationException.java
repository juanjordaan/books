package cloud.jordaan.juan.books.domain.error;

import lombok.Getter;

@Getter
public class ClientApplicationException extends RuntimeException {
    private final ErrorDto errorDto;
    public ClientApplicationException(ErrorDto errorDto) {
        super(errorDto.message);
        this.errorDto = errorDto;
    }
}