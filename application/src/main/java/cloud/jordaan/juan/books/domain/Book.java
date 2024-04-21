package cloud.jordaan.juan.books.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    Long id;
    Long version;
    String isbn;
    String title;
    String author;
    String publishYear;
    String publisher;
}