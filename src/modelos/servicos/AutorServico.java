package modelos.servicos;

import excecoes.Mensagem;
import modelos.entidades.Autor;
import modelos.repositorios.AutorRepositorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AutorServico {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final AutorRepositorio autorRepositorio = new AutorRepositorio();

    public void listarAutores(){
        if(autorRepositorio.getMinhaLista().isEmpty()){
            System.out.println("\nNenhum Autor cadastrado");
            return;
        }
        for(Object a: AutorRepositorio.minhaLista) {
            System.out.println(a);
        }
    }

    public void cadastrarAutor(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Digite o nome do autor: ");
            String nome = sc.nextLine();
            System.out.println("Digite a data de nascimento: ");
            String dataNascimentoStr  = sc.nextLine();
            System.out.println("Digite a nacionalidade: ");
            String nacionalidade = sc.nextLine();
            System.out.println("Digite a biografia: ");
            String biografia = sc.nextLine();

            if (nome.length() < 2) {
                throw new Mensagem("Nome deve conter ao menos 2 caracteres");
            }

            LocalDate dataNascimento;
            try {
                dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
            } catch (DateTimeParseException e) {
                throw new Mensagem("Formato de data inválido. Use dd/MM/yyyy.");
            }

            for (Autor a : autorRepositorio.getMinhaLista()) {
                if (a.getNome().equalsIgnoreCase(nome)) {
                    throw new Mensagem("Esse autor já está cadastrado!");
                }
            }

            Autor autor = new Autor(nome, dataNascimento, nacionalidade, biografia);

            if (autorRepositorio.salvar(autor)) {
                System.out.println("Autor cadastrado com sucesso");
            }
        } catch (Mensagem erro) {
            System.out.println(erro.getMessage());
        }
    }
}