package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.Limpeza;
import modelo.StatusLimpeza;
import modelo.Usuario;

public class StatusLimpezaDao{
	
	public void adiciona(StatusLimpeza p) throws ClassNotFoundException, SQLException {
		String sql = 
			"INSERT INTO statuslimpeza ( statusLimpeza,  idUsuario, idLimpeza )" +
		"VALUES (?, ?, ?)";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setBoolean(1,  p.getStatus());
		comandoSql.setInt(2, p.getUsuario().getId());
		comandoSql.setInt(3, p.getLimpeza().getId());
		
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}

	public void atualiza(StatusLimpeza p) throws ClassNotFoundException, SQLException {
		String sql = 
			"UPDATE StatusLimpeza SET StatusLimpeza=?, idUsuario=?, idLimpeza=?, "
			+ " WHERE idStatusLimpeza=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setBoolean(1,  p.getStatus());
		comandoSql.setInt(2, p.getUsuario().getId());
		comandoSql.setInt(3, p.getLimpeza().getId());
		comandoSql.setInt(4, p.getId());
		
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
	
	public List<StatusLimpeza> listaTodos() throws ClassNotFoundException, SQLException{
		List<StatusLimpeza> lista = new LinkedList<StatusLimpeza>();
		
		String sql = "SELECT * FROM StatusLimpeza inner join Usuario on " +
					"StatusLimpeza.idUsuario = Usuario.idUsuario"+
					"inner join Limpeza" +
					"StatusLimpeza.idLimpeza = Limpeza.idLimpeza";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		
		ResultSet rs = comandoSql.executeQuery();
		
		while (rs.next()) {
			StatusLimpeza p = new StatusLimpeza();
			p.setId(rs.getInt("idStatusLimpeza"));
			p.setStatus(rs.getBoolean("Status"));
			
			Usuario u = new Usuario();
			
			u.setId(rs.getInt("idUsuario"));
			u.setNome(rs.getString("Nome"));
			
			p.setUsuario(u);
			
			Limpeza l = new Limpeza();
			
			l.setId(rs.getInt("IdLimpeza"));
			l.setDataMarcada(rs.getString("DataMarcada"));
			
			p.setLimpeza(l);
			
			lista.add(p);
		}
		
		return lista;
	}
	
	@SuppressWarnings("null")
	public StatusLimpeza listaPorId(int id) throws ClassNotFoundException, SQLException{
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
