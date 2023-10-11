package br.com.samuel.learningspring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {

    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 92, message = "Name must be between 3 and 92 characters")
    private String name;

    @NotEmpty(message = "CPF is required")
    @Size(min = 11, max = 11, message = "CPF must be 11 characters")
    private String cpf;

    @NotEmpty(message = "Email is required")
    @Size(min = 3, max = 62, message = "Email must be between 3 and 62 characters")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotEmpty(message = "Type is required")
    @Pattern(regexp = "(COMMON|SELLER)", message = "Type must be COMMON or SELLER (case sensitive)")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
