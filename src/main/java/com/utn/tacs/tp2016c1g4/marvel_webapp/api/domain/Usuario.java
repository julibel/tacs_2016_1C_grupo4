package com.utn.tacs.tp2016c1g4.marvel_webapp.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario implements Entity {

	@JsonProperty("_id")
	private Long id;
	private String userName;
	private String email;
	private Long idPerfil;
	private String pass;
	private int lastAccess;

	public Usuario() {

	};

	public Usuario(Long id, Long idPerfil, String userName, String pass) {
		this.id = id;
		this.userName = userName;
		this.idPerfil = idPerfil;
		this.pass = pass;
	}

	public Long getId() {
		return id;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public int getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(int lastAccess) {
		this.lastAccess = lastAccess;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}