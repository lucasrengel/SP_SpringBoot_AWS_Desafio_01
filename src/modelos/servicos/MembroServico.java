package modelos.servicos;

import excecoes.Mensagem;
import modelos.entidades.Membro;
import modelos.relatorios.RelatorioAutores;
import modelos.relatorios.RelatorioMembros;
import modelos.repositorios.MembroRepositorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MembroServico {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final MembroRepositorio membroRepositorio = new MembroRepositorio();

    public void listarMembros(){
        RelatorioMembros relatorioMembros = new RelatorioMembros(membroRepositorio);
        relatorioMembros.gerarRelatorio();
    }

    public void cadastrarMembro(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Digite o nome do membro: ");
            String nome = sc.nextLine();
            System.out.println("Digite a data de associacao (dd/MM/yyyy): ");
            String dataAssociacaoStr  = sc.nextLine();
            System.out.println("Digite o email: ");
            String email = sc.nextLine();
            System.out.println("Digite o telefone: ");
            String telefone = sc.nextLine();
            System.out.println("Digite o endereco: ");
            String endereco = sc.nextLine();

            if (nome.length() < 2) {
                throw new Mensagem("Nome deve conter ao menos 2 caracteres");
            }

            LocalDate dataAssociacao;
            try {
                dataAssociacao = LocalDate.parse(dataAssociacaoStr, formatter);
            } catch (DateTimeParseException e) {
                throw new Mensagem("Formato de data inválido. Use dd/MM/yyyy.");
            }

            for (Membro m : membroRepositorio.getMinhaLista()) {
                if (m.getEmail().equalsIgnoreCase(email)) {
                    throw new Mensagem("Esse email ja foi usado!");
                }
            }

            Membro membro = new Membro(nome, dataAssociacao, email, telefone, endereco);

            if (membroRepositorio.salvar(membro)) {
                System.out.println("Membro cadastrado com sucesso");
            }
        } catch (Mensagem erro) {
            System.out.println(erro.getMessage());
        }
    }
}
