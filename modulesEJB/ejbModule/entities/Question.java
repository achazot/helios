package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Question 
{
	@Id
	@GeneratedValue
	private int id;
	private String text;
	private int points; 
	@ManyToOne 
	private QCM qcm;
	
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
	
	@Column(name="points")
	public int getPoints() 
	{
		return points;
	}
	
	public void setPoints(int apoints) 
	{
		points = apoints;
	} 
	
	@Column(name="qcm")
	public QCM getQcm()
	{
		return qcm;
	}
	
	public void setQcm(QCM aqcm)
	{
		qcm = aqcm; 
	}
	
}
