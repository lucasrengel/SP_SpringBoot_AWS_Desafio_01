package aplicacao;

import bd.BD;
import excecoes.Mensagem;
import modelos.entidades.Autor;
import modelos.entidades.Membro;
import modelos.servicos.AutorServico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Programa {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        BD.getConexao();
        Scanner sc = new Scanner(System.in);

        int n = 0;

        while (n != 4) {
            System.out.println("1) Autor");
            System.out.println("4) Sair");
            System.out.print("Escolha uma opção: ");

            n = sc.nextInt();

            switch (n) {
                case 1:
                    AutorServico autorServico = new AutorServico();
                    System.out.println("\n1) Cadastrar Autor");
                    System.out.println("2) Imprimir Autores");

                    n = sc.nextInt();

                    if(n == 1){
                        try {
                            System.out.println("Digite o nome do autor: ");
                            sc.nextLine();
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

                            for (Autor a : autorServico.getMinhaLista()) {
                                if (a.getNome().equalsIgnoreCase(nome)) {
                                    throw new Mensagem("Esse autor já está cadastrado!");
                                }
                            }

                            Autor autor = new Autor(nome, dataNascimento, nacionalidade, biografia);

                            if (autorServico.cadastraAutor(autor)) {
                                System.out.println("Autor cadastrado com sucesso");
                            }
                        } catch (Mensagem erro) {
                            System.out.println(erro.getMessage());
                        }
                    }
                    if(n == 2){
                        if(autorServico.getMinhaLista().isEmpty()){
                            System.out.println("\nNenhum Autor cadastrado");
                            break;
                        }
                        for(Object a: AutorServico.minhaLista) {
                            System.out.println(a);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Você escolheu a Opção 2.");
                    break;
                case 3:
                    System.out.println("Você escolheu a Opção 3.");
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
