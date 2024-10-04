package modelos.entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {

    private int id;
    private Livro livro;
    private Membro membro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private EstadoEmprestimo estado;
    private double multa;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Emprestimo() {
    }

    public Emprestimo(Livro livro, Membro membro, String dataEmprestimoStr, String dataDevolucaoStr, EstadoEmprestimo estado, double multa) {
        this.livro = livro;
        this.membro = membro;
        this.dataEmprestimo = LocalDate.parse(dataEmprestimoStr, formatter);
        this.dataDevolucao = LocalDate.parse(dataDevolucaoStr, formatter);
        this.estado = estado;
        this.multa = multa;
    }

    public Emprestimo(int id, Livro livro, Membro membro, String dataEmprestimoStr, String dataDevolucaoStr, EstadoEmprestimo estado, double multa) {
        this.id = id;
        this.livro = livro;
        this.membro = membro;
        this.dataEmprestimo = LocalDate.parse(dataEmprestimoStr, formatter);
        this.dataDevolucao = LocalDate.parse(dataDevolucaoStr, formatter);
        this.estado = estado;
        this.multa = multa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public EstadoEmprestimo getEstado() {
        return estado;
    }

    public void setEstado(EstadoEmprestimo estado) {
        this.estado = estado;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }
}
