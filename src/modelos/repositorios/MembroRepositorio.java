package modelos.repositorios;

import bd.BD;
import modelos.entidades.Membro;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MembroRepositorio {

    public static ArrayList<Membro> minhaLista = new ArrayList<>();


    private Connection getConexao(){
        return BD.getConexao();
    }

    public ArrayList<Membro> getMinhaLista() {

        minhaLista.clear();

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_membros");
            while (res.next()) {

                int id = res.getInt("id");
                String nome = res.getString("nome");
                LocalDate dataAssociacao = res.getDate("dataAssociacao").toLocalDate();
                String email = res.getString("email");
                String telefone = res.getString("telefone");
                String endereco = res.getString("endereco");

                Membro objeto = new Membro( id,  nome,  dataAssociacao,  email,  telefone, endereco);

                minhaLista.add(objeto);
            }

            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return minhaLista;
    }

    public boolean salvar(Membro objeto) {
        String sql = "INSERT INTO tb_membros(id,nome,dataAssociacao, email, telefone, endereco) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setDate(3, Date.valueOf(objeto.getDataAssociacao()));
            stmt.setString(4, objeto.getEmail());
            stmt.setString(5, objeto.getTelefone());
            stmt.setString(6, objeto.getEndereco());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public Membro buscaPorId(int id) {

        Membro objeto = new Membro();
        objeto.setId(id);

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_membros WHERE id = " + id);
            res.next();

            objeto.setNome(res.getString("nome"));

            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return objeto;
    }
}
