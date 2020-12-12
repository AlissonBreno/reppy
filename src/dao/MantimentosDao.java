package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.LocalMantimentos;
import modelo.Mantimentos;
import modelo.TipoMantimentos;
import modelo.Usuario;

public class MantimentosDao{
	
	public void adiciona(Mantimentos m) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO mantimentos (nome, validade, idTipoMantimentoS, idLocalMantimentoS, idUsuario)" +
					 "VALUES(?, ?, ?, ?, ?)";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1, m.getNome());
		comandoSql.setString(2, m.getValidade());
		comandoSql.setInt(3, m.getTipoMantimentos().getId());
		comandoSql.setInt(4, m.getLocalMantimentos().getId());
		comandoSql.setInt(5, m.getUsuario().getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}

	public void atualiza(Mantimentos m) throws ClassNotFoundException, SQLException {
		String sql = 
			"UPDATE mantimentos SET nome=?, validade=?, idTipoMantimentoS=?, "
			+ "idLocalMantimentoS=?, idUsuario=? WHERE idMantimentos=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1, m.getNome());
		comandoSql.setString(2, m.getValidade());
		comandoSql.setInt(3, m.getTipoMantimentos().getId());
		comandoSql.setInt(4, m.getLocalMantimentos().getId());
		comandoSql.setInt(5, m.getUsuario().getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public void remove(int id) throws ClassNotFoundException, SQLException {
		String sql = 
			"DELETE FROM mantimentos WHERE idMantimentos=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public List<Mantimentos> listaTodos() throws ClassNotFoundException, SQLException{
		List<Mantimentos> lista = new LinkedList<Mantimentos>();
		
		String sql = "select * from mantimentos \r\n" + 
					 "inner join usuario ON usuario.idUsuario = mantimentos.idUsuario " + 
					 "inner join localmantimentos on localmantimentos.idLocalmantimentos = mantimentos.idLocalMantimentos " + 
					 "inner join tipomantimentos on tipomantimentos.idTipoMantimentos = mantimentos.idTipoMantimentos";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		
		ResultSet rs = comandoSql.executeQuery();
		
		while (rs.next()) {
			Mantimentos m = new Mantimentos();
			m.setId(rs.getInt("idMantimentos"));
			m.setNome(rs.getString("nome"));
			m.setValidade(rs.getString("validade"));
			
			TipoMantimentos tm = new TipoMantimentos();
			tm.setId(rs.getInt("idTipoMantimentos"));
			tm.setNome(rs.getString("nome"));
			m.setTipoMantimentos(tm);
			
			LocalMantimentos lm = new LocalMantimentos();
			lm.setId(rs.getInt("idLocalMantimentoS"));
			lm.setNome(rs.getString("nome"));
			m.setLocalMantimentos(lm);
			
			Usuario u = new Usuario();
			u.setId(rs.getInt("idUsuario"));
			u.setNome(rs.getString("nome"));
			m.setUsuario(u);

			lista.add(m);
		}
		
		return lista;
	}
	
	public Mantimentos listaPorId(int id) throws ClassNotFoundException, SQLException{
		String sql = "SELECT * FROM mantimentos WHERE idMantimentos=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		ResultSet rs = comandoSql.executeQuery();
		
		Mantimentos m = null;
		
		if (rs.next()) {
			m = new Mantimentos();
			m.setId(rs.getInt("idMantimentos"));
			m.setNome(rs.getString("nome"));
			m.setValidade(rs.getString("validade"));
			
			TipoMantimentos tm = new TipoMantimentos();
			tm.setId(rs.getInt("idTipoMantimentos"));
			tm.setNome(rs.getString("nome"));
			m.setTipoMantimentos(tm);
			
			LocalMantimentos lm = new LocalMantimentos();
			lm.setId(rs.getInt("idLocalMantimentoS"));
			lm.setNome(rs.getString("nome"));
			m.setLocalMantimentos(lm);
			
			Usuario u = new Usuario();
			u.setId(rs.getInt("idUsuario"));
			u.setNome(rs.getString("nome"));
			m.setUsuario(u);
			
		
		}
		
		return m;
	}
}
