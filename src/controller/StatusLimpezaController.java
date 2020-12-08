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

	public Integer getIdLimpeza() {
		return IdLimpeza;
	}

	public void setIdLimpeza(Integer idLimpeza) {
		IdLimpeza = idLimpeza;
	}

	public void setStatusLimpeza(StatusLimpeza statusLimpeza) {
		this.statusLimpeza = statusLimpeza;
	}

	private Integer IdLimpeza;
	
	public void salvar() {
		
	System.out.println("Salvando..." + statusLimpeza.getStatus());
		
		StatusLimpezaDao dao = new StatusLimpezaDao();
		
		try {
			
			Limpeza l = new LimpezaDao().listaPorId(idLimpeza)
			Limpeza.setIdLimpeza(l);
			
			
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
		limpeza = null;
		usuario = null;
	}
	
	public void cancelar() {
		statusLimpeza = new StatusLimpeza();
		usuario = null;
		limpeza = null;
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
			IdLimpeza = this.statusLimpeza.getLimpeza().getId();
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
		
		IdLimpeza = p.getLimpeza().getId();
		IdUsuario = p.getUsuario().getId();
	}
	
	public List<StatusLimpeza> getTodasLimpeza(){
		
		List<StatusLimpeza> lista = null;
		try {
		lista = new StatusLimpeza().listaTodos();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {	
			e.printStackTrace();
		}
		
		
		
		return lista;
	}
	
	public String redirecionar(StatusLimpeza u) {
		this.statusLimpeza = u;
		return "edit?faces-redirect=true&includeViewParams=true";
	}
}
