package com.example.qiniu.qiniuyun.common;

import java.io.Serializable;

/**
 * Response 实现序列化，实现向客户端友好响应效果
 */
public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	private Object object;
	private int status;
	private String desc;



	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Response [object=" + object + ", status=" + status + ", desc=" + desc + "]";
	}

}