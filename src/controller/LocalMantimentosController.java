package controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.LocalMantimentosDao;
import modelo.LocalMantimentos;

@ManagedBean
@ViewScoped
public class LocalMantimentosController {
private LocalMantimentos localMantimento = new LocalMantimentos();
	
	public void salvarDados() {
		LocalMantimentosDao dao = new LocalMantimentosDao();
		System.out.println("uai");
		try {
			if(localMantimento.getId() == null) {				
				dao.adiciona(localMantimento);
			}else{
				dao.atualiza(localMantimento);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		localMantimento = new LocalMantimentos();
		
	}
	
	public List<LocalMantimentos> getLocalMantimentos(){
		
		List<LocalMantimentos> lista = null;
		
		try {
			lista =  new LocalMantimentosDao().listaTodos();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public void remover(LocalMantimentos lm) {
		try {
			new LocalMantimentosDao().remove(lm.getId());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Função para redirecionar a página.
	public String redirecionar(LocalMantimentos lm) {
		this.localMantimento = lm;
		return "edit?faces-redirect=true&includeViewParams=true";
	}
	
	public void carregarPeloId() {
		LocalMantimentosDao dao = new LocalMantimentosDao();
		try {
			localMantimento = dao.listaPorId(this.localMantimento.getId());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public LocalMantimentos getLocalMantimento() {
		return localMantimento;
	}
}
