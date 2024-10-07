package modelos.repositorios;

import bd.BD;
import modelos.entidades.Autor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AutorRepositorio {

    public static ArrayList<Autor> minhaLista = new ArrayList<>();


    private Connection getConexao(){
        return BD.getConexao();
    }

    public ArrayList<Autor> getMinhaLista() {

        minhaLista.clear();

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_autores");
            while (res.next()) {

                int id = res.getInt("id");
                String nome = res.getString("nome");
                LocalDate dataNascimento = res.getDate("dataNascimento").toLocalDate();
                String nacionalidade = res.getString("nacionalidade");
                String biografia = res.getString("biografia");

                Autor objeto = new Autor( id,  nome,  dataNascimento,  nacionalidade,  biografia);

                minhaLista.add(objeto);
            }

            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return minhaLista;
    }

    public boolean salvar(Autor objeto) {
        String sql = "INSERT INTO tb_autores(id,nome,dataNascimento, nacionalidade, biografia) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setDate(3, Date.valueOf(objeto.getDataNascimento()));
            stmt.setString(4, objeto.getNacionalidade());
            stmt.setString(5, objeto.getBiografia());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public Autor buscaPorId(int id) {

        Autor objeto = new Autor();
        objeto.setId(id);

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_autores WHERE id = " + id);
            res.next();

            String nome = res.getString("nome");
            LocalDate dataNascimento = res.getDate("dataNascimento").toLocalDate();
            String nacionalidade = res.getString("nacionalidade");
            String biografia = res.getString("biografia");

            objeto.setNome(nome);

            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return objeto;
    }
}