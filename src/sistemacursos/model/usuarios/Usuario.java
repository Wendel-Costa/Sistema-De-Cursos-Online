package sistemacursos.model.usuarios;

public abstract class Usuario {
    protected int id;
    protected String nome;
    protected String email;

    public Usuario(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public abstract String getTipo();
    
    public abstract String getUsuario();
}