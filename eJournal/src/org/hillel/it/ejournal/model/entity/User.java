package org.hillel.it.ejournal.model.entity;

import java.util.Date;

public class User extends Entity {
	private String name;
	private String surname;
	private Date birthDate;
	private Sex sex;
	private Role role;
	private String login;
	private String password;

	public User(String name, String surname, Date birthDate, Sex sex,
			Role role, String login, String password) {
		super(null, EntityType.USER);
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.sex = sex;
		this.role = role;
		this.login = login;
		this.password = password;
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

	@Override
	public String toString(){
		return ("\nName: "+name+
				"\nSurname: "+surname+
				"\nDate of birth: "+birthDate+
				"\nSex: "+sex+
				"\nRole: "+role);
	}
}
