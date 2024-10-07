package modelos.repositorios;

import bd.BD;
import modelos.entidades.Emprestimo;
import modelos.entidades.Livro;
import modelos.entidades.Membro;
import modelos.enumeracoes.EstadoEmprestimo;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmprestimoRepositorio {

    public static ArrayList<Emprestimo> minhaLista = new ArrayList<>();
    LivroRepositorio livroRepositorio = new LivroRepositorio();
    MembroRepositorio membroRepositorio = new MembroRepositorio();

    private Connection getConexao(){
        return BD.getConexao();
    }

    public ArrayList<Emprestimo> getMinhaLista() {

        minhaLista.clear();

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_emprestimos");
            while (res.next()) {

                int id = res.getInt("id");
                Livro livro = livroRepositorio.buscaPorId(res.getInt("id_livro"));
                Membro membro = membroRepositorio.buscaPorId(res.getInt("id_membro"));
                LocalDate dataEmprestimo = res.getDate("dataEmprestimo").toLocalDate();
                LocalDate dataDevolucao = res.getDate("dataDevolucao").toLocalDate();
                EstadoEmprestimo estado = EstadoEmprestimo.valueOf(res.getString("estado"));
                BigDecimal multa = res.getBigDecimal("multa");

                Emprestimo objeto = new Emprestimo(id, livro, membro, dataEmprestimo, dataDevolucao, estado, multa);

                minhaLista.add(objeto);
            }

            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return minhaLista;
    }

    public boolean salvar(Emprestimo objeto) {
        String sql = "INSERT INTO tb_emprestimos(id, id_livro, id_membro, dataEmprestimo, dataDevolucao, estado, multa) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setInt(2, objeto.getLivro().getId());
            stmt.setInt(3, objeto.getMembro().getId());
            stmt.setString(4, objeto.getDataEmprestimo().toString());
            stmt.setString(5, objeto.getDataDevolucao().toString());
            stmt.setString(6, objeto.getEstado().toString());
            stmt.setBigDecimal(7, objeto.getMulta());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
