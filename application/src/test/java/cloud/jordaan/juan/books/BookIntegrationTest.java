package cloud.jordaan.juan.books;

import static  org.junit.jupiter.api.Assertions.*;

import cloud.jordaan.juan.books.clients.BookClient;
import cloud.jordaan.juan.books.interfaces.rest.BookDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ActiveProfiles
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BooksApplication.class)
public class BookIntegrationTest {
    @Autowired
    private TestRestTemplate rest;

    @Test
    void contextLoads() {
    }

    @Test
    public void givenPagingWhenGetAll_thenExpectOK() {
        // given
        Map<String, Long> page = new HashMap<>();
        page.put("page", 1L);
        page.put("pageCount", 5L);

        // when
        final ResponseEntity<List<BookDto>> responseEntity = BookClient.get(rest, 1L, 5L);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<BookDto> books = responseEntity.getBody();
        assertEquals(books.size(), 5);

        BookDto book = books.getFirst();
        assertNotNull(book.getId());
        assertNotNull(book.getVersion());
        assertNotNull(book.getIsbn());
        assertNotNull(book.getTitle());
        assertNotNull(book.getAuthor());
        assertNotNull(book.getPublishYear());
        assertNotNull(book.getPublisher());
    }

    @Test
    public void givenBookIdWhenGetOne_thenExpectOK() {
        // given
        BookDto givenBook = BookClient.get(rest, 1L, 5L).getBody().getFirst();

        // when
        final ResponseEntity<BookDto> responseEntity = BookClient.get(rest, givenBook.getId());

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        BookDto book = responseEntity.getBody();

        assertEquals(givenBook.getId(), book.getId());
        assertEquals(givenBook.getVersion(), book.getVersion());
        assertEquals(givenBook.getIsbn(), book.getIsbn());
        assertEquals(givenBook.getTitle(), book.getTitle());
        assertEquals(givenBook.getAuthor(), book.getAuthor());
        assertEquals(givenBook.getPublishYear(), book.getPublishYear());
        assertEquals(givenBook.getPublisher(), book.getPublisher());
    }

    @Test
    public void givenBookWhenCreate_thenExpectOK() {
        // given
        BookDto givenBook = new BookDto();
        givenBook.setIsbn("0123456789");
        givenBook.setTitle("Test 0123456789");
        givenBook.setAuthor("Dev Test");
        givenBook.setPublishYear("2024");
        givenBook.setPublisher("Test Publisher");

        // when
        final ResponseEntity<BookDto> responseEntity = BookClient.post(rest, givenBook);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        BookDto book = responseEntity.getBody();

        assertNotNull(book.getId());
        assertNotNull(book.getVersion());
        assertEquals(givenBook.getIsbn(), book.getIsbn());
        assertEquals(givenBook.getTitle(), book.getTitle());
        assertEquals(givenBook.getAuthor(), book.getAuthor());
        assertEquals(givenBook.getPublishYear(), book.getPublishYear());
        assertEquals(givenBook.getPublisher(), book.getPublisher());

        BookClient.delete(rest, book.getId());
    }

    @Test
    public void givenBookWhenUpdate_thenExpectOK() throws JsonProcessingException {
        BookDto givenBook = BookClient.get(rest, 1L, 5L).getBody().getFirst();

        ResponseEntity<Void> book = BookClient.put(rest, givenBook);

        assertEquals(HttpStatus.OK, book.getStatusCode());
    }
}