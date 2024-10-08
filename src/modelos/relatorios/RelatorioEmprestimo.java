package modelos.relatorios;

import modelos.entidades.Emprestimo;
import modelos.entidades.Membro;
import modelos.enumeracoes.EstadoEmprestimo;
import modelos.repositorios.EmprestimoRepositorio;
import modelos.repositorios.MembroRepositorio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RelatorioEmprestimo implements Relatorio{

    private final EmprestimoRepositorio emprestimoRepositorio;
    private final MembroRepositorio membroRepositorio = new MembroRepositorio();

    public RelatorioEmprestimo(EmprestimoRepositorio emprestimoRepositorio) {
        this.emprestimoRepositorio = emprestimoRepositorio;
    }

    @Override
    public void gerarRelatorio() {
        ArrayList<Emprestimo> emprestimos = emprestimoRepositorio.getMinhaLista();

        if(emprestimos.isEmpty()){
            System.out.println("Nenhum emprestimo cadastrado");
            return;
        }

        for (Emprestimo e : emprestimos) {
            System.out.println(e.toString());
        }
    }

    public void gerarLivrosEmprestados() {
        System.out.println("Relatorio de Livros Emprestados:");
        for (Emprestimo e : emprestimoRepositorio.getMinhaLista()) {
            if (e.getEstado() == EstadoEmprestimo.ATIVO) {
                System.out.println("Livro: " + e.getLivro().getTitulo() + ", Membro: " + e.getMembro().getNome() +
                        ", Data de Empréstimo: " + e.getDataEmprestimo());
            }
        }
    }

    public void gerarMembrosComMultas() {
        System.out.println("Relatório de Membros com Multas:");

        Map<Integer, BigDecimal> multasPorMembro = new HashMap<>();

        for (Emprestimo emprestimo : emprestimoRepositorio.getMinhaLista()) {
            BigDecimal multa = emprestimo.getMulta();
            if (multa.compareTo(BigDecimal.ZERO) > 0) {
                int idMembro = emprestimo.getMembro().getId();
                multasPorMembro.put(idMembro, multasPorMembro.getOrDefault(idMembro, BigDecimal.ZERO).add(multa));
            }
        }

        for (Map.Entry<Integer, BigDecimal> entry : multasPorMembro.entrySet()) {
            Membro membro = membroRepositorio.buscaPorId(entry.getKey());
            BigDecimal totalMulta = entry.getValue();
            System.out.println("Membro: " + membro.getNome() + ", Total de Multas: R$ " + totalMulta);
        }
    }
}
