package org.dinque.bookapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addNewUser(
            @RequestBody UserRequest request
    ){
        return ResponseEntity.ok(userService.addUser(request));
    }

    @GetMapping("/books")
    public ResponseEntity<UserBooksResponse> userBooks(){
        return ResponseEntity.ok(userService.getUserBooks());
    }

    @PostMapping("/bind-book")
    public ResponseEntity<UserBooksResponse> bindBookToUser(
            @RequestBody BindBookRequest request
    ){
        return ResponseEntity.ok(userService.bindBook(request));
    }

}
