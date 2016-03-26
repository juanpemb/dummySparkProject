package org.jp.spark.model;

public class HomeAssistant {

	private String name;
	private String lastName;
	private String mail;
	private String phone;
	private String valoracion;
	private String cp;

	public HomeAssistant(String name,
			String lastName,
			String mail,
			String phone,
			String valoracion,
			String cp) {
		this.name = name;
		this.lastName = lastName;
		this.mail = mail;
		this.phone=phone;
		this.valoracion = valoracion;
		this.cp = cp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getValoracion() {
		return valoracion;
	}

	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

}
