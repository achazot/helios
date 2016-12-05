package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Answer 
{
	@Id
	@GeneratedValue
	private int id; 
	private String text; 
	private boolean valid; 
	
	@ManyToOne 
	private Question question;

	@Column(name="id")
	public int getId()
	{
		return id;
	}
	
	@Column(name="text")
	public String getText() 
	{
		return text;
	}

	public void setText(String atext) 
	{
		text = atext;
	} 
	
	@Column(name="valid")
	public boolean getValid() 
	{
		return valid;
	}

	public void setValid(boolean avalid) 
	{
		valid = avalid;
	}
	
	@Column(name="question")
	public Question getQuestion() 
	{
		return question;
	}

	public void setQuestion(Question aquestion) 
	{
		question = aquestion;
	}
	
}
