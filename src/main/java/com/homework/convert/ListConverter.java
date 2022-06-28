package com.homework.convert;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.List;

public class ListConverter implements AttributeConverter<List<String>, String> {

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		return attribute.toString().replaceAll("[\\[\\]]", "");
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		return Arrays.asList(dbData.split(","));
	}
}
