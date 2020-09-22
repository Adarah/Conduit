package com.mag.conduit.application.dto.form;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.mag.conduit.infrastructure.validator.DefaultBeforeExpensiveSequence;
import com.mag.conduit.infrastructure.validator.Expensive;
import com.mag.conduit.infrastructure.validator.UniqueEmail;
import com.mag.conduit.infrastructure.validator.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@JsonRootName("user")
@NoArgsConstructor
public class SignupForm {
    @NotBlank(message = "{username.not_blank}")
    @Size(min = 3, max = 20, message = "{username.size}")
    @Pattern(regexp = "^[A-Za-z]+$", message = "{username.alpha}")
    @UniqueUsername(groups = Expensive.class)
    private String username;

    @NotBlank(message = "{email.not_blank}")
    @Email(regexp = "^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$",
            message = "{email.valid}")
    @UniqueEmail(groups = Expensive.class)
    private String email;

    @NotBlank(message = "{password.not_blank}")
    @Size(min = 6, message = "{password.size}")
    private String password;
}
