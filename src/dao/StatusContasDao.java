package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.Limpeza;
import modelo.StatusContas;
import modelo.StatusLimpeza;
import modelo.Usuario;

public class StatusContasDao{
	
	public void adiciona(StatusContas s) throws ClassNotFoundException, SQLException {
		String sql = 
			"INSERT INTO statusContas ( status,  idUsuario, idLimpeza )" +
		"VALUES (?, ?, ?)";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setBoolean(1,  s.getStatus());
		comandoSql.setInt(2, s.getUsuario().getId());
		comandoSql.setInt(3, s.getLimpeza().getId());
		
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}

	public void atualiza(StatusContas s) throws ClassNotFoundException, SQLException {
		String sql = 
			"UPDATE StatusLimpeza SET StatusLimpeza=?, idUsuario=?, idLimpeza=?, "
			+ " WHERE idStatusLimpeza=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setBoolean(1,  s.getStatus());
		comandoSql.setInt(2, s.getUsuario().getId());
		comandoSql.setInt(3, s.getLimpeza().getId());
		comandoSql.setInt(4, s.getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public void remove(int id) throws ClassNotFoundException, SQLException {
		String sql = 
			"DELETE FROM StatusLimpeza WHERE idStatusLimpeza=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public List<StatusContas> listaTodos() throws ClassNotFoundException, SQLException{
		List<StatusLimpeza> lista = new LinkedList<StatusLimpeza>();
		
		String sql = "SELECT * FROM StatusLimpeza inner join Usuario on " +
					"StatusLimpeza.idUsuario = Usuario.idUsuario"+
					"inner join Limpeza" +
					"StatusLimpeza.idLimpeza = Limpeza.idLimpeza";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		
		ResultSet rs = comandoSql.executeQuery();
		
		while (rs.next()) {
			StatusContas p = new StatusContas();
			p.setId(rs.getInt("idStatusContas"));
			p.setTipoContas(rs.getInt("TipoContas"));
			
			Usuario u = new Usuario();
			
			u.setId(rs.getInt("idUsuario"));
			u.setNome(rs.getString("Nome"));
			
			p.setUsuario(u);
			
			lista.add(p);
		}
		
		return lista;
	}
	
	@SuppressWarnings("null")
	public StatusContas listaPorId(int id) throws ClassNotFoundException, SQLException{
		String sql = "SELECT * FROM StatusLimpeza WHERE idStatusLimpeza=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		ResultSet rs = comandoSql.executeQuery();
		
		StatusLimpeza p = null;
		
		if (rs.next()) {
			p.setId(rs.getInt("idStatusLimpeza"));
			p.setStatus(rs.getBoolean("Status"));
			
			Usuario u = new Usuario();
			
			u.setId(rs.getInt("idUsuario"));
			
			p.setUsuario(u);
			
			Limpeza l = new Limpeza();
			
			l.setId(rs.getInt("IdLimpeza"));
			
			p.setLimpeza(l);
			
		}
		
		return p;
	}
}
