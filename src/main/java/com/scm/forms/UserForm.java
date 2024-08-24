package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Should not be blank")
    public String name;
    @NotBlank(message = "Please enter your email")
    @Email(message = "Email is required")
    public String email;
    @NotBlank
    @Size(min = 6, max = 20, message = "Minimum 6 characters required")
    public String password;
    @Size(min = 10, max = 10, message = "10 characters required")
    @Positive
    public String phoneNumber;
    @Size(min = 20, max = 1000, message = "Minimum 20 characters")
    public String about;

    // @Valid required in the function args, where you want to validate
}
