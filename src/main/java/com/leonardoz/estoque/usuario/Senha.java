package com.leonardoz.estoque.usuario;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Senha {

    @Column(columnDefinition = "binary(20)", nullable = false, name = "senha_hash")
    private byte[] senhaHash;

    @Column(columnDefinition = "binary(8)", nullable = false)
    private byte[] salt;

    public Senha() {

    }

    public byte[] getPasswordHash() {
	return senhaHash;
    }

    public void setPassword(byte[] pass) {
	this.senhaHash = pass;
    }

    public byte[] getSalt() {
	return this.salt;
    }

    public void setSalt(byte[] salt) {
	this.salt = salt;
    }

}
