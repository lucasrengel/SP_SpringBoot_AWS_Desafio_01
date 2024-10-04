package modelos.entidades;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Autor extends Pessoa{

    private LocalDate dataNascimento;
    private String nacionalidade;
    private String biografia;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Autor() {
    }

    public Autor(String nome, LocalDate dataNascimento, String nacionalidade, String biografia) {
        super(nome);
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.biografia = biografia;
    }

    public Autor(int id, String nome, LocalDate dataNascimento, String nacionalidade, String biografia) {
        super(id, nome);
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.biografia = biografia;
    }

    public int getId() {
        return super.getId();
    }


    public String getNome() {
        return super.getNome();
    }

    public String getDataNascimento() {
        return dataNascimento.toString();
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = LocalDate.parse(dataNascimento);
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    @Override
    public String toString() {
        return super.toString() + ", Data de nascimento: " + getDataNascimento() + ", Nacionalidade: " + getNacionalidade() + ", Biografia: " + getBiografia() + ".\n";
    }
}
