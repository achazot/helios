package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Chapter
{
	@Id
	@GeneratedValue
	private int id;
	private String title;
	@Lob
	private String text;
	private boolean qcm; 
	@ManyToOne
	private Module module;
	
	@Column(name="id")
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;

	}
	@Column(name="title")
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	@Column(name="text")
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	
	@Column(name="module")
	public Module getModule()
	{
		return module;
	}
	public void setModule(Module module)
	{
		this.module = module;
	}
	
	@Column(name="qcm")
	public boolean getQcm()
	{
		return qcm;
	}
	public void setQcm(boolean b)
	{
		this.qcm = b;
	}	
}
