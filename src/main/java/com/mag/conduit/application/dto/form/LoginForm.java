package com.mag.conduit.application.dto.form;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.mag.conduit.infrastructure.validator.Login;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@JsonRootName("user")
@NoArgsConstructor
@Login
public class LoginForm {
    @NotBlank(message = "{email.not_blank}")
    @Email(regexp = "^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$",
            message = "{email.valid}")
    private String email;

    @NotBlank(message = "{password.not_blank}")
    @Size(min = 6, message = "{password.size}")
    private String password;
}
