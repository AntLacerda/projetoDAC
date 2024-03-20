package jdbc.com.nReport.entities;

public class Ocorrencia {
    private int id;
    private String data;
    private String hora;
    private String local;
    private String tipoCrime;
    private int idDelegado;

    public Ocorrencia() {

    }

    public Ocorrencia(int id, String data, String hora, String local, String tipoCrime, int idDelegado) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.tipoCrime = tipoCrime;
        this.idDelegado = idDelegado;
    }

    public Ocorrencia(String data, String hora, String local, String tipoCrime, int idDelegado) {
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.tipoCrime = tipoCrime;
        this.idDelegado = idDelegado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipoCrime() {
        return tipoCrime;
    }

    public void setTipoCrime(String tipoCrime) {
        this.tipoCrime = tipoCrime;
    }

    public int getIdDelegado() {
        return idDelegado;
    }

    public void setIdDelegado(int idDelegado) {
        this.idDelegado = idDelegado;
    }
}
