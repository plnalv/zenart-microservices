package by.aliyeva.zenart.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
    String id,
    @NotNull(message = "User firstname is required")
    String firstName,
    @NotNull(message = "User firstname is required")
    String lastName,
    @NotNull(message = "User Email is required")
    @Email(message = "User email is not valid")
    String email,
    String phone
) {
    
}
