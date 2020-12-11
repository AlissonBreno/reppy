package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.LocalMantimentos;

public class LocalMantimentosDao {
	public void adiciona(LocalMantimentos lm) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO localmantimentos (nome, descricao) VALUE (?, ?)";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1, lm.getNome());
		comandoSql.setString(2, lm.getDescricao());
		comandoSql.execute();
		
		Conexao.getInstance().commit();
	}

	public void atualiza(LocalMantimentos lm) throws ClassNotFoundException, SQLException {
		
		String sql = "UPDATE localmantimentos SET nome=?, descricao=? WHERE idLocalMantimentoS=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1, lm.getNome());
		comandoSql.setString(2, lm.getDescricao());
		comandoSql.setInt(3, lm.getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public void remove(int id) throws ClassNotFoundException, SQLException {
		
		String sql = "DELETE FROM localmantimentos WHERE idLocalMantimentoS=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public LocalMantimentos listaPorId(int id) throws ClassNotFoundException, SQLException{
		
		String sql = "SELECT * FROM localmantimentos WHERE idLocalMantimentoS=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		ResultSet rs = comandoSql.executeQuery();
		
		LocalMantimentos lm = null;
		
		if (rs.next()) {
			lm = new LocalMantimentos();
			lm.setId(rs.getInt("idLocalMantimentoS"));
			lm.setNome(rs.getString("nome"));
			lm.setDescricao(rs.getString("descricao"));
		}
		
		return lm;
	}
	
	
	public List<LocalMantimentos> listaTodos() throws ClassNotFoundException, SQLException{
		List<LocalMantimentos> lista = new LinkedList<LocalMantimentos>();
		
		String sql = "SELECT * FROM localmantimentos";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		
		ResultSet rs = comandoSql.executeQuery();
		
		while (rs.next()) {
			LocalMantimentos tu = new LocalMantimentos();
			tu.setId(rs.getInt("idLocalMantimentoS"));
			tu.setNome(rs.getString("nome"));
			tu.setDescricao(rs.getString("descricao"));
			lista.add(tu);
		}
		
		return lista;
	}
}
