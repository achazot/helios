package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Question 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String text;
	private int points; 
	@ManyToOne 
	private QCM qcm;
	@OneToMany(fetch = FetchType.EAGER, mappedBy="question")
	private List<Answer> answers; 
	
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
	
	public List<Answer> getAnswers()
	{
		return answers;
	}
	
	public void addAnswer(Answer answer)
	{
	    answers.add(answer);
	}
}
