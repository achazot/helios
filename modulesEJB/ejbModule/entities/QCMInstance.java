package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class QCMInstance
{

	@Id 
	@GeneratedValue
	private int id;
	@ManyToOne
	private User student;
	@ManyToOne
	private QCM original;
	private boolean done;
	private int note;
	private int trials;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name="id")
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	@Column(name="student")
	public User getStudent()
	{
		return student;
	}
	public void setStudent(User student)
	{
		this.student = student;
	}
	
	@Column(name="original")
	public QCM getOriginal()
	{
		return original;
	}
	public void setOriginal(QCM original)
	{
		this.original = original;
	}
	
	@Column(name="done")
	public boolean isDone()
	{
		return done;
	}
	public void setDone(boolean done)
	{
		this.done = done;
	}
	
	@Column(name="note")
	public int getNote()
	{
		return note;
	}
	public void setNote(int note)
	{
		this.note = note;
	}
	
	@Column(name="trials")
	public int getTrials()
	{
		return trials;
	}
	public void setTrials(int trials)
	{
		this.trials = trials;
	}
	
	@Column(name="date")
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	
}
