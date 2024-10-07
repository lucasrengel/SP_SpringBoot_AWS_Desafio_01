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

    public void listarEmprestimos(){
        RelatorioEmprestimo relatorioEmprestimo = new RelatorioEmprestimo(emprestimoRepositorio);
        relatorioEmprestimo.gerarRelatorio();
    }

    public void cadastrarEmprestimo(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Digite o id do Livro: ");
            int idLivro = sc.nextInt();
            System.out.print("Digite o id do Membro: ");
            int idMembro = sc.nextInt();
            System.out.print("Digite a data do emprestimo(dd/MM/yyyy): ");
            sc.nextLine();
            String dataEmprestimoStr = sc.nextLine();
            System.out.print("Digite a data da devolucao(dd/MM/yyyy): ");
            String dataDevolucaoStr = sc.nextLine();
            System.out.print("Digite o estado(ATIVO, CONCLUIDO, ATRASADO): ");
            EstadoEmprestimo estado = EstadoEmprestimo.valueOf(sc.next());
            System.out.print("Digite a multa: ");
            sc.nextLine();
            BigDecimal multa = sc.nextBigDecimal();


            LocalDate dataEmprestimo;
            LocalDate dataDevolucao;
            try {
                dataEmprestimo = LocalDate.parse(dataEmprestimoStr, formatter);
                dataDevolucao = LocalDate.parse(dataDevolucaoStr, formatter);
            } catch (DateTimeParseException e) {
                throw new Mensagem("Formato de data inválido. Use dd/MM/yyyy.");
            }


            for (Emprestimo e : emprestimoRepositorio.getMinhaLista()) {
                if (e.getLivro().getQuantidade() <= 0) {
                    throw new Mensagem("Esse livro nao esta disponivel!");
                }
            }

            Emprestimo emprestimo = new Emprestimo(livroRepositorio.buscaPorId(idLivro), membroRepositorio.buscaPorId(idMembro), dataEmprestimo, dataDevolucao, estado, multa);

            if (emprestimoRepositorio.salvar(emprestimo)) {
                System.out.println("Emprestimo cadastrado com sucesso");
            }
        } catch (Mensagem erro) {
            System.out.println(erro.getMessage());
        }
    }
}
