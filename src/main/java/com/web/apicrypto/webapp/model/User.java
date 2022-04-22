package com.web.apicrypto.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
public class User {
    @Id
    private String id;
    @Email
    @NotEmpty(message = "Email is required")
    @Field("username")
    private String username;
    @NotBlank(message = "Password is required")
    @Field("password")
    private String password;
    @Field("created")
    private LocalDateTime created;
    @Field("enabled")
    private boolean enabled;
}
