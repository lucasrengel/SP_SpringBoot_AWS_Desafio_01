package modelos.relatorios;

import modelos.entidades.Autor;
import modelos.repositorios.AutorRepositorio;

import java.util.ArrayList;

public class RelatorioAutores implements Relatorio{

    private final AutorRepositorio autorRepositorio;

    public RelatorioAutores(AutorRepositorio autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
    }

    @Override
    public void gerarRelatorio() {
        ArrayList<Autor> autores = autorRepositorio.getMinhaLista();

        if(autores.isEmpty()){
            System.out.println("\nNenhum Autor cadastrado");
            return;
        }

        for(Object a: AutorRepositorio.minhaLista) {
            System.out.println(a.toString());
        }
    }
}
