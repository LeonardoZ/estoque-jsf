package com.leonardoz.estoque.pessoa.cliente;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Embeddable
public class InformacaoEleitoral {

	@Column(name = "numero_titulo_eleitoral", length = 30)
	private String numeroTituloEleitoral;

	@Column(name = "zona_eleitoral", length = 6)
	private String zonaEleitoral;

	@Column(name = "estado_eleitoral", length = 3)
	private String estado_eletitoral;

	public InformacaoEleitoral() {
	}

	public String getNumeroTituloEleitoral() {
		return numeroTituloEleitoral;
	}

	public void setNumeroTituloEleitoral(String numeroTituloEleitoral) {
		this.numeroTituloEleitoral = numeroTituloEleitoral;
	}

	public String getZonaEleitoral() {
		return zonaEleitoral;
	}

	public void setZonaEleitoral(String zonaEleitoral) {
		this.zonaEleitoral = zonaEleitoral;
	}

	public String getEstado_eletitoral() {
		return estado_eletitoral;
	}

	public void setEstado_eletitoral(String estado_eletitoral) {
		this.estado_eletitoral = estado_eletitoral;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof InformacaoEleitoral)) {
			return false;
		}
		InformacaoEleitoral castOther = (InformacaoEleitoral) other;
		return new EqualsBuilder().append(numeroTituloEleitoral, castOther.numeroTituloEleitoral)
				.append(zonaEleitoral, castOther.zonaEleitoral).append(estado_eletitoral, castOther.estado_eletitoral)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(numeroTituloEleitoral).append(zonaEleitoral).append(estado_eletitoral)
				.toHashCode();
	}
	
	

}
