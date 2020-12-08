package controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.UsuarioDao;
import dao.TipoUsuarioDao;
import modelo.TipoUsuario;
import modelo.Usuario;

@ManagedBean
@ViewScoped
public class RecebimentosController {
	
	private Usuario usuario = new Usuario();
	private Integer IdTipoUsuario;
	
	public void salvar() {
		
	System.out.println("Salvando..." + usuario.getNome());
		
		UsuarioDao dao = new UsuarioDao();
		
		try {
			
			TipoUsuario t = new TipoUsuarioDao().listaPorId(IdTipoUsuario);
			usuario.setTipoUsuario(t);
			
			
			if (usuario.getId() == null) {
			dao.adiciona(usuario);
			}
			else {
			dao.atualiza(usuario);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		usuario = new Usuario();
		IdTipoUsuario = null;
	}
	
	public void cancelar() {
		usuario = new Usuario();
		IdTipoUsuario = null;
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
	
	public void carregarPeloId() {
		UsuarioDao dao = new UsuarioDao();
		

		
		
		try {
			usuario = dao.listaPorId(this.usuario.getId());
			IdTipoUsuario = this.usuario.getTipoUsuario().getId();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Usuario getUsuario() {
		return usuario;
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
	
	public void carregar(Usuario p) {
		usuario = p;
		
		IdTipoUsuario = p.getTipoUsuario().getId();
	}
	
	public List<TipoUsuario> getTodosTiposUsuarios(){
		
		List<TipoUsuario> lista = null;
		try {
		lista = new TipoUsuarioDao().listaTodos();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {	
			e.printStackTrace();
		}
		
		
		
		return lista;
	}
	
	public String redirecionar(Usuario u) {
		this.usuario = u;
		return "edit?faces-redirect=true&includeViewParams=true";
	}

	public Integer getIdTipoUsuario() {
		return IdTipoUsuario;
	}

	public void setIdTipoUsuario(Integer idTipoUsuario) {
		this.IdTipoUsuario = idTipoUsuario;
	}
}
