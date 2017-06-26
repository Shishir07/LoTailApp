package com.coverfox.springmvc.model;

public class LogDetail {
	private long id;
	private String detail;
	public LogDetail(long id, String detail){
		this.id = id;
		this.detail = detail;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LogDetail))
			return false;
		LogDetail other = (LogDetail) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "LogDetail [id=" + id + ", detail=" + detail + "]";
	}
	
}
