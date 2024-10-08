package modelos.servicos;

import excecoes.Mensagem;
import modelos.entidades.Emprestimo;
import modelos.enumeracoes.EstadoEmprestimo;
import modelos.relatorios.RelatorioEmprestimo;
import modelos.repositorios.EmprestimoRepositorio;
import modelos.repositorios.LivroRepositorio;
import modelos.repositorios.MembroRepositorio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EmprestimoServico {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final EmprestimoRepositorio emprestimoRepositorio = new EmprestimoRepositorio();
    private final LivroRepositorio livroRepositorio = new LivroRepositorio();
    private final MembroRepositorio membroRepositorio = new MembroRepositorio();
    Scanner sc = new Scanner(System.in);

    public void listarEmprestimos(){
        RelatorioEmprestimo relatorioEmprestimo = new RelatorioEmprestimo(emprestimoRepositorio);
        relatorioEmprestimo.gerarRelatorio();
    }

    public void cadastrarEmprestimo(){

        try {
            System.out.print("Digite o id do Livro: ");
            int idLivro = sc.nextInt();
            System.out.print("Digite o id do Membro: ");
            int idMembro = sc.nextInt();
            System.out.print("Digite a data do emprestimo(dd/MM/yyyy): ");
            sc.nextLine();
            String dataEmprestimoStr = sc.nextLine();

            LocalDate dataEmprestimo;

            BigDecimal multaPendente = emprestimoRepositorio.buscaPorId(idMembro).getMulta();
            if (multaPendente.compareTo(BigDecimal.ZERO) > 0) {
                throw new Mensagem("Voce tem multas pendentes, pague antes de fazer um novo emprestimo");
            }


            try {
                dataEmprestimo = LocalDate.parse(dataEmprestimoStr, formatter);
            } catch (DateTimeParseException e) {
                throw new Mensagem("Formato de data inválido. Use dd/MM/yyyy.");
            }

            LocalDate dataDevolucao = null;
            EstadoEmprestimo estado = EstadoEmprestimo.valueOf("ATIVO");


            for (Emprestimo e : emprestimoRepositorio.getMinhaLista()) {
                if (e.getLivro().getQuantidade() <= 0) {
                    throw new Mensagem("Esse livro nao esta disponivel!");
                }
            }

            Emprestimo emprestimo = new Emprestimo(livroRepositorio.buscaPorId(idLivro), membroRepositorio.buscaPorId(idMembro), dataEmprestimo, dataDevolucao, estado, new BigDecimal(0));

            if (emprestimoRepositorio.salvar(emprestimo)) {
                System.out.println("Emprestimo cadastrado com sucesso");
            }
        } catch (Mensagem erro) {
            System.out.println(erro.getMessage());
        }
    }

    public void fazerDevolucao() {
        try {
            System.out.print("Digite o id do Emprestimo: ");
            int id = sc.nextInt();
            System.out.print("Digite a data de Devolucao(dd/MM/yyyy): ");
            sc.nextLine();
            String dataDevolucaoStr = sc.nextLine();

            LocalDate dataDevolucao;
            try {
                dataDevolucao = LocalDate.parse(dataDevolucaoStr, formatter);
            } catch (DateTimeParseException e) {
                throw new Mensagem("Formato de data inválido. Use dd/MM/yyyy.");
            }

            Emprestimo emprestimo = emprestimoRepositorio.buscaPorId(id);
            LocalDate dataLimite = emprestimo.getDataEmprestimo().plusDays(10);

            BigDecimal multa = BigDecimal.ZERO;
            BigDecimal valorPorMulta = new BigDecimal("5.00");

            if(dataDevolucao.isAfter(dataLimite)){
                int diasAtraso = dataDevolucao.compareTo(dataLimite);
                multa = valorPorMulta.multiply(new BigDecimal(diasAtraso));
                System.out.println("Atraso de " + diasAtraso + " dias. Multa: R$ " + multa);
                System.out.println("\nVoce deseja pagar a multa?(s/n)");
                String opcao = sc.nextLine();
                if(opcao.equals("s")){
                    emprestimo.setDataDevolucao(dataDevolucao);
                    emprestimo.setEstado(EstadoEmprestimo.CONCLUIDO);
                    emprestimo.setMulta(multa);

                    if(emprestimoRepositorio.devolucao(emprestimo)){
                        System.out.println("Devolução recebida com sucesso.");
                    }
                }
                if(opcao.equals("n")){
                    emprestimo.setEstado(EstadoEmprestimo.ATRASADO);
                    emprestimo.setMulta(multa);
                    if(emprestimoRepositorio.atualiza(emprestimo)){
                        System.out.println("Devolução nao foi concluida.");
                    }
                }
            }else{
                System.out.println("Devolucao dentro do prazo.");

                if(emprestimoRepositorio.devolucao(emprestimo)){
                    System.out.println("Devolução recebida com sucesso");
                }
            }

        } catch (Mensagem erro) {
            System.out.println(erro.getMessage());
        }
    }
}
