package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.TipoUsuario;

public class TipoUsuarioDao {
	
	public void adiciona(TipoUsuario tu) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO TipoUsuario (Nome, Descricao) VALUE (?, ?)";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1, tu.getName());
		comandoSql.setString(2, tu.getDescricao());
		comandoSql.execute();
		
		Conexao.getInstance().commit();
	}

	public void atualiza(TipoUsuario tu) throws ClassNotFoundException, SQLException {
		
		String sql = "UPDATE TipoUsuario SET Nome=?, Descricao=? WHERE idTipoUsuario=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1, tu.getName());
		comandoSql.setString(2, tu.getDescricao());
		comandoSql.setInt(3, tu.getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public void remove(int id) throws ClassNotFoundException, SQLException {
		
		String sql = "DELETE FROM TipoUsuario WHERE idTipoUsuario=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public List<TipoUsuario> listaTodos() throws ClassNotFoundException, SQLException{
		List<TipoUsuario> lista = new LinkedList<TipoUsuario>();
		
		String sql = "SELECT * FROM TipoUsuario";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		
		ResultSet rs = comandoSql.executeQuery();
		
		while (rs.next()) {
			TipoUsuario tu = new TipoUsuario();
			tu.setId(rs.getInt("idTipoUsuario"));
			tu.setName(rs.getString("Nome"));
			tu.setDescricao(rs.getString("Descricao"));
			lista.add(tu);
		}
		
		return lista;
	}
	
	public TipoUsuario listaPorId(int id) throws ClassNotFoundException, SQLException{
		
		String sql = "SELECT * FROM TipoUsuario WHERE idTipoUsuario=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		ResultSet rs = comandoSql.executeQuery();
		
		TipoUsuario tu = null;
		
		if (rs.next()) {
			tu = new TipoUsuario();
			tu.setId(rs.getInt("idTipoUsuario"));
			tu.setName(rs.getString("Nome"));
			tu.setDescricao(rs.getString("Descricao"));
		}
		
		return tu;
	}
}
