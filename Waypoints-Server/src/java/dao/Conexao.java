package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ailton Jr
 */

public class Conexao {
    // Parametros de conexão do Heroku
    private static final String host = "ec2-54-83-22-48.compute-1.amazonaws.com";
    private static final String porta = "5432";
    private static final String database = "d723u8srtesf3u";
    private static final String usuario = "pemsvgjsbvpezs";
    private static final String senha = "WvjudgCxJ4BbJJJIQa56gHKdr3";  
    
    // URL conexão banco
    private static final String banco = 
            "jdbc:postgresql://"+ host +":"+ porta +"/"+ database +"?sslmode=require&user="+ usuario +"&password="+ senha;
    
    // Driver a ser usado, nesse caso Postgres
    private static final String driver = "org.postgresql.Driver";
    private static Connection con = null;
    
    // Retorna uma conexão com o banco de dados
    public static Connection getConexao(){
        // Se o objeto con não foi inicializado
        if (con == null){
            try {
                // Driver a ser usado
                Class.forName(driver);
                // Conexão com o BD
                con = DriverManager.getConnection(banco);
            } catch (ClassNotFoundException ex) {
                System.out.println("Não encontrou o driver");
            } catch (SQLException ex) {
                System.out.println("Erro ao conectar com o banco: "+
                        ex.getMessage());
            }
        }
        return con;
    }
    
    // Recebe um comando SQL para ser executado
    public static PreparedStatement getPreparedStatement(String sql){
        // Se a conexao não foi inicializado
        if (con == null){
            // Cria a conexão
            con = getConexao();
        }
        try {
            // Retorna um objeto java.sql.PreparedStatement
            return con.prepareStatement(sql);
        } catch (SQLException e){
            System.out.println("Erro de sql: "+
                    e.getMessage());
        }
        return null;
    }
    
}