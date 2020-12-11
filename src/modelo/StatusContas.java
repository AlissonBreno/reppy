package modelo;

public class StatusContas {
	private Integer id;
	private Double Valor;
	private String Vencimento; 
	private TipoContas TipoContas;

	public StatusContas() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVencimento() {
		return Vencimento;
	}

	public void setVencimento(String vencimento) {
		this.Vencimento = vencimento;
	}
	public Double getValor() {
		return Valor;
	}

	public void setValor(Double valor) {
		this.Valor = valor;
	}

	public TipoContas getTipoContas() {
		return TipoContas;
	}

	public void setTipoContas(TipoContas tipoContas) {
		TipoContas = tipoContas;
	}
	
}