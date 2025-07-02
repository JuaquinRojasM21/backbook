package cl.ucm.BackBook.backbook.dto;

import lombok.Data;

@Data
public class RegistroAdminRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
}
