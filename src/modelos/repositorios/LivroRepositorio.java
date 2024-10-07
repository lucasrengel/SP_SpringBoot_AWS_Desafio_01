package modelos.repositorios;

import bd.BD;
import modelos.entidades.Autor;
import modelos.entidades.Livro;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class LivroRepositorio {
    public static ArrayList<Livro> minhaLista = new ArrayList<>();
    AutorRepositorio autorRepositorio = new AutorRepositorio();


    private Connection getConexao(){
        return BD.getConexao();
    }

    public ArrayList<Livro> getMinhaLista() {

        minhaLista.clear();

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_livros");
            while (res.next()) {

                int id = res.getInt("id");
                String titulo = res.getString("titulo");
                Autor autor = autorRepositorio.buscaPorId(res.getInt("id_autor"));
                LocalDate dataPublicacao = res.getDate("dataPublicacao").toLocalDate();
                Long isbn = res.getLong("isbn");
                String genero = res.getString("genero");
                int quantidade = res.getInt("quantidade");

                Livro objeto = new Livro( id,  titulo, autor,  dataPublicacao,  isbn,  genero, quantidade);

                minhaLista.add(objeto);
            }

            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return minhaLista;
    }

    public boolean salvar(Livro objeto) {
        String sql = "INSERT INTO tb_livros(id,  titulo, id_autor,  dataPublicacao,  isbn,  genero, quantidade) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getTitulo());
            stmt.setInt(3, objeto.getAutor().getId());
            stmt.setString(4, objeto.getDataPublicacao().toString());
            stmt.setString(5, objeto.getIsbn().toString());
            stmt.setString(6, objeto.getGenero());
            stmt.setInt(7, objeto.getQuantidade());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
