package com.leonardoz.estoque.modelo.valor;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class HorarioDeTrabalho {

	@Temporal(TemporalType.TIME)
	@Column(name = "inicio_expediente", length = 14)
	private Date inicioDoExpediente;

	@Temporal(TemporalType.TIME)
	@Column(name = "inicio_almoco", length = 14)
	private Date inicioDoAlmoco;

	@Temporal(TemporalType.TIME)
	@Column(name = "termino_almoco", length = 14)
	private Date terminoDoAlmoco;

	@Temporal(TemporalType.TIME)
	@Column(name = "termino_expediente", length = 14)
	private Date terminoDoExpediente;

	@Column(name = "dias_da_semana", length = 48)
	private String diasDaSemanaFormatados;

	@Transient
	private DiaDaSemana[] diasDaSemana;

	public HorarioDeTrabalho() {
		iniciarCampos();
	}
	
	public void iniciarCampos(){
		diasDaSemana = new DiaDaSemana[7];
	}

	public void carregarDiasDeTrabalho() {
		System.out.println("Carregou os horários");
		boolean permitido = diasDaSemanaFormatados != null && !diasDaSemanaFormatados.isEmpty();
		if (permitido) {
			String[] separados = diasDaSemanaFormatados.split(",");
			diasDaSemana = new DiaDaSemana[separados.length];
			for (int i = 0; i < separados.length; i++) {
				String diaPorValor = separados[i];
				diasDaSemana[i] = DiaDaSemana.recuperaPorValor(diaPorValor);
			}
		}
	}


	public void configurarDiasDeTrabalhoEspecificados() {
		boolean permitido = diasDaSemana != null && diasDaSemana.length > 0;
		System.err.println("Vai ser carregado "+Arrays.asList(getDiasDaSemana()));
		System.err.println("O seguinte conteudo "+getDiasDaSemanaFormatados());
		if (permitido) {
			diasDaSemanaFormatados = Arrays.asList(diasDaSemana).stream().map(DiaDaSemana::getValor).collect(Collectors.joining(","));
		}
	}

	public Date getInicioDoExpediente() {
		return inicioDoExpediente;
	}

	public void setInicioDoExpediente(Date inicioDoExpediente) {
		this.inicioDoExpediente = inicioDoExpediente;
	}

	public Date getInicioDoAlmoco() {
		return inicioDoAlmoco;
	}

	public void setInicioDoAlmoco(Date inicioDoAlmoco) {
		this.inicioDoAlmoco = inicioDoAlmoco;
	}

	public Date getTerminoDoAlmoco() {
		return terminoDoAlmoco;
	}

	public void setTerminoDoAlmoco(Date terminoDoAlmoco) {
		this.terminoDoAlmoco = terminoDoAlmoco;
	}

	public Date getTerminoDoExpediente() {
		return terminoDoExpediente;
	}

	public void setTerminoDoExpediente(Date terminoDoExpediente) {
		this.terminoDoExpediente = terminoDoExpediente;
	}

	public String getDiasDaSemanaFormatados() {
		return diasDaSemanaFormatados;
	}

	public void setDiasDaSemanaFormatados(String diasDaSemanaFormatados) {
		this.diasDaSemanaFormatados = diasDaSemanaFormatados;
	}

	public DiaDaSemana[] getDiasDaSemana() {
		return diasDaSemana;
	}

	public void setDiasDaSemana(DiaDaSemana[] diasDaSemana) {
		System.out.println(Arrays.asList(diasDaSemana).toString());
		this.diasDaSemana = diasDaSemana;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof HorarioDeTrabalho)) {
			return false;
		}
		HorarioDeTrabalho castOther = (HorarioDeTrabalho) other;
		return new EqualsBuilder().append(inicioDoExpediente, castOther.inicioDoExpediente)
				.append(inicioDoAlmoco, castOther.inicioDoAlmoco).append(terminoDoAlmoco, castOther.terminoDoAlmoco)
				.append(terminoDoExpediente, castOther.terminoDoExpediente)
				.append(diasDaSemanaFormatados, castOther.diasDaSemanaFormatados).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(inicioDoExpediente).append(inicioDoAlmoco).append(terminoDoAlmoco)
				.append(terminoDoExpediente).append(diasDaSemanaFormatados).toHashCode();
	}

}
