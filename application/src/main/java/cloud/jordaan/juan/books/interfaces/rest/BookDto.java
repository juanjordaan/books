package cloud.jordaan.juan.books.interfaces.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BookDto implements Serializable {
    Long id;
    Long version;
    String isbn;
    String title;
    String author;
    String publishYear;
    String publisher;
}