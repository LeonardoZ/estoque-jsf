package com.leonardoz.estoque.cliente;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Embeddable
public class InformacaoEleitoral {

    @Column(name = "numero_titulo_eleitoral", length = 30)
    private String numeroTituloEleitoral;

    @Column(name = "zona_eleitoral", length = 6)
    private String zonaEleitoral;

    @Column(name = "estado_eleitoral", length = 3)
    private String estadoEleitoral;

    public InformacaoEleitoral() {
    }

    @Size(max = 30)
    public String getNumeroTituloEleitoral() {
	return numeroTituloEleitoral;
    }

    public void setNumeroTituloEleitoral(String numeroTituloEleitoral) {
	this.numeroTituloEleitoral = numeroTituloEleitoral;
    }

    @Size(max = 6)
    public String getZonaEleitoral() {
	return zonaEleitoral;
    }

    public void setZonaEleitoral(String zonaEleitoral) {
	this.zonaEleitoral = zonaEleitoral;
    }

    @Size(max = 3)
    public String getEstadoEleitoral() {
	return estadoEleitoral;
    }

    public void setEstadoEleitoral(String estadoEleitoral) {
	this.estadoEleitoral = estadoEleitoral;
    }

    @Override
    public boolean equals(final Object other) {
	if (!(other instanceof InformacaoEleitoral)) {
	    return false;
	}
	InformacaoEleitoral castOther = (InformacaoEleitoral) other;
	return new EqualsBuilder().append(numeroTituloEleitoral, castOther.numeroTituloEleitoral)
		.append(zonaEleitoral, castOther.zonaEleitoral).append(estadoEleitoral, castOther.estadoEleitoral)
		.isEquals();
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(numeroTituloEleitoral).append(zonaEleitoral).append(estadoEleitoral)
		.toHashCode();
    }

}
