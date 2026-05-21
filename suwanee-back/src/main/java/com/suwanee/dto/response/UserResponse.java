package com.suwanee.dto.response;

import com.suwanee.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private UUID id;
    private String email;
    private LocalDateTime createdAt;

    public static UserResponse from(User user){
        UserResponse response = new UserResponse();
        response.id = user.getId();
        response.email = user.getEmail();
        response.createdAt = user.getCreatedAt();
        return response;
    }
}
