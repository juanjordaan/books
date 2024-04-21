package cloud.jordaan.juan.books.interfaces.rest;

import cloud.jordaan.juan.books.application.books.command.BookCommandService;
import cloud.jordaan.juan.books.application.books.query.BookQueryService;
import cloud.jordaan.juan.books.domain.Paging;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookCommandService commandService;
    private final BookQueryService queryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBook(@Valid @RequestBody BookCreateRequest request) {
        return ResponseEntity.ok().body(BookMapper.INSTANCE.from(commandService.createBook(BookMapper.INSTANCE.fromBookCreateRequest(request))));
    }

    @GetMapping(value="/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBook(@PathVariable("bookId") Long bookId) {
        return ResponseEntity.ok().body(BookMapper.INSTANCE.from(queryService.getBook(bookId)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllBooks(@RequestParam(name="page", required = false) Long page, @RequestParam(name="pageCount", required = false) Long pageCount) {
        return ResponseEntity.ok().body(queryService.getAllBooks(new Paging(page, pageCount)).stream().map(BookMapper.INSTANCE::from).toList());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateBook(@Valid @RequestBody BookUpdateRequest request) {
        return ResponseEntity.ok().body(BookMapper.INSTANCE.from(commandService.updateBook(BookMapper.INSTANCE.fromBookUpdateRequest(request))));
    }

    @DeleteMapping(value="/{bookId}")
    public ResponseEntity deleteBook(@PathVariable("bookId") Long bookId) {
        commandService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
}