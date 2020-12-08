package modelo;

public class StatusLimpeza {
	private Integer id;
	private Boolean Status;
	private Usuario Usuario;
	private Limpeza Limpeza;
	
	public StatusLimpeza() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

	public Usuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}

	public Limpeza getLimpeza() {
		return Limpeza;
	}

	public void setLimpeza(Limpeza limpeza) {
		Limpeza = limpeza;
	}

	
}