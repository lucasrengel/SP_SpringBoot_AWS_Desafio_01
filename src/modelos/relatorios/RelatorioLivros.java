package modelos.relatorios;

import modelos.entidades.Livro;
import modelos.repositorios.LivroRepositorio;

import java.util.ArrayList;

public class RelatorioLivros implements Relatorio {

    private final LivroRepositorio livroRepositorio;

    public RelatorioLivros(LivroRepositorio livroRepositorio) {
        this.livroRepositorio = livroRepositorio;
    }

    @Override
    public void gerarRelatorio() {
        ArrayList<Livro> livros = livroRepositorio.getMinhaLista();

        if(livros.isEmpty()){
            System.out.println("\nNenhum Autor cadastrado");
            return;
        }

        for(Object l: LivroRepositorio.minhaLista) {
            System.out.println(l.toString());
        }
    }
}
