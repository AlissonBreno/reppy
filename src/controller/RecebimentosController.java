package controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.UsuarioDao;
import dao.RecebimentosDao;
import dao.TipoUsuarioDao;
import modelo.Contas;
import modelo.Recebimentos;
import modelo.TipoUsuario;
import modelo.Usuario;

@ManagedBean
@ViewScoped
public class RecebimentosController {
	
	private Recebimentos recebimentos = new Recebimentos();
	public Recebimentos getRecebimentos() {
		return recebimentos;
	}

	public void setRecebimentos(Recebimentos recebimentos) {
		this.recebimentos = recebimentos;
	}

	public Integer getIdConta() {
		return IdConta;
	}

	public void setIdConta(Integer idConta) {
		IdConta = idConta;
	}

	public Integer getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		IdUsuario = idUsuario;
	}

	private Integer IdConta;
	private Integer IdUsuario;
	
	public void salvar() {
		
		RecebimentosDao dao = new RecebimentosDao();
		
		try {
			
			Usuario u = new Usuario();
			recebimentos.setUsuario(u);
			
			Contas c = new Contas();
			recebimentos.setConta(c);
			
			
			if (recebimentos.getId() == null) {
			dao.adiciona(recebimentos);
			}
			else {
			dao.atualiza(recebimentos);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		recebimentos = new Recebimentos();
		IdUsuario = null;
		IdConta = null;
	}
	
	public void cancelar() {
		recebimentos = new Recebimentos();
		IdUsuario = null;
		IdConta = null;
	}
	
	public List<Recebimentos> getTodosRecebimentos(){
		List<Recebimentos> lista = null;
		try {
			lista = new RecebimentosDao().listaTodos();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;	
	}
	
	public void carregarPeloId() {
		RecebimentosDao dao = new RecebimentosDao();
		

		
		
		try {
			recebimentos = dao.listaPorId(this.recebimentos.getId());
			IdUsuario = this.getIdUsuario();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void remover(Recebimentos p) {
		try {	
			new RecebimentosDao().remove(p.getId());			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void carregar(Recebimentos p) {
		recebimentos = p;
		
		IdUsuario = p.getUsuario().getId();
		
		IdConta = p.getConta().getId();
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
	
	public String redirecionar(Recebimentos u) {
		this.recebimentos = u;
		return "edit?faces-redirect=true&includeViewParams=true";
	}

}
