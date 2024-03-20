package jdbc.com.nReport.entities;

public class Delegado {
    private int id;
    private String matricula;
    private String nome;
    private String contato;

    public Delegado() {

    }

    public Delegado(int id, String matricula, String nome, String contato) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.contato = contato;
    }

    public Delegado(String matricula, String nome, String contato) {
        this.matricula = matricula;
        this.nome = nome;
        this.contato = contato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
