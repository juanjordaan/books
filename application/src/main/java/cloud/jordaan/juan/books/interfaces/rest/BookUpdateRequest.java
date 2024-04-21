package cloud.jordaan.juan.books.interfaces.rest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BookUpdateRequest implements Serializable {
    @NotNull(message = "Id is required")
    Long id;
    @NotNull(message = "Version is required")
    Long version;
    @NotNull(message = "ISBN is required")
    @NotEmpty(message = "ISBN is required")
    String isbn;
    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    String title;
    @NotNull(message = "Author is required")
    @NotEmpty(message = "Author is required")
    String author;
    @NotNull(message = "Publish Year is required")
    @NotEmpty(message = "Publish Year is required")
    String publishYear;
    @NotNull(message = "Publisher is required")
    @NotEmpty(message = "Publisher is required")
    String publisher;
}