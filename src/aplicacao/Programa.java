package aplicacao;

import bd.BD;
import modelos.servicos.AutorServico;
import modelos.servicos.LivroServico;
import modelos.servicos.MembroServico;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Programa {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        BD.getConexao();
        Scanner sc = new Scanner(System.in);

        int n = 0;

        while (n != 5) {
            System.out.println("-------------");
            System.out.println("1) Autor");
            System.out.println("2) Membro");
            System.out.println("3) Livro");
            System.out.println("4) Emprestimo");
            System.out.println("5) Sair");
            System.out.println("-------------");
            System.out.print("Escolha uma opção: ");

            n = sc.nextInt();

            switch (n) {
                case 1:
                    AutorServico autorServico = new AutorServico();
                    System.out.println("\n1) Cadastrar Autor");
                    System.out.println("2) Imprimir Autores");

                    n = sc.nextInt();

                    if (n == 1) {
                        autorServico.cadastrarAutor();
                    }
                    if (n == 2) {
                        autorServico.listarAutores();
                    }
                    break;
                case 2:
                    MembroServico membroServico = new MembroServico();
                    System.out.println("\n1) Cadastrar Membro");
                    System.out.println("2) Imprimir Membros");

                    n = sc.nextInt();

                    if (n == 1) {
                        membroServico.cadastrarMembro();
                    }
                    if (n == 2) {
                        membroServico.listarMembros();
                    }
                    break;
                case 3:
                    LivroServico livroServico = new LivroServico();
                    System.out.println("\n1) Cadastrar Livro");
                    System.out.println("2) Imprimir Livros");

                    n = sc.nextInt();

                    if (n == 1) {
                        livroServico.cadastrarLivro();
                    }
                    if (n == 2) {
                        livroServico.listarLivros();
                    }
                    break;
                case 4:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println(); // Linha em branco para separar cada iteração
        }


    }
}
