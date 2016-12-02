package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User
{
	@Id
	private String login;
	private String name;
	private String surname;
	private String mail;
	private String password;
	private String grp;
	
	@Column(name="login")
	public String getLogin()
	{
		return login;
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

	@Column(name="grp")
	public String getGrp()
	{
		return grp;
	}
	
	public void setLogin( String alogin )
	{
		login = alogin;
	}

	public void setName( String aname )
	{
		name = aname;
	}

	public void setSurname( String asurname )
	{
		surname = asurname;
	}

	public void setMail( String amail )
	{
		mail = amail;
	}

	public void setPassword( String apassword )
	{
		password = apassword;
	}

	public void setGrp( String agrp )
	{
		grp = agrp;
	}


}