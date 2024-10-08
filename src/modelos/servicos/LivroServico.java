package modelos.servicos;

import excecoes.Mensagem;
import modelos.entidades.Livro;
import modelos.relatorios.RelatorioLivros;
import modelos.repositorios.AutorRepositorio;
import modelos.repositorios.LivroRepositorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class LivroServico {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final LivroRepositorio livroRepositorio = new LivroRepositorio();
    private final AutorRepositorio autorRepositorio = new AutorRepositorio();

    public void listarLivros(){
        RelatorioLivros relatorioLivros = new RelatorioLivros(livroRepositorio);
        relatorioLivros.gerarRelatorio();
    }

    public void cadastrarLivro(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Digite o titulo do livro: ");
            String titulo = sc.nextLine();
            System.out.print("Digite o id do autor: ");
            int idAutor = sc.nextInt();
            System.out.print("Digite a data de publicacao(dd/MM/yyyy): ");
            sc.nextLine();
            String dataPublicacaoStr = sc.nextLine();
            System.out.print("Digite o isbn: ");
            Long isbn = sc.nextLong();
            System.out.print("Digite o genero: ");
            sc.nextLine();
            String genero = sc.nextLine();
            System.out.print("Digite a quantidade de livros: ");
            int quantidade = sc.nextInt();


            if (titulo.length() < 2) {
                throw new Mensagem("Titulo deve conter ao menos 2 caracteres");
            }

            LocalDate dataPublicacao;
            try {
                dataPublicacao = LocalDate.parse(dataPublicacaoStr, formatter);
            } catch (DateTimeParseException e) {
                throw new Mensagem("Formato de data inválido. Use dd/MM/yyyy.");
            }

            for (Livro l : livroRepositorio.getMinhaLista()) {
                if (l.getIsbn().equals(isbn)) {
                    throw new Mensagem("Esse isbn ja esta sendo utilizado!");
                }
            }

            Livro livro = new Livro(titulo, autorRepositorio.buscaPorId(idAutor),  dataPublicacao,  isbn,  genero, quantidade);

            if (livroRepositorio.salvar(livro)) {
                System.out.println("Livro cadastrado com sucesso");
            }
        } catch (Mensagem erro) {
            System.out.println(erro.getMessage());
        }
    }
}
