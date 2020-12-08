package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.Limpeza;
import modelo.TipoLimpeza;
import modelo.TipoUsuario;
import modelo.Usuario;

public class LimpezaDao{
	
	public void adiciona(Limpeza p) throws ClassNotFoundException, SQLException {
		String sql = 
			"INSERT INTO Usuario ( DataMarcada, idUsuario, idTipoLimpeza )" +
		"VALUES (?, ?, ?)";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1,  p.getDataMarcada());
		comandoSql.setInt(2, p.getUsuario().getId());
		comandoSql.setInt(3, p.getTipoLimpeza().getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}

	public void atualiza(Limpeza p) throws ClassNotFoundException, SQLException {
		String sql = 
			"UPDATE Limpeza SET DataMarcada=?, idUsuario=?, idTipoLimpeza=? "
			+ " WHERE idLimpeza=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1,  p.getDataMarcada());
		comandoSql.setInt(2, p.getUsuario().getId());
		comandoSql.setInt(3, p.getTipoLimpeza().getId());
		comandoSql.setInt(4, p.getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public void remove(int id) throws ClassNotFoundException, SQLException {
		String sql = 
			"DELETE FROM Limpeza WHERE idLimpeza=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public List<Limpeza> listaTodos() throws ClassNotFoundException, SQLException{
		List<Limpeza> lista = new LinkedList<Limpeza>();
		
		String sql = "SELECT * FROM Limpeza inner join Usuario on " +
					"Limpeza.idUsuario = Usuario.idUsuario";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		
		ResultSet rs = comandoSql.executeQuery();
		
		while (rs.next()) {
			Limpeza p = new Limpeza();
			p.setId(rs.getInt("idLimpeza"));
			p.setDataMarcada(rs.getString("DataMarcada"));
			
			Usuario u = new Usuario();
			
			u.setId(rs.getInt("idUsuario"));
			u.setNome(rs.getString("Nome"));
			
			p.setUsuario(u);
			
			TipoLimpeza t = new TipoLimpeza();
			
			t.setId(rs.getInt("idTipoLimpeza"));
			t.setTipo(rs.getString("Tipo"));
			
			
			p.setTipoLimpeza(t);
			
			lista.add(p);
		}
		
		return lista;
	}
	
	public Limpeza listaPorId(int id) throws ClassNotFoundException, SQLException{
		String sql = "SELECT * FROM Limpeza WHERE idLimpeza=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		ResultSet rs = comandoSql.executeQuery();
		
		Limpeza p = null;
		
		if (rs.next()) {
			p = new Limpeza();
			p.setId(rs.getInt("idLimpeza"));
			p.setDataMarcada(rs.getString("DataMarcada"));
			
			Usuario u = new Usuario();
			u.setId(rs.getInt("idUsuario"));
			
			p.setUsuario(u);
			
			TipoLimpeza t = new TipoLimpeza();
			t.setId(rs.getInt("idTipoLimpeza"));
			
			
			p.setTipoLimpeza(t);
}
		
		return p;
	}
}
