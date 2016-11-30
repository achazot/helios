package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student
{
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String surname;
	private String mail;
	private String password;

	@GeneratedValue
	@Column(name="id")
	public int getId()
	{
		return id;
	}
	
	@Column(name="name")
	public String getName()
	{
		return name;
	}
	
	@Column(name="surname")
	public String getSurname()
	{
		return surname;
	}
	
	@Column(name="mail")
	public String getMail()
	{
		return mail;
	}

	@Column(name="password")
	public String getPassword()
	{
		return password;
	}

	public int setId()
	{
		return id;
	}

	public String setName()
	{
		return name;
	}

	public String setSurname()
	{
		return surname;
	}

	public String setMail()
	{
		return mail;
	}

	public String setPassword()
	{
		return password;
	}


}