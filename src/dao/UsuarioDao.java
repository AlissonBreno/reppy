package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


import modelo.TipoUsuario;
import modelo.Usuario;

public class UsuarioDao{
	
	public void adiciona(Usuario p) throws ClassNotFoundException, SQLException {
		String sql = 
			"INSERT INTO Usuario ( login, senha, Nome, Telefone, idTipoUsuario )" +
		"VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1,  p.getLogin());
		comandoSql.setString(2, p.getSenha());
		comandoSql.setString(3, p.getNome());
		comandoSql.setString(4, p.getTelefone());
		comandoSql.setInt(5, p.getTipoUsuario().getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}

	public void atualiza(Usuario p) throws ClassNotFoundException, SQLException {
		String sql = 
			"UPDATE Usuario SET login=?, senha=?, Nome=?, "
			+ "Telefone=?, idTipoUsuario=? WHERE idUsuario=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1,  p.getLogin());
		comandoSql.setString(2, p.getSenha());
		comandoSql.setString(3, p.getNome());
		comandoSql.setString(4, p.getTelefone());
		comandoSql.setInt(5, p.getTipoUsuario().getId());
		comandoSql.setInt(6, p.getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public void remove(int id) throws ClassNotFoundException, SQLException {
		String sql = 
			"DELETE FROM Usuario WHERE idUsuario=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public List<Usuario> listaTodos() throws ClassNotFoundException, SQLException{
		List<Usuario> lista = new LinkedList<Usuario>();
		
		String sql = "SELECT * FROM Usuario inner join TipoUsuario on " +
					"usuario.idTipoUsuario = TipoUsuario.idTipoUsuario";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		
		ResultSet rs = comandoSql.executeQuery();
		
		while (rs.next()) {
			Usuario p = new Usuario();
			p.setId(rs.getInt("idUsuario"));
			p.setLogin(rs.getString("login"));
			p.setSenha(rs.getString("senha"));
			p.setNome(rs.getString("Nome"));
			p.setTelefone(rs.getString("Telefone"));
			
			TipoUsuario t = new TipoUsuario();
			
			t.setId(rs.getInt("idTipoUsuario"));
			t.setName(rs.getString("Nome"));
			
			p.setTipoUsuario(t);
			
			lista.add(p);
		}
		
		return lista;
	}
	
	public Usuario listaPorId(int id) throws ClassNotFoundException, SQLException{
		String sql = "SELECT * FROM Usuario WHERE idUsuario=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		ResultSet rs = comandoSql.executeQuery();
		
		Usuario p = null;
		
		if (rs.next()) {
			p = new Usuario();
			p.setId(rs.getInt("idUsuario"));
			p.setLogin(rs.getString("login"));
			p.setSenha(rs.getString("senha"));
			p.setNome(rs.getString("Nome"));
			p.setTelefone(rs.getString("Telefone"));
			
			TipoUsuario tu = new TipoUsuario();
			tu.setId(rs.getInt("idTipoUsuario"));
			
			p.setTipoUsuario(tu);
		}
		
		return p;
	}
}
