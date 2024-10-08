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
                LocalDate dataDevolucao = null;
                if (res.getDate("dataDevolucao") != null) {
                    dataDevolucao = res.getDate("dataDevolucao").toLocalDate();
                }
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
            if (objeto.getDataDevolucao() != null) {
                stmt.setString(5, objeto.getDataDevolucao().toString());
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }
            stmt.setString(6, objeto.getEstado().toString());
            stmt.setBigDecimal(7, objeto.getMulta());



            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public boolean devolucao(Emprestimo objeto) {
        String sql = "UPDATE tb_emprestimos SET dataDevolucao = ?, estado = ?, multa = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getDataDevolucao().toString());
            stmt.setString(2, objeto.getEstado().toString());
            stmt.setBigDecimal(3, objeto.getMulta());
            stmt.setInt(4, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public boolean atualiza(Emprestimo objeto) {
        String sql = "UPDATE tb_emprestimos SET estado = ?, multa = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getEstado().toString());
            stmt.setBigDecimal(2, objeto.getMulta());
            stmt.setInt(3, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public Emprestimo buscaPorId(int id) {

        Emprestimo objeto = new Emprestimo();
        objeto.setId(id);

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_emprestimos WHERE id = " + id);
            res.next();

            objeto.setLivro(livroRepositorio.buscaPorId(res.getInt("id_livro")));
            objeto.setMembro(membroRepositorio.buscaPorId(res.getInt("id_membro")));
            objeto.setDataEmprestimo(LocalDate.parse(res.getString("dataEmprestimo")));
            objeto.setEstado(EstadoEmprestimo.valueOf(res.getString("estado")));
            objeto.setMulta(res.getBigDecimal("multa"));

            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return objeto;
    }
}
