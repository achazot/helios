package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Subscription
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private User student;
	@ManyToOne
	private Module module;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private int progress; 
	@OneToMany(fetch = FetchType.EAGER, mappedBy="subscription")
	private List<QCMInstance> qcminst; 
	
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
	
	@Column(name="module")
	public Module getModule()
	{
		return module;
	}
	
	public void setModule(Module module)
	{
		this.module = module;
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
	
	@Column(name="progress")
	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	public List<QCMInstance> getInstances()
	{
		return qcminst;
	}
	
	public void addInstance(QCMInstance qcmInstance)
	{
		qcminst.add(qcmInstance);
	}
	
}
