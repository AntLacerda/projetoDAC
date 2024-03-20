package jdbc.com.nReport.entities;

public class Delegacia {
    private Ocorrencia ocorrencia = new Ocorrencia();
    private Reu reu = new Reu();

    public Delegacia(){

    }

    public Delegacia(Ocorrencia ocorrencia, Reu reu){
        this.ocorrencia = ocorrencia;
        this.reu = reu;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Reu getReu() {
        return reu;
    }

    public void setReu(Reu reu) {
        this.reu = reu;
    }
}
