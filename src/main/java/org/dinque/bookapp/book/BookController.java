package org.dinque.bookapp.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookResponse> addNewBook(
            @RequestBody BookRequest request
    ){
        return ResponseEntity.ok(bookService.addBook(request));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookResponse> getBook(
            @PathVariable Integer id
    ){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

}
