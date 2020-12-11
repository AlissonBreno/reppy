package controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.TipoMantimentosDao;
import modelo.TipoMantimentos;


@ManagedBean
@ViewScoped
public class TipoMantimentosController {
	
	private TipoMantimentos tipoMantimentos = new TipoMantimentos();
	
	public void salvarDados() {
		TipoMantimentosDao dao = new TipoMantimentosDao();
		
		try {
			if(tipoMantimentos.getId() == null) {				
				dao.adiciona(tipoMantimentos);
			}else{
				dao.atualiza(tipoMantimentos);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tipoMantimentos = new TipoMantimentos();
		
	}
	
	public List<TipoMantimentos> getTipoMantimentos(){
		
		List<TipoMantimentos> lista = null;
		
		try {
			lista =  new TipoMantimentosDao().listaTodos();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public void remover(TipoMantimentos tm) {
		try {
			new TipoMantimentosDao().remove(tm.getId());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Função para redirecionar a página.
	public String redirecionar(TipoMantimentos tm) {
		this.tipoMantimentos = tm;
		return "edit?faces-redirect=true&includeViewParams=true";
	}
	
	public void carregarPeloId() {
		TipoMantimentosDao dao = new TipoMantimentosDao();
		try {
			tipoMantimentos = dao.listaPorId(this.tipoMantimentos.getId());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public TipoMantimentos getTipoMantimento() {
		return tipoMantimentos;
	}
}
