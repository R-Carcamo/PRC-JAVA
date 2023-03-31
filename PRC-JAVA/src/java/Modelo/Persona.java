
package Modelo;

public class Persona {
    int idUser;
    int dni;
    String Titulo;
    String Cuerpo;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getCuerpo() {
        return Cuerpo;
    }

    public void setCuerpo(String Cuerpo) {
        this.Cuerpo = Cuerpo;
    }

    public Persona() {
    }

    public Persona(int idUser, int dni, String Titulo, String Cuerpo) {
        this.idUser = idUser;
        this.dni = dni;
        this.Titulo = Titulo;
        this.Cuerpo = Cuerpo;
    }
    
    
}
