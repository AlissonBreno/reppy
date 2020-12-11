package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.TipoContas;

public class TipoContasDao {
	
	
	
	public void adiciona(TipoContas tu) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO tiposdeconta (TipodeConta, Credor) VALUE (?, ?)";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1, tu.getTipo());
		comandoSql.setString(2, tu.getCredor());
		comandoSql.execute();
		
		Conexao.getInstance().commit();
	}

	public void atualiza(TipoContas tu) throws ClassNotFoundException, SQLException {
		
		String sql = "UPDATE tiposdeconta SET TipodeConta=?, Credor=? WHERE idTiposdeConta=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1, tu.getTipo());
		comandoSql.setString(2, tu.getCredor());
		comandoSql.setInt(3, tu.getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public void remove(int id) throws ClassNotFoundException, SQLException {
		
		String sql = "DELETE FROM tiposdeconta WHERE idTiposdeConta=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public List<TipoContas> listaTodos() throws ClassNotFoundException, SQLException{
		List<TipoContas> lista = new LinkedList<TipoContas>();
		
		String sql = "SELECT * FROM tiposdeconta";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		
		ResultSet rs = comandoSql.executeQuery();
		
		while (rs.next()) {
			TipoContas tu = new TipoContas();
			tu.setId(rs.getInt("idTiposdeConta"));
			tu.setTipo(rs.getString("Tipo"));
			tu.setCredor(rs.getString("Credor"));
			lista.add(tu);
		}
		
		return lista;
	}
	
	public TipoContas listaPorId(int id) throws ClassNotFoundException, SQLException{
		
		String sql = "SELECT * FROM tiposdeconta WHERE idTiposdeConta=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		ResultSet rs = comandoSql.executeQuery();
		
		TipoContas tu = null;
		
		if (rs.next()) {
			tu = new TipoContas();
			tu.setId(rs.getInt("idTiposdeConta"));
			tu.setTipo(rs.getString("Tipo"));
			tu.setCredor(rs.getString("Credor"));
		}
		
		return tu;
	}
	
}
