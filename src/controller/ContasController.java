package controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.UsuarioDao;
import dao.ContasDao;
import dao.TipoContasDao;
import dao.TipoUsuarioDao;
import modelo.Contas;
import modelo.TipoContas;
import modelo.TipoUsuario;
import modelo.Usuario;

@ManagedBean
@ViewScoped
public class ContasController {
	
	private Contas contas = new Contas();
	private Integer IdTipoContas;
	
	public void salvar() {
		
	System.out.println("Salvando..." + contas.getValor());
		
		ContasDao dao = new ContasDao();
		
		try {
			
			TipoContas t = new TipoContasDao().listaPorId(IdTipoContas);
			contas.setTipoContas(t);
			
			
			if (contas.getId() == null) {
			dao.adiciona(contas);
			}
			else {
			dao.atualiza(contas);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		contas = new Contas();
		IdTipoContas = null;
	}
	
	public void cancelar() {
		contas = new Contas();
		IdTipoContas = null;
	}
	
	public List<Contas> getTodasContas(){
		List<Contas> lista = null;
		try {
			lista = new ContasDao().listaTodos();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;	
	}
	
	public void carregarPeloId() {
		ContasDao dao = new ContasDao();
		

		
		
		try {
			contas = dao.listaPorId(this.contas.getId());
			IdTipoContas = this.contas.getTipoContas().getId();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Contas getContas() {
		return contas;
	}
	
	public void remover(Contas p) {
		try {	
			new UsuarioDao().remove(p.getId());			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void carregar(Contas p) {
		contas = p;
		
		IdTipoContas = p.getTipoContas().getId();
	}
	
	public List<TipoContas> getTodosTiposContas(){
		
		List<TipoContas> lista = null;
		try {
		lista = new TipoContasDao().listaTodos();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {	
			e.printStackTrace();
		}
		
		
		
		return lista;
	}
	
	public String redirecionar(Contas u) {
		this.contas = u;
		return "edit?faces-redirect=true&includeViewParams=true";
	}

	public Integer getIdTipoContas() {
		return IdTipoContas;
	}

	public void setIdTipoContas(Integer idTipoContas) {
		this.IdTipoContas = idTipoContas;
	}
}
