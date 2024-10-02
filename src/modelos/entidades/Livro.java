package modelos.entidades;

import java.time.LocalDate;

public class Livro {
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private Long isbn;
    private String genero;
    private int quantidade;

    public Livro() {
    }

    public Livro(String titulo, String autor, LocalDate dataPublicacao, Long isbn, String genero, int quantidade) {
        this.titulo = titulo;
        this.autor = autor;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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
}
