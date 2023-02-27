package com.hoffmann.lotecaatualizada.domain.request;

public class UserRegisterRequest {

    private String name;
    private String cpf;
    private String surname;
    private String password;
    private String cellphone;
    private String email;

    public UserRegisterRequest() {
    }

    public UserRegisterRequest(String name, String cpf, String surname, String password, String cellphone, String email) {
        this.name = name;
        this.cpf = cpf;
        this.surname = surname;
        this.password = password;
        this.cellphone = cellphone;
        this.email = email;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
