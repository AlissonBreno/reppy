package controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.LimpezaDao;
import dao.StatusLimpezaDao;
import dao.UsuarioDao;
import modelo.Limpeza;
import modelo.StatusLimpeza;
import modelo.Usuario;

@ManagedBean
@ViewScoped
public class StatusLimpezaController {
	
	private StatusLimpeza statusLimpeza = new StatusLimpeza();
	private Integer IdUsuario;
	
	public Integer getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		IdUsuario = idUsuario;
	}

	public Integer getLimpeza() {
		return Limpeza;
	}

	public void setIdLimpeza(Integer limpeza) {
		this.Limpeza = limpeza;
	}

	public void setStatusLimpeza(StatusLimpeza statusLimpeza) {
		this.statusLimpeza = statusLimpeza;
	}

	private Integer Limpeza;
	
	public void salvar() {
		
		StatusLimpezaDao dao = new StatusLimpezaDao();
		
		try {
			
			Limpeza l = new LimpezaDao().listaPorId(Limpeza);
			StatusLimpeza.setLimpeza(l);
			
			if (statusLimpeza.getId() == null) {
			dao.adiciona(statusLimpeza);
			}
			else {
			dao.atualiza(statusLimpeza);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		statusLimpeza = new StatusLimpeza();
		Limpeza = null;
		IdUsuario = null;
	}
	
	public void cancelar() {
		statusLimpeza = new StatusLimpeza();
		IdUsuario = null;
		Limpeza = null;
	}
	
	public List<StatusLimpeza> getTodasLimpezas(){
		List<StatusLimpeza> lista = null;
		try {
			lista = new StatusLimpezaDao().listaTodos();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;	
	}
	
	public void carregarPeloId() {
		StatusLimpezaDao dao = new StatusLimpezaDao();
		

		
		
		try {
			statusLimpeza = dao.listaPorId(this.statusLimpeza.getId());
			IdUsuario = this.statusLimpeza.getUsuario().getId();
			Limpeza = this.statusLimpeza.getLimpeza().getId();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public StatusLimpeza getStatusLimpeza() {
		return statusLimpeza;
	}
	
	public void remover(StatusLimpeza p) {
		try {	
			new StatusLimpezaDao().remove(p.getId());			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void carregar(StatusLimpeza p) {
		statusLimpeza = p;
		
		Limpeza = p.getLimpeza().getId();
		IdUsuario = p.getUsuario().getId();
	}
	
	
	public String redirecionar(StatusLimpeza u) {
		this.statusLimpeza = u;
		return "edit?faces-redirect=true&includeViewParams=true";
	}
}
