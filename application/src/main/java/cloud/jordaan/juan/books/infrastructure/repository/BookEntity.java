package cloud.jordaan.juan.books.infrastructure.repository;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Version
    Long version;

    String isbn;
    String title;
    String author;
    String publishYear;
    String publisher;
}