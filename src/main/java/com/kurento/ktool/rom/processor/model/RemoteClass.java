package com.kurento.ktool.rom.processor.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RemoteClass extends Type {

	@SerializedName("extends")
	private TypeRef extendsProp;
	private List<Property> properties;
	private List<Method> methods;
	private List<TypeRef> events;
	private boolean abstractClass;

	public RemoteClass(String name, String doc, TypeRef extendsProp) {
		super(name, doc);
		this.extendsProp = extendsProp;
		this.properties = new ArrayList<Property>();
		this.methods = new ArrayList<Method>();
		this.events = new ArrayList<TypeRef>();
	}

	public RemoteClass(String name, String doc, TypeRef extendsProp,
			List<Property> properties, List<Method> methods,
			List<TypeRef> events) {
		super(name, doc);
		this.extendsProp = extendsProp;
		this.properties = properties;
		this.methods = methods;
		this.events = events;
	}

	public List<Property> getAllProperties() {
		RemoteClass parent = null;
		if (this.getExtends() != null) {
			parent = (RemoteClass) this.getExtends().getType();
		}
		List<Property> res = new ArrayList<Property>();
		if (parent != null) {
			res.addAll(parent.getAllProperties());
		}
		res.addAll(this.getProperties());
		return res;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public List<TypeRef> getEvents() {
		return events;
	}

	public TypeRef getExtends() {
		return extendsProp;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public boolean isAbstract() {
		return abstractClass;
	}

	public void addProperty(Property property) {
		this.properties.add(property);
	}

	public void addMethod(Method method) {
		this.methods.add(method);
	}

	public void setAbstract(boolean abstractModel) {
		this.abstractClass = abstractModel;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public void setEvents(List<TypeRef> events) {
		this.events = events;
	}

	public void setExtendsProp(TypeRef extendsProp) {
		this.extendsProp = extendsProp;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	public boolean isAssignableTo(String remoteClassName) {
		if (this.getName().equals(remoteClassName)) {
			return true;
		} else {
			if (getExtends() != null) {
				return ((RemoteClass) getExtends().getType())
						.isAssignableTo(remoteClassName);
			} else {
				return false;
			}
		}
	}

	@Override
	public List<ModelElement> getChildren() {
		List<ModelElement> children = new ArrayList<ModelElement>();
		if (extendsProp != null) {
			children.add(extendsProp);
		}
		children.addAll(properties);
		children.addAll(methods);
		children.addAll(events);
		return children;
	}

	@Override
	public String toString() {
		return "RemoteClass [extends=" + extendsProp + ", properties="
				+ properties + ", methods=" + methods + ", doc=" + getDoc()
				+ ", name=" + getName() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (abstractClass ? 1231 : 1237);
		result = prime * result
				+ ((properties == null) ? 0 : properties.hashCode());
		result = prime * result + ((events == null) ? 0 : events.hashCode());
		result = prime * result
				+ ((extendsProp == null) ? 0 : extendsProp.hashCode());
		result = prime * result + ((methods == null) ? 0 : methods.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RemoteClass other = (RemoteClass) obj;
		if (abstractClass != other.abstractClass)
			return false;
		if (properties == null) {
			if (other.properties != null)
				return false;
		} else if (!properties.equals(other.properties))
			return false;
		if (events == null) {
			if (other.events != null)
				return false;
		} else if (!events.equals(other.events))
			return false;
		if (extendsProp == null) {
			if (other.extendsProp != null)
				return false;
		} else if (!extendsProp.equals(other.extendsProp))
			return false;
		if (methods == null) {
			if (other.methods != null)
				return false;
		} else if (!methods.equals(other.methods))
			return false;
		return true;
	}

}
