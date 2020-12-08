package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.TipoLimpeza;
import modelo.TipoUsuario;
import modelo.Usuario;

public class TipoLimpezaDao{
	
	public void adiciona(TipoLimpeza p) throws ClassNotFoundException, SQLException {
		String sql = 
			"INSERT INTO tipolimpeza ( Tipo )" +
		"VALUES (?)";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1,  p.getTipo());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}

	public void atualiza(TipoLimpeza p) throws ClassNotFoundException, SQLException {
		String sql = 
			"UPDATE tipolimpeza SET Tipo=? WHERE idTipoLimpeza=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1,  p.getTipo());
		comandoSql.setInt(1, p.getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public void remove(int id) throws ClassNotFoundException, SQLException {
		String sql = 
			"DELETE FROM tipolimpeza WHERE idTipoLimpeza=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public List<TipoLimpeza> listaTodos() throws ClassNotFoundException, SQLException{
		List<TipoLimpeza> lista = new LinkedList<TipoLimpeza>();
		
		String sql = "SELECT * FROM tipolimpeza " ;
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		
		ResultSet rs = comandoSql.executeQuery();
		
		while (rs.next()) {
			TipoLimpeza p = new TipoLimpeza();
			p.setId(rs.getInt("idTipoLimpeza"));
			p.setTipo(rs.getString("Tipo"));
			
			
			lista.add(p);
		}
		
		return lista;
	}
	
	public TipoLimpeza listaPorId(int id) throws ClassNotFoundException, SQLException{
		String sql = "SELECT * FROM tipolimpeza WHERE idTipoLimpeza=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		ResultSet rs = comandoSql.executeQuery();
		
		TipoLimpeza p = null;
		
		if (rs.next()) {
			p = new TipoLimpeza();
			p.setId(rs.getInt("idTipoLimpeza"));
			p.setTipo(rs.getString("Tipo"));

		
		
		
		}
		
		return p;
	}
}