package com.example.qiniu.qiniuyun.common;

import java.io.Serializable;

/**
 * Request 实现序列化
 */
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;
	private String className;
	private String methodName;
	private Class[] parameterTypes;
	private Object[] parameterValues;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Object[] getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(Object[] parameterValues) {
	    this.parameterValues = parameterValues;
	}
}
