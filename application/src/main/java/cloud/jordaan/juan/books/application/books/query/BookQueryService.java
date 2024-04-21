package cloud.jordaan.juan.books.application.books.query;

import cloud.jordaan.juan.books.domain.Book;
import cloud.jordaan.juan.books.domain.Paging;
import cloud.jordaan.juan.books.domain.error.ErrorCodes;
import cloud.jordaan.juan.books.domain.error.ErrorDto;
import cloud.jordaan.juan.books.domain.error.InternalApplicationException;
import cloud.jordaan.juan.books.infrastructure.repository.BookEntity;
import cloud.jordaan.juan.books.infrastructure.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookQueryService {
    private final BookRepository repository;

    public List<Book> getAllBooks(Paging paging) {
        Stream<BookEntity> books;
        if (paging.enabled()) {
            books = repository.findAllPaged(paging.limit(), paging.offset()).stream();
        } else {
            books = repository.findAll().stream();
        }

        return books
            .parallel()
            .map(BookQueryMapper.INSTANCE::map)
            .toList();
    }

    public Book getBook(Long bookId) {
        var entity = repository.findById(bookId)
            .orElseThrow(() -> new InternalApplicationException(ErrorDto.INSTANCE(ErrorCodes.BOOK_NOT_EXIST, "Book does not exist", "")));

        return BookQueryMapper.INSTANCE.map(entity);
    }
}