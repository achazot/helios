package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Module
{
	@Id
	@GeneratedValue
	private int id;
	private String title;
	@ManyToOne
	private String teacher;

	@Column(name="id")
	public int getId()
	{
		return id;
	}
	
	@Column(name="title")
	public String getTitle()
	{
		return title;
	}
	
	@Column(name="teacher")
	public String getTeacher()
	{
		return teacher;
	}

	public void setId( int aid )
	{
		id = aid;
	}

	public void setTitle( String atitle )
	{
		title = atitle;
	}

	public void setTeacher( String ateacher )
	{
		teacher = ateacher;
	}
}
