package com.utn.tacs.tp2016c1g4.marvel_webapp.business;

public class Personaje {

	private int id;
	private String name;

	public Personaje(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}