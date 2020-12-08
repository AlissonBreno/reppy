package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.Contas;
import modelo.TipoContas;
import modelo.TipoUsuario;
import modelo.Usuario;

public class ContasDao{
	
	public void adiciona(Contas p) throws ClassNotFoundException, SQLException {
		String sql = 
			"INSERT INTO contas ( Valor, Vencimento, idTipoConta )" +
		"VALUES (?, ?, ?)";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setDouble(1,  p.getValor());
		comandoSql.setString(2, p.getVencimento());
		comandoSql.setInt(3, p.getTipoContas().getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}

	public void atualiza(Contas p) throws ClassNotFoundException, SQLException {
		String sql = 
			"UPDATE contas SET Valor=?, Vencimento=?, "
			+ "idTipoConta=? WHERE idConta=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setDouble(1,  p.getValor());
		comandoSql.setString(2, p.getVencimento());
		comandoSql.setInt(3, p.getTipoContas().getId());
		comandoSql.setInt(4, p.getId());
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public void remove(int id) throws ClassNotFoundException, SQLException {
		String sql = 
			"DELETE FROM contas WHERE idContas=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		comandoSql.execute();
		Conexao.getInstance().commit();
	}
	
	public List<Contas> listaTodos() throws ClassNotFoundException, SQLException{
		List<Contas> lista = new LinkedList<Contas>();
		
		String sql = "SELECT * FROM contas inner join tiposdeconta on " +
					"contas.idTipoContas = tiposdeconta.idTipoConta";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		
		ResultSet rs = comandoSql.executeQuery();
		
		while (rs.next()) {
			Contas p = new Contas();
			p.setId(rs.getInt("idContas"));
			p.setValor(rs.getDouble("Valor"));
			p.setVencimento(rs.getString("Vencimento"));

			
			TipoContas t = new TipoContas();
			
			t.setId(rs.getInt("idTipoContas"));
			t.setCredor(rs.getString("Credor"));
			
			
			
			p.setTipoContas(t);
			
			lista.add(p);
		}
		
		return lista;
	}
	
	public Contas listaPorId(int id) throws ClassNotFoundException, SQLException{
		String sql = "SELECT * FROM contas WHERE idContas=?";
		
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);
		
		ResultSet rs = comandoSql.executeQuery();
		
		Contas p = null;
		
		if (rs.next()) {
			p = new Contas();
			p.setId(rs.getInt("idContas"));
			p.setValor(rs.getDouble("Valor"));
			p.setVencimento(rs.getString("Vencimento"));
			
			TipoContas tu = new TipoContas();
			tu.setId(rs.getInt("idTipoContas"));
			
			p.setTipoContas(tu);
		}
		
		return p;
	}
}
