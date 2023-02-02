package com.hoffmann.lotecaatualizada.utilitario;

public class UserArgs {
    private String email;
    private String token;

    public UserArgs(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
