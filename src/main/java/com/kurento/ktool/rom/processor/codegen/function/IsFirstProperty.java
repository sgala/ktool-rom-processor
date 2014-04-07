package com.kurento.ktool.rom.processor.codegen.function;

import java.util.List;

import com.kurento.ktool.rom.processor.model.Property;
import com.kurento.ktool.rom.processor.model.RemoteClass;

import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class IsFirstProperty implements TemplateMethodModelEx {

	@Override
	public Object exec(@SuppressWarnings("rawtypes") List arguments)
			throws TemplateModelException {

		RemoteClass thisRemoteClass = (RemoteClass) ((StringModel) arguments
				.get(0)).getWrappedObject();
		RemoteClass otherRemoteClass = (RemoteClass) ((StringModel) arguments
				.get(1)).getWrappedObject();

		if (!otherRemoteClass.getAllProperties().isEmpty()) {

			List<Property> props = otherRemoteClass.getAllProperties();

			if (props.isEmpty()) {
				return false;
			} else {
				return props.get(0).getType().getType() == thisRemoteClass;
			}

		} else {
			return false;
		}
	}

}
