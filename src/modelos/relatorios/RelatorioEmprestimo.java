package modelos.relatorios;

import modelos.entidades.Emprestimo;
import modelos.repositorios.EmprestimoRepositorio;

import java.util.ArrayList;

public class RelatorioEmprestimo implements Relatorio{

    private final EmprestimoRepositorio emprestimoRepositorio;

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
}
