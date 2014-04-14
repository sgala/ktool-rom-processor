package com.kurento.ktool.rom.processor.codegen.function;

import java.util.LinkedList;
import java.util.List;

import com.kurento.ktool.rom.processor.model.ComplexType;
import com.kurento.ktool.rom.processor.model.Method;
import com.kurento.ktool.rom.processor.model.Param;
import com.kurento.ktool.rom.processor.model.Property;
import com.kurento.ktool.rom.processor.model.RemoteClass;
import com.kurento.ktool.rom.processor.model.Return;
import com.kurento.ktool.rom.processor.model.Type;

import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class RemoteClassDependencies implements TemplateMethodModelEx {

	@Override
	public Object exec(@SuppressWarnings("rawtypes") List arguments)
			throws TemplateModelException {

		Object type = arguments.get(0);

		if (type instanceof StringModel) {
			type = ((StringModel) type).getWrappedObject();
			if (type instanceof Return) {
				type = ((Return) type).getType();
			}
		}

		List<Type> types = new LinkedList<Type>();

		if (type instanceof RemoteClass) {
			RemoteClass remoteClass = (RemoteClass) type;

			for (Property prop : remoteClass.getProperties()) {
				types.add(prop.getType().getType());
			}

			RemoteClass clazz = remoteClass;
			while (clazz.getExtends() != null) {
				clazz = (RemoteClass) clazz.getExtends().getType();
				for (Property prop : clazz.getProperties()) {
					types.add(prop.getType().getType());
				}

			}

			for (Method method : remoteClass.getMethods()) {
				types.addAll(getMethodTypes(method));
			}

			if (remoteClass.getExtends() != null)
				types.remove(remoteClass.getExtends().getType());

			types.remove(remoteClass);
		}

		types = removeDuplicates(types);

		return types;
	}

	private List<Type> removeDuplicates(List<Type> original) {
		List<Type> types = new LinkedList<Type>();

		for (Type t : original) {
			if (!types.contains(t))
				types.add(t);
		}

		return types;
	}

	private List<Type> getMethodTypes(Method method) {
		List<Type> types = new LinkedList<Type>();

		for (Param p : method.getParams()) {

			if (p.getType().getType() instanceof RemoteClass
					|| p.getType().getType() instanceof ComplexType) {
				types.add(p.getType().getType());
			}

		}

		Return ret = method.getReturn();

		if (ret != null
				&& (ret.getType().getType() instanceof RemoteClass || ret
						.getType().getType() instanceof ComplexType))
			types.add(ret.getType().getType());

		return types;
	}
}
