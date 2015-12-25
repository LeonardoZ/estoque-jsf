package com.leonardoz.estoque.pessoa.fornecedor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.leonardoz.estoque.modelo.valor.HorarioComercial;
import com.leonardoz.estoque.pessoa.NumeroDeTelefone;
import com.leonardoz.estoque.pessoa.PessoaJuridica;

@Entity
@Table(name = "fornecedor")
public class Fornecedor extends PessoaJuridica {

	private static final long serialVersionUID = 1L;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "valor", column = @Column(name = "telefone2", length = 17) ) })
	private NumeroDeTelefone telefone2;

	@Embedded
	private HorarioComercial horarioComercial;

	@Column(name = "fax", length = 20)
	private String fax;

	public Fornecedor() {
		carregarDetalhes();
	}

	public void carregarDetalhes() {
		super.iniciarCampos();
		if (horarioComercial == null) {
			horarioComercial = new HorarioComercial();
		} else {
			horarioComercial.carregarDiasDeTrabalho();
		}
		if (telefone2 == null)
			telefone2 = new NumeroDeTelefone();
	}

	public void configurarDetalhes() {
		horarioComercial.configurarDiasDeTrabalhoEspecificados();

	}

	public NumeroDeTelefone getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(NumeroDeTelefone telefone2) {
		this.telefone2 = telefone2;
	}

	public HorarioComercial getHorarioComercial() {
		return horarioComercial;
	}

	public void setHorarioComercial(HorarioComercial horarioComercial) {
		this.horarioComercial = horarioComercial;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Fornecedor)) {
			return false;
		}
		Fornecedor castOther = (Fornecedor) other;
		return super.equals(castOther);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
