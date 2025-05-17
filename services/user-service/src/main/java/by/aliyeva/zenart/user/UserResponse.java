package by.aliyeva.zenart.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
