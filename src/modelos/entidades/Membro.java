package modelos.entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Membro extends Pessoa {

    private String endereco;
    private String telefone;
    private String email;
    private LocalDate dataAssociacao;
    private List<Emprestimo> emprestimos = new ArrayList<>();


    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Membro() {
    }

    public Membro(String nome, LocalDate dataAssociacao, String email, String telefone, String endereco) {
        super(nome);
        this.dataAssociacao = dataAssociacao;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Membro(int id, String nome, LocalDate dataAssociacao, String email, String telefone, String endereco) {
        super(id, nome);
        this.dataAssociacao = dataAssociacao;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public int getId() {
        return super.getId();
    }


    public String getNome() {
        return super.getNome();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataAssociacao() {
        return dataAssociacao;
    }

    public void setDataAssociacao(LocalDate dataAssociacao) {
        this.dataAssociacao = dataAssociacao;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    @Override
    public String toString() {
        return super.toString() + ", Data de associacao: " +
                getDataAssociacao() + ", Email: " + getEmail() +
                ", Telefone: " + getTelefone() + ", Endereco: " + getEndereco() + ".\n";
    }
}
