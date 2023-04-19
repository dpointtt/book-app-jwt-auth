package org.dinque.bookapp.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse addBook(BookRequest request) {
        var book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .pages(request.getPages())
                .bookText(request.getBook_text())
                .build();

        var savedBook = bookRepository.save(book);
        return BookResponse.builder()
                .id(savedBook.getId())
                .title(savedBook.getTitle())
                .author(savedBook.getAuthor())
                .pages(savedBook.getPages())
                .book_text(savedBook.getBookText())
                .build();
    }

    public BookResponse getBookById(Integer id){
        var book = bookRepository.findById(id.longValue()).orElseThrow(() -> new RuntimeException("Not found"));
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .pages(book.getPages())
                .book_text(book.getBookText())
                .build();
    }
}
