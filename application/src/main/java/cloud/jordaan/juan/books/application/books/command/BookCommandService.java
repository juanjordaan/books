package cloud.jordaan.juan.books.application.books.command;

import cloud.jordaan.juan.books.domain.Book;
import cloud.jordaan.juan.books.infrastructure.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookCommandService {
    private final BookRepository repository;

    public Book createBook(Book book) {
        return BookCommandMapper.INSTANCE.map(repository.save(BookCommandMapper.INSTANCE.map(book)));
    }

    public Book updateBook(Book book) {
        return BookCommandMapper.INSTANCE.map(repository.save(BookCommandMapper.INSTANCE.map(book)));
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}