package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static Connection conexao;
	private String banco = "reppy"; // nome do BD
	private String login = "root"; // login do mysql
	private String senha = ""; // senha do mysql

	public Conexao() throws ClassNotFoundException, SQLException  {
		// carrega o drive
		Class.forName("com.mysql.cj.jdbc.Driver");

		// estabelece conexão: drive://servidor:porta/banco, login, senha
		conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.banco, this.login, this.senha);

		// as modificações so s�o salvas no banco com o commit (explicito no c�digo)
		conexao.setAutoCommit(false);
	}

	// verifica se não existe conexao, senão houver cria uma nova conexão
	public static Connection getInstance() throws ClassNotFoundException, SQLException {
		if (conexao == null)
			new Conexao();

		return conexao;
	}
}