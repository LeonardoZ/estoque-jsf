package com.leonardoz.estoque.usuario;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.modelo.entidade.Entidade;

@Entity
public class Usuario extends Entidade {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 120, unique = true)
    private String login;

    @Embedded
    private Senha senha;

    @Column(nullable = false, length = 120)
    private String name;

    public Usuario() {

    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    @Override
    public boolean equals(final Object other) {
	if (!(other instanceof Usuario)) {
	    return false;
	}
	Usuario castOther = (Usuario) other;
	return new EqualsBuilder().append(login, castOther.login).append(senha, castOther.senha)
		.append(name, castOther.name).isEquals();
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(login).append(senha).append(name).toHashCode();
    }

}
