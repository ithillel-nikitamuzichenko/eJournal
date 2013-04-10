package org.hillel.it.ejournal.model.entity;

import java.util.Date;

public class User implements Entity {
	protected String name;
	protected String surname;
	protected Date birthDate;
	protected Sex sex;
	protected Role role;
	protected String login;
	private String password;
	protected Integer id = null;

	public User(String name, String surname, Date birthDate, Sex sex,
			Role role, String login, String password) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.sex = sex;
		this.role = role;
		this.login = login;
		this.password = password;
	}

	@Override
	public EntityType getEntityType() {
		return EntityType.USER;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		if (this.id == null) {
			this.id = id;
		}
	}

	@Override
	public EntityLog getEntityLog() {
		return null;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Sex getSex() {
		return sex;
	}

	public Role getRole() {
		return role;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getInfoString() {
		return (String.format(
				"Name: %s\nSurname: %s\nDate of birth: %s\nSex: %s\nRole: %s",
				name, surname, birthDate, sex, role));
	}
}
