package org.example.console.model;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String name;
    private String email;
    private int age;

    @Setter(AccessLevel.NONE)
    private final LocalDateTime createdAt = LocalDateTime.now();
}
