package by.aliyeva.zenart.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
//@RequiredArgsConstructor
public class UserController {

    public UserController(UserService service) {
        this.service = service;
    }

    private final UserService service;

    @PostMapping
    public ResponseEntity<String> createUser(
            @RequestBody @Valid UserRequest request
    ) {
        return ResponseEntity.ok(service.createUser(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(
            @RequestBody @Valid UserRequest request
    ) {
        service.updateUser(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/exists/{user-id}")
    public ResponseEntity<Boolean> existById(
            @PathVariable("user-id") String userId
    ) {
        return ResponseEntity.ok(service.existById(userId));
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable("user-id") String userId
    ) {
        return ResponseEntity.ok(service.findById(userId));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("user-id") String userId
    ) {
        service.deleteUser(userId);
        return ResponseEntity.accepted().build();
    }
}
