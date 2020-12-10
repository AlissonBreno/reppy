package controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.LimpezaDao;
import dao.StatusContasDao;
import dao.StatusLimpezaDao;
import dao.TipoContasDao;
import dao.UsuarioDao;
import modelo.Limpeza;
import modelo.StatusContas;
import modelo.StatusLimpeza;
import modelo.TipoContas;
import modelo.Usuario;

@ManagedBean
@ViewScoped
public class StatusContasController {
	
	private StatusContas statuscontas = new StatusContas();
	private Integer IdTipoContas;
		
	public StatusContas getStatuscontas() {
		return statuscontas;
	}

	public void setStatuscontas(StatusContas statuscontas) {
		this.statuscontas = statuscontas;
	}

	public Integer getIdTipoContas() {
		return IdTipoContas;
	}

	public void setIdTipoContas(Integer idTipoContas) {
		IdTipoContas = idTipoContas;
	}

	public void salvar() {
		
		StatusContasDao dao = new StatusContasDao();
		
		try {
			
			TipoContas l = new TipoContasDao().listaPorId(IdTipoContas);
			statuscontas.setTipoContas(l);
			
			if (statuscontas.getId() == null) {
			dao.adiciona(statuscontas);
			}
			else {
			dao.atualiza(statuscontas);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		statuscontas = new StatusContas();
		IdTipoContas = null;
	}
	
	public void cancelar() {
		statuscontas = new StatusContas();
		IdTipoContas = null;
	}
	
	public List<StatusContas> getTodasContas(){
		List<StatusContas> lista = null;
		try {
			lista = new StatusContasDao().listaTodos();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;	
	}
	
	public void carregarPeloId() {
		
		StatusContasDao dao = new StatusContasDao();
		
		try {
			statuscontas = dao.listaPorId(this.statuscontas.getId());
			
			IdTipoContas = this.statuscontas.getTipoContas().getId();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public StatusContas getStatusContas() {
		return statuscontas;
	}
	
	public void remover(StatusContas p) {
		try {	
			new StatusLimpezaDao().remove(p.getId());			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void carregar(StatusContas p) {
		statuscontas = p;
		
		IdTipoContas = p.getTipoContas().getId();
	}
	
	
	public String redirecionar(StatusContas u) {
		this.statuscontas = u;
		return "edit?faces-redirect=true&includeViewParams=true";
	}
}
