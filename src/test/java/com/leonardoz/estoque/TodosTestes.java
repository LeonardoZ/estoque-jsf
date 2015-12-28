package com.leonardoz.estoque;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.leonardoz.estoque.pessoa.CnpjTest;
import com.leonardoz.estoque.pessoa.CpfTest;
import com.leonardoz.estoque.pessoa.InscricaoEstadualTest;
import com.leonardoz.estoque.pessoa.NumeroDeTelefoneTest;
import com.leonardoz.estoque.produto.QuantidadeFracionadaTest;
import com.leonardoz.estoque.produto.QuantidadeTest;

@RunWith(Suite.class)
@SuiteClasses({ CnpjTest.class, CpfTest.class, InscricaoEstadualTest.class, NumeroDeTelefoneTest.class,
		QuantidadeFracionadaTest.class, QuantidadeTest.class })
public class TodosTestes {

}
