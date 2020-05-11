package it.polito.tdp.borders.model;

public class Border {
	
	private int id;
	private Country c1;
	private Country c2;
	
	public Border(int id, Country c1, Country c2) {
		super();
		this.id = id;
		this.c1 = c1;
		this.c2 = c2;
	}

	public int getId() {
		return id;
	}

	public Country getC1() {
		return c1;
	}

	public Country getC2() {
		return c2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Border other = (Border) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
