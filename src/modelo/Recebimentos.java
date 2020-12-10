package modelo;

public class Recebimentos {
	private Integer id;
	private String DataRecebimento;
	private Usuario Usuario;
	private Contas Conta;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDataRecebimento() {
		return DataRecebimento;
	}
	public void setDataRecebimento(String dataRecebimento) {
		DataRecebimento = dataRecebimento;
	}
	public Usuario getUsuario() {
		return Usuario;
	}
	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}
	public Contas getConta() {
		return Conta;
	}
	public void setConta(Contas conta) {
		Conta = conta;
	}

}	