package modelos.entidades;

import java.time.LocalDate;

public class Livro {

    private int id;
    private String titulo;
    private Autor idAutor;
    private LocalDate dataPublicacao;
    private Long isbn;
    private String genero;
    private int quantidade;

    public Livro() {
    }

    public Livro(String titulo, Autor idAutor, LocalDate dataPublicacao, Long isbn, String genero, int quantidade) {
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.dataPublicacao = dataPublicacao;
        this.isbn = isbn;
        this.genero = genero;
        this.quantidade = quantidade;
    }

    public Livro(int id, String titulo, Autor idAutor, LocalDate dataPublicacao, Long isbn, String genero, int quantidade) {
        this.id = id;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.dataPublicacao = dataPublicacao;
        this.isbn = isbn;
        this.genero = genero;
        this.quantidade = quantidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return idAutor;
    }

    public void setAutor(Autor idAutor) {
        this.idAutor = idAutor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Titulo: " + getTitulo() + ", Autor: " +
                this.idAutor.getNome() + ", Data de publicacao: " +
                getDataPublicacao() + ", ISBN: " + getIsbn()
                + ", Genero: " + getGenero() + ", Quantidade: " +
                getQuantidade() + ".\n";
    }
}
