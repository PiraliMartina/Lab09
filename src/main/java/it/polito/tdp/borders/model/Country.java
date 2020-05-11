package it.polito.tdp.borders.model;

public class Country {
	
	private String abb;
	private int code;
	private String name;
	
	public Country(String abb, int code, String name) {
		super();
		this.abb = abb;
		this.code = code;
		this.name = name;
	}

	public String getAbb() {
		return abb;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (code != other.code)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
	
	

}
