package controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.LocalMantimentosDao;
import dao.MantimentosDao;
import dao.TipoMantimentosDao;
import dao.UsuarioDao;

import modelo.LocalMantimentos;
import modelo.Mantimentos;
import modelo.TipoMantimentos;
import modelo.Usuario;

@ManagedBean
@ViewScoped
public class MantimentosController {
	
	private Mantimentos mantimentos = new Mantimentos();
	private Integer idTipoMantimentos;
	private Integer idLocalMantimentos;
	private Integer idUsuario;
	
	public void salvar() {
		
		System.out.println("Salvando..." + mantimentos.getId());
		
		MantimentosDao dao = new MantimentosDao();
		
		try {
			
			TipoMantimentos tm = new TipoMantimentosDao().listaPorId(idTipoMantimentos);
			mantimentos.setTipoMantimentos(tm);
			
			LocalMantimentos lm = new LocalMantimentosDao().listaPorId(idLocalMantimentos);
			mantimentos.setLocalMantimentos(lm);
			
			Usuario u = new UsuarioDao().listaPorId(idUsuario);
			mantimentos.setUsuario(u);
			
			
			if (mantimentos.getId() == null) {
			dao.adiciona(mantimentos);
			}
			else {
			dao.atualiza(mantimentos);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		mantimentos = new Mantimentos();
		idTipoMantimentos = null;
		idLocalMantimentos = null;
		idUsuario = null;
	}
	
	public void cancelar() {
		mantimentos = new Mantimentos();
		idTipoMantimentos = null;
		idLocalMantimentos = null;
		idUsuario = null;
	}
	
	public List<Mantimentos> getTodosMantimentos(){
		List<Mantimentos> lista = null;
		try {
			lista = new MantimentosDao().listaTodos();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;	
	}
	
	public void carregarPeloId() {
		MantimentosDao dao = new MantimentosDao();
		
		try {
			mantimentos = dao.listaPorId(this.mantimentos.getId());
			idTipoMantimentos = this.mantimentos.getTipoMantimentos().getId();
			idLocalMantimentos = this.mantimentos.getLocalMantimentos().getId();
			idUsuario = this.mantimentos.getUsuario().getId();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	public void remover(Usuario p) {
		try {	
			new UsuarioDao().remove(p.getId());			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void carregar(Mantimentos p) {
		mantimentos = p;
		
		idTipoMantimentos = p.getTipoMantimentos().getId();
		idLocalMantimentos = p.getLocalMantimentos().getId();
		idUsuario = p.getUsuario().getId();
	}
	
	public List<TipoMantimentos> getTodosTiposMantimentos(){
		
		List<TipoMantimentos> lista = null;
		try {
		lista = new TipoMantimentosDao().listaTodos();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {	
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<LocalMantimentos> getTodosLocalMantimentos(){
		
		List<LocalMantimentos> lista = null;
		try {
		lista = new LocalMantimentosDao().listaTodos();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {	
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Usuario> getTodosUsuarios(){
		
		List<Usuario> lista = null;
		try {
		lista = new UsuarioDao().listaTodos();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {	
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public String redirecionar(Mantimentos u) {
		this.mantimentos = u;
		return "edit?faces-redirect=true&includeViewParams=true";
	}

	public Mantimentos getMantimentos() {
		return mantimentos;
	}

	public void setMantimentos(Mantimentos mantimentos) {
		this.mantimentos = mantimentos;
	}

	public Integer getIdTipoMantimentos() {
		return idTipoMantimentos;
	}

	public void setIdTipoMantimentos(Integer idTipoMantimentos) {
		this.idTipoMantimentos = idTipoMantimentos;
	}

	public Integer getIdLocalMantimentos() {
		return idLocalMantimentos;
	}

	public void setIdLocalMantimentos(Integer idLocalMantimentos) {
		this.idLocalMantimentos = idLocalMantimentos;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
