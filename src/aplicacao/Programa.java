package aplicacao;

import bd.BD;
import modelos.relatorios.RelatorioEmprestimo;
import modelos.repositorios.EmprestimoRepositorio;
import modelos.servicos.AutorServico;
import modelos.servicos.EmprestimoServico;
import modelos.servicos.LivroServico;
import modelos.servicos.MembroServico;

import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {
        BD.getConexao();
        Scanner sc = new Scanner(System.in);



        int n = 0;

        while (n != 6) {
            System.out.println("-------------");
            System.out.println("1) Autor");
            System.out.println("2) Membro");
            System.out.println("3) Livro");
            System.out.println("4) Emprestimo");
            System.out.println("5) Relatorios");
            System.out.println("6) Sair");
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
                    EmprestimoServico emprestimoServico = new EmprestimoServico();
                    System.out.println("\n1) Cadastrar Emprestimo");
                    System.out.println("2) Fazer Devolucao");
                    System.out.println("3) Imprimir Emprestimos");

                    n = sc.nextInt();

                    if (n == 1) {
                        emprestimoServico.cadastrarEmprestimo();
                    }
                    if (n == 2) {
                        emprestimoServico.fazerDevolucao();
                    }
                    if (n == 3) {
                        emprestimoServico.listarEmprestimos();
                    }
                    break;
                case 5:
                    RelatorioEmprestimo relatorioEmprestimo = new RelatorioEmprestimo(new EmprestimoRepositorio());
                    System.out.println("1) Livros Emprestados");
                    System.out.println("2) Membros com multa");

                    n = sc.nextInt();

                    if (n == 1) {
                        relatorioEmprestimo.gerarLivrosEmprestados();
                    }
                    if (n == 2) {
                        relatorioEmprestimo.gerarMembrosComMultas();
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println();
        }


    }
}
