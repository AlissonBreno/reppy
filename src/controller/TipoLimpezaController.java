package controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.UsuarioDao;
import dao.TipoLimpezaDao;
import dao.TipoUsuarioDao;
import modelo.TipoLimpeza;
import modelo.TipoUsuario;
import modelo.Usuario;

@ManagedBean
@ViewScoped
public class TipoLimpezaController {
	
	private TipoLimpeza tipoLimpeza = new TipoLimpeza();
	
	public void salvar() {
		
//	System.out.println("Salvando..." + TipoLimpeza.);
		
		TipoLimpezaDao dao = new TipoLimpezaDao();
		
		try {
			
			
			if (tipoLimpeza.getId() == null) {
			dao.adiciona(tipoLimpeza);
			}
			else {
			dao.atualiza(tipoLimpeza);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		tipoLimpeza = new TipoLimpeza();
	}
	
	public void cancelar() {
		tipoLimpeza = new TipoLimpeza();
	}
	
	public List<TipoLimpeza> getTodosTipoLimpeza(){
		List<TipoLimpeza> lista = null;
		try {
			lista = new TipoLimpezaDao().listaTodos();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;	
	}
	
	public void carregarPeloId() {
		TipoLimpezaDao dao = new TipoLimpezaDao();
		

		
		
		try {
			tipoLimpeza = dao.listaPorId(this.tipoLimpeza.getId());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public TipoLimpeza getTipoLimpeza() {
		return tipoLimpeza;
	}
	
	public void remover(TipoLimpeza p) {
		try {	
			new TipoLimpezaDao().remove(p.getId());			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void carregar(TipoLimpeza p) {
		tipoLimpeza = p;
		
	}
	
	public List<TipoLimpeza> getTodosTiposLimpeza(){
		
		List<TipoLimpeza> lista = null;
		try {
		lista = new TipoLimpezaDao().listaTodos();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {	
			e.printStackTrace();
		}
		
		
		
		return lista;
	}
	
	public String redirecionar(TipoLimpeza u) {
		this.tipoLimpeza = u;
		return "edit?faces-redirect=true&includeViewParams=true";
	}


}
