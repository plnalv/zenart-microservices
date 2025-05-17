package by.aliyeva.zenart.user;

import by.aliyeva.zenart.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public String createUser(UserRequest request) {
        var user = repository.save(mapper.toUser(request));
        return user.getId();
    }

    public void updateUser(UserRequest request) {
        var user = repository.findById(request.id())
                .orElseThrow(() -> new UserNotFoundException(
                    format("Cannot update user:: Not found user with id: " + request.id())
                ));
        mergerUser(user, request);
        repository.save(user);
    }

    private void mergerUser(User user, UserRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            user.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            user.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            user.setEmail(request.email());
        }
        if (request.phone() != null) {
            user.setPhone(request.phone());
        }
    }

    public List<UserResponse> findAllUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromUser)
                .collect(Collectors.toList());
    }

    public Boolean existById(String userId) {
        return repository.findById(userId)
                .isPresent();
    }

    public UserResponse findById(String userId) {
        return repository.findById(userId)
                .map(mapper::fromUser)
                .orElseThrow(() -> new UserNotFoundException(
                        format("No user found with the id: " + userId)
                ));
    }

    public void deleteUser(String userId) {
        repository.deleteById(userId);
    }
}
