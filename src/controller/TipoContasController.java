package controller;


import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.TipoContasDao;
import modelo.TipoContas;

@ManagedBean
@ViewScoped
public class TipoContasController {
	
	private TipoContas tipoContas = new TipoContas();
	
	public void salvarDados() {
		TipoContasDao dao = new TipoContasDao();
		
		System.out.println("Id: " + tipoContas.getId());
		System.out.println("Tipo: " + tipoContas.getTipo());
		System.out.println("Credor: " + tipoContas.getCredor());
		
		try {
			if(tipoContas.getId() == null) {				
				dao.adiciona(tipoContas);
			}else{
				dao.atualiza(tipoContas);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tipoContas = new TipoContas();
		
	}
	
	public List<TipoContas> getTipoContas(){
		List<TipoContas> lista = null;
		
		try {
			lista =  new TipoContasDao().listaTodos();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public void remover(TipoContas tu) {
		try {
			new TipoContasDao().remove(tu.getId());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Função para redirecionar a página.
	public String redirecionar(TipoContas tu) {
		this.tipoContas = tu;
		return "edit?faces-redirect=true&includeViewParams=true";
	}
	
	public void carregarPeloId() {
		TipoContasDao dao = new TipoContasDao();
		try {
			tipoContas = dao.listaPorId(this.tipoContas.getId());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public TipoContas getTipoConta() {
		return tipoContas;
	}

}
