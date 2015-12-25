package com.leonardoz.estoque.produto.unidade;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CadastroUnidadeDeMedidaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private UnidadeDeMedida unidade = new UnidadeDeMedida();

	@Inject
	private UnidadesDeMedida unidades;

	public CadastroUnidadeDeMedidaBean() {

	}

	@PostConstruct
	public void iniciar() {
		if (unidade == null) {
			unidade = new UnidadeDeMedida();
		}
	}

	public List<UnidadeDeMedida> listarUnidadeDeMedidas() {
		return unidades.recuperarUnidadeDeMedidas();
	}


	public void salvarUnidadeDeMedida() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.unidades.guardarUnidadeDeMedida(unidade);
			this.unidade = new UnidadeDeMedida();
			context.addMessage(null, new FacesMessage("Unidade de medida salva com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao salvar a unidade.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void novaUnidadeDeMedida() {
		this.unidade = new UnidadeDeMedida();
	}

	public void removerUnidadeDeMedida() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.unidades.removerUnidadeDeMedida(Long.valueOf(this.unidade.getId()));
			this.unidade = new UnidadeDeMedida();
			context.addMessage(null, new FacesMessage("Unidade de medida removida com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao remover a unidade.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}

	}

	public UnidadeDeMedida getUnidadeDeMedida() {
		return unidade;
	}

	public void setUnidadeDeMedida(UnidadeDeMedida unidade) {
		this.unidade = unidade;
	}

}
