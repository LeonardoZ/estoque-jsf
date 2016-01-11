package com.leonardoz.estoque;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.leonardoz.estoque.pagamento.PagamentoTest;
import com.leonardoz.estoque.pessoa.CnpjTest;
import com.leonardoz.estoque.pessoa.CpfTest;
import com.leonardoz.estoque.pessoa.InscricaoEstadualTest;
import com.leonardoz.estoque.pessoa.NumeroDeTelefoneTest;
import com.leonardoz.estoque.produto.DinheiroTest;
import com.leonardoz.estoque.produto.PorcentagemTest;
import com.leonardoz.estoque.produto.ProdutoTest;
import com.leonardoz.estoque.produto.QuantidadeFracionadaTest;
import com.leonardoz.estoque.produto.QuantidadeTest;

@RunWith(Suite.class)
@SuiteClasses({ PorcentagemTest.class, DinheiroTest.class, ProdutoTest.class, CnpjTest.class, CpfTest.class,
		PagamentoTest.class, InscricaoEstadualTest.class, NumeroDeTelefoneTest.class, QuantidadeFracionadaTest.class,
		QuantidadeTest.class })
public class TodosTestes {

}
