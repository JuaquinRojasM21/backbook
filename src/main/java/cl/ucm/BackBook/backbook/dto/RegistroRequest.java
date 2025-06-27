package cl.ucm.BackBook.backbook.dto;

import lombok.Data;

@Data
public class RegistroRequest{
    private String nombre;
    private String email;
    private String password;
    private String rol;
}