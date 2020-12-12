package modelo;

public class Contas {
	private Integer id;
	private Double Valor;
	public void setValor(Double valor) {
		Valor = valor;
	}

	private String Vencimento; 
	private TipoContas TipoContas;

	public Contas() {
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

	public TipoContas getTipoContas() {
		return TipoContas;
	}

	public void setTipoContas(TipoContas tipoContas) {
		TipoContas = tipoContas;
	}
	
}