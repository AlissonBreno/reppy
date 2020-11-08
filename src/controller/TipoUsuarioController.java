package controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.TipoUsuarioDao;
import modelo.TipoUsuario;

@ManagedBean
@ViewScoped
public class TipoUsuarioController {
	
	private TipoUsuario tipoUsuario = new TipoUsuario();
	
	public void salvarDados() {
		TipoUsuarioDao dao = new TipoUsuarioDao();
		
		System.out.println("Id: " + tipoUsuario.getId());
		System.out.println("Nome: " + tipoUsuario.getName());
		System.out.println("Descricao: " + tipoUsuario.getDescricao());
		
		try {
			if(tipoUsuario.getId() == null) {				
				dao.adiciona(tipoUsuario);
			}else{
				dao.atualiza(tipoUsuario);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tipoUsuario = new TipoUsuario();
		
	}
	
	public List<TipoUsuario> getTipoUsuarios(){
		List<TipoUsuario> lista = null;
		
		try {
			lista =  new TipoUsuarioDao().listaTodos();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public void remover(TipoUsuario tu) {
		try {
			new TipoUsuarioDao().remove(tu.getId());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Função para redirecionar a página.
	public String redirecionar(TipoUsuario tu) {
		this.tipoUsuario = tu;
		return "edit?faces-redirect=true&includeViewParams=true";
	}
	
	public void carregarPeloId() {
		TipoUsuarioDao dao = new TipoUsuarioDao();
		try {
			tipoUsuario = dao.listaPorId(this.tipoUsuario.getId());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

}
