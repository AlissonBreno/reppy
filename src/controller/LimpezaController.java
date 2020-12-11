package controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.UsuarioDao;
import dao.LimpezaDao;
import dao.TipoLimpezaDao;
import modelo.Limpeza;
import modelo.TipoLimpeza;
import modelo.Usuario;

@ManagedBean
@ViewScoped
public class LimpezaController {
	
	private Limpeza limpeza = new Limpeza();
	private Integer idTipoLimpeza;
	private Integer IdUsuario;
	
	public Limpeza getLimpeza() {
		return limpeza;
	}

	public void setLimpeza(Limpeza limpeza) {
		this.limpeza = limpeza;
	}

	public Integer getIdTipoLimpeza() {
		return idTipoLimpeza;
	}

	public void setIdTipoLimpeza(Integer idTipoLimpeza) {
		this.idTipoLimpeza = idTipoLimpeza;
	}

	public Integer getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		IdUsuario = idUsuario;
	}

	public void salvar() {
		
		LimpezaDao dao = new LimpezaDao();
		
		try {
			
			TipoLimpeza t = new TipoLimpezaDao().listaPorId(idTipoLimpeza);
			limpeza.setTipoLimpeza(t);
			
			Usuario i = new UsuarioDao().listaPorId(IdUsuario);
			limpeza.setUsuario(i);
			
			
			if (limpeza.getId() == null) {
			dao.adiciona(limpeza);
			}
			else {
			dao.atualiza(limpeza);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		limpeza = new Limpeza();
		idTipoLimpeza = null;
		IdUsuario = null;
	}
	
	public void cancelar() {
		limpeza = new Limpeza();
		IdUsuario = null;
		idTipoLimpeza = null;
	}
	
	public List<Limpeza> getTodasLimpezas(){
		List<Limpeza> lista = null;
		try {
			lista = new LimpezaDao().listaTodos();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;	
	}
	
	public void carregarPeloId() {
		LimpezaDao dao = new LimpezaDao();
		

		
		
		try {
			limpeza = dao.listaPorId(this.limpeza.getId());
			IdUsuario = this.getIdUsuario();
			idTipoLimpeza = this.getIdTipoLimpeza();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void remover(Limpeza p) {
		try {	
			new LimpezaDao().remove(p.getId());			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void carregar(Limpeza p) {
		limpeza = p;
		
		IdUsuario = p.getUsuario().getId();
	}

	
	public String redirecionar(Limpeza u) {
		this.limpeza = u;
		return "edit?faces-redirect=true&includeViewParams=true";
	}

}
