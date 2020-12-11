package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.TipoMantimentos;
import modelo.TipoUsuario;

public class TipoMantimentosDao {
	
	public void adiciona(TipoMantimentos tm) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO tipomantimentos (nome, descricao) VALUE (?, ?)";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1, tm.getNome());
		comandoSql.setString(2, tm.getDescricao());
		comandoSql.execute();
		
		Conexao.getInstance().commit();
	}

	public void atualiza(TipoMantimentos tm) throws ClassNotFoundException, SQLException {
		
		String sql = "UPDATE tipomantimentos SET nome=?, descricao=? WHERE idTipoMantimentoS=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1, tm.getNome());
		comandoSql.setString(2, tm.getDescricao());
		comandoSql.setInt(3, tm.getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public void remove(int id) throws ClassNotFoundException, SQLException {
		
		String sql = "DELETE FROM tipomantimentos WHERE idTipoMantimentoS=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public TipoMantimentos listaPorId(int id) throws ClassNotFoundException, SQLException{
		
		String sql = "SELECT * FROM tipomantimentos WHERE idTipoMantimentoS=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		ResultSet rs = comandoSql.executeQuery();
		
		TipoMantimentos tm = null;
		
		if (rs.next()) {
			tm = new TipoMantimentos();
			tm.setId(rs.getInt("idTipoMantimentoS"));
			tm.setNome(rs.getString("nome"));
			tm.setDescricao(rs.getString("descricao"));
		}
		
		return tm;
	}
	
	
	public List<TipoMantimentos> listaTodos() throws ClassNotFoundException, SQLException{
		List<TipoMantimentos> lista = new LinkedList<TipoMantimentos>();
		
		String sql = "SELECT * FROM tipomantimentos";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		
		ResultSet rs = comandoSql.executeQuery();
		
		while (rs.next()) {
			TipoMantimentos tu = new TipoMantimentos();
			tu.setId(rs.getInt("idTipoMantimentoS"));
			tu.setNome(rs.getString("nome"));
			tu.setDescricao(rs.getString("descricao"));
			lista.add(tu);
		}
		
		return lista;
	}
}
