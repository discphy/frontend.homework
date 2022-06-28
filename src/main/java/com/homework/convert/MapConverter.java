package com.homework.convert;

import com.homework.object.EliObject;

import jakarta.persistence.AttributeConverter;

public class MapConverter implements AttributeConverter<EliObject, String> {

	@Override
	public String convertToDatabaseColumn(EliObject attribute) {
		return attribute.toString();
	}

	@Override
	public EliObject convertToEntityAttribute(String dbData) {
		return new EliObject(dbData);
	}

}
