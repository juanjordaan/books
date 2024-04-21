package cloud.jordaan.juan.books.clients;

import cloud.jordaan.juan.books.interfaces.rest.BookDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.List;

public interface BookClient {
    static ResponseEntity<List<BookDto>> get(TestRestTemplate rest, Long page, Long pageCount) {
        return rest.exchange("/books?page=" + page+"&pageCount="+pageCount, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {}, new HashMap<>());
    }

    static ResponseEntity<BookDto> get(TestRestTemplate rest, Long id) {
        return rest.exchange("/books/"+id, HttpMethod.GET, null, BookDto.class, new HashMap<>());
    }

    static ResponseEntity<BookDto> post(TestRestTemplate rest, BookDto givenBook) {
        return rest.postForEntity("/books", givenBook, BookDto.class);
    }

    static void delete(TestRestTemplate rest, Long id) {
        rest.delete("/books/" + id);
    }

    static ResponseEntity<Void> put(TestRestTemplate rest, BookDto book) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BookDto> entity = new HttpEntity<BookDto>(book, headers);

        return rest.exchange("/books", HttpMethod.PUT, entity, Void.class);
    }
}
