package com.leonardoz.estoque.compra;

import java.io.Serializable;
import java.util.Date;

import com.leonardoz.estoque.modelo.entidade.Filtro;

public class FiltroCompra extends Filtro implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date data;

    public Date getData() {
	return data;
    }

    public void setData(Date data) {
	this.data = data;
    }

}
