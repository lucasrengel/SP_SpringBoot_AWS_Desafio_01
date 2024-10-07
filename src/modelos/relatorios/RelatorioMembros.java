package modelos.relatorios;

import modelos.entidades.Membro;
import modelos.repositorios.MembroRepositorio;

import java.util.ArrayList;

public class RelatorioMembros implements Relatorio{

    private final MembroRepositorio membroRepositorio;

    public RelatorioMembros(MembroRepositorio membroRepositorio) {
        this.membroRepositorio = membroRepositorio;
    }

    @Override
    public void gerarRelatorio() {
        ArrayList<Membro> membros = membroRepositorio.getMinhaLista();

        if(membros.isEmpty()){
            System.out.println("\nNenhum Membro cadastrado");
            return;
        }

        for(Object m: MembroRepositorio.minhaLista) {
            System.out.println(m.toString());
        }
    }

}
