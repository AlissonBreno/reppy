package modelo;

public class TipoContas {
	private Integer id;
	private String TipodeConta;
	private String Credor;
	
	public TipoContas() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipodeConta() {
		return TipodeConta;
	}

	public void setTipodeConta(String tipodeConta) {
		this.TipodeConta = tipodeConta;
	}

	public String getCredor() {
		return Credor;
	}

	public void setCredor(String credor) {
		this.Credor = credor;
	}

}