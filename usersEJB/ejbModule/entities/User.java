package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User
{
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String surname;
	private String mail;
	private String password;
	private String group;
	
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

	@Column(name="group")
	public String getGroup()
	{
		return group;
	}
	
	public void setId( int aid )
	{
		id = aid;
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

	public void setGroup( String agroup )
	{
		group = agroup;
	}


}