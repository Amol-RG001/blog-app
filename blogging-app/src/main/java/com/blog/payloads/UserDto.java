package com.blog.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 12, message = "username must be min of 2 characters and max 12 characters.")
    private String name;

    @Email(message = "please check your email address once!")
    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,12}$" , message =" at least one uppercase letter, one lowercase letter, one number and one special character." )
    @Size(min = 4, max = 12, message = "Minimum 4 and maximum 12 characters.")
    private String password;

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 250, message = "username must be min of 5 characters and max 250 characters.")
    private String about;

}
