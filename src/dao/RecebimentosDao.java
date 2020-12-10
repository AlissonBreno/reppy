package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.Contas;
import modelo.Recebimentos;
import modelo.TipoUsuario;
import modelo.Usuario;

public class RecebimentosDao{
	
	public void adiciona(Recebimentos p) throws ClassNotFoundException, SQLException {
		String sql = 
			"INSERT INTO Recebimentos ( idUsuario, idConta )" +
		"VALUES (?, ?)";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, p.getUsuario().getId());
		comandoSql.setInt(2, p.getConta().getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}

	public void atualiza(Recebimentos p) throws ClassNotFoundException, SQLException {
		String sql = 
			"UPDATE Recebimentos SET idUsuario=?, idConta=? WHERE idRecebimentos=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, p.getUsuario().getId());
		comandoSql.setInt(2, p.getConta().getId());
		comandoSql.setInt(3, p.getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public void remove(int id) throws ClassNotFoundException, SQLException {
		String sql = 
			"DELETE FROM Recebimentos WHERE idRecebimentos=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public List<Recebimentos> listaTodos() throws ClassNotFoundException, SQLException{
		List<Recebimentos> lista = new LinkedList<Recebimentos>();
		
		String sql = "SELECT * FROM Recebimentos inner join Usuario on " +
					"Recebimentos.idUsuario = Usuario.idUsuario" +
					"inner join Contas on " +
					"Recebimentos.idContas = Contas.idContas"
				;
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		
		ResultSet rs = comandoSql.executeQuery();
		
		while (rs.next()) {
			Recebimentos p = new Recebimentos();
			p.setId(rs.getInt("idRecebimentos"));
			
			Usuario t = new Usuario();
			
			t.setId(rs.getInt("idUsuario"));
			
			p.setUsuario(t);
			
			Contas c = new Contas();
			
			c.setId(rs.getInt("idConta"));
			
			p.setConta(c);
			
			
			
			lista.add(p);
		}
		
		return lista;
	}
	
	public Recebimentos listaPorId(int id) throws ClassNotFoundException, SQLException{
		String sql = "SELECT * FROM Recebimentos WHERE idRecebimentos=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		ResultSet rs = comandoSql.executeQuery();
		
		Recebimentos p = null;
		
		if (rs.next()) {
			p = new Recebimentos();
			p.setId(rs.getInt("idRecebimentos"));
					
			Usuario u = new Usuario();
			u.setId(rs.getInt("idUsuario"));
			
			p.setUsuario(u);
		
			Contas c = new Contas();
			c.setId(rs.getInt("idConta"));
			
			p.setConta(c);
		}
		
		return p;
	}
}
