package dto;

public class OperarioDTO {

    private String nombreCompleto;
    private String username;
    private String password;
    private boolean activo;

    public OperarioDTO(String nombreCompleto, String username, String password, boolean activo) {
        this.nombreCompleto = nombreCompleto;
        this.username = username;
        this.password = password;
        this.activo = activo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
