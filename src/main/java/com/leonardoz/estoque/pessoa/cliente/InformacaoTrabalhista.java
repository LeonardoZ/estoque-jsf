package com.leonardoz.estoque.pessoa.cliente;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Embeddable
public class InformacaoTrabalhista {

	@Column(name = "numero_ctps", length = 30)
	private String numeroCTPS;
	
	@Column(name = "serie_ctps", length = 30)
	private String serieCTPS;
	
	@Column(name = "pis_pasesp", length = 30)
	private String pisPASESP;
	
	@Column(name = "estado_ctps", length = 30)
	private String estadoCTPS;

	public InformacaoTrabalhista() {

	}

	public String getNumeroCTPS() {
		return numeroCTPS;
	}

	public void setNumeroCTPS(String numeroCTPS) {
		this.numeroCTPS = numeroCTPS;
	}

	public String getSerieCTPS() {
		return serieCTPS;
	}

	public void setSerieCTPS(String serieCTPS) {
		this.serieCTPS = serieCTPS;
	}

	public String getPisPASESP() {
		return pisPASESP;
	}

	public void setPisPASESP(String pisPASESP) {
		this.pisPASESP = pisPASESP;
	}

	public String getEstadoCTPS() {
		return estadoCTPS;
	}

	public void setEstadoCTPS(String estadoCTPS) {
		this.estadoCTPS = estadoCTPS;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof InformacaoTrabalhista)) {
			return false;
		}
		InformacaoTrabalhista castOther = (InformacaoTrabalhista) other;
		return new EqualsBuilder().append(numeroCTPS, castOther.numeroCTPS).append(serieCTPS, castOther.serieCTPS)
				.append(pisPASESP, castOther.pisPASESP).append(estadoCTPS, castOther.estadoCTPS).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(numeroCTPS).append(serieCTPS).append(pisPASESP).append(estadoCTPS)
				.toHashCode();
	}

}
