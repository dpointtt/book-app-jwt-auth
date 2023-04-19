package org.dinque.bookapp.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.dinque.bookapp.book.Book;
import org.dinque.bookapp.book.BookRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse addUser(UserRequest request){
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        var savedUser = userRepository.save(user);

        return UserResponse.builder()
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }

    public UserBooksResponse getUserBooks(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<String> books = new ArrayList<>();

        for (var book:
             user.getBooksList()) {
            books.add(book.getTitle());
        }

        return UserBooksResponse.builder()
                .email(user.getUsername())
                .books(books)
                .build();
    }

    @Transactional
    public UserBooksResponse bindBook(BindBookRequest request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        Book bookToBind = bookRepository.findById(request.getBook_id().longValue()).orElseThrow(() -> new RuntimeException("Book not found"));

        user.getBooksList().add(bookToBind);

        user = userRepository.save(user);

        List<String> books = new ArrayList<>();

        for (var book:
                user.getBooksList()) {
            books.add(book.getTitle());
        }

        return UserBooksResponse.builder()
                .email(user.getUsername())
                .books(books)
                .build();
    }

}
