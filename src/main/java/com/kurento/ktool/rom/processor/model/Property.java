package com.kurento.ktool.rom.processor.model;

import com.google.gson.JsonElement;

public class Property extends DataItem {

	public Property(String name, String doc, TypeRef type, boolean optional) {
		super(name, doc, type, optional);
	}

	public Property(String name, String doc, TypeRef type,
			JsonElement defaultValue) {
		super(name, doc, type, defaultValue);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Property [type()=");
		sb.append(getType());
		sb.append(", required()=");
		sb.append(isOptional());
		sb.append(", doc()=");
		sb.append(getDoc());
		if (isOptional()) {
			sb.append(", defaultValue()=");
			sb.append(getDefaultValue());
		}
		sb.append("]");

		return sb.toString();
	}

}
