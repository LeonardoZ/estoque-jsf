package com.leonardoz.estoque.pessoa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SexoJpaConverter implements AttributeConverter<Sexo, String> {

    @Override
    public String convertToDatabaseColumn(Sexo attribute) {
	return attribute.getSigla();
    }

    @Override
    public Sexo convertToEntityAttribute(String dbData) {
	return Sexo.porSigla(dbData);
    }

}
