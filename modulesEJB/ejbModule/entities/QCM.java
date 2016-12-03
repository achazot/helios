package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class QCM 
{
	@Id 
	@GeneratedValue
	private int id;
	private String title; 
	private int total;
	private int minimum;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creation; 
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiration; 
	private boolean areAnswersShown; 
	
	
/** TODO : add this when chapter class is done  + getter & setter **/	
//	@ManyToOne 
//	private Chapter chapter; 
	
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
	
	@Column(name="total")
	public int getTotal()
	{
		return total;
	}
	
	@Column(name="minimum")
	public int getMinimum()
	{
		return minimum;
	}
	
	@Column(name="creation")
	public Date getCreation()
	{
		return creation;
	}
	
	@Column(name="expiration")
	public Date getExpiration()
	{
		return expiration;
	}
	
	@Column(name="areAnswersShown")
	public boolean areAnswersShown()
	{
		return areAnswersShown;
	}

	public void setId( int aid )
	{
		id = aid;
	}

	public void setTitle( String atitle )
	{
		title = atitle;
	}

	public void setTotal( int atotal )
	{
		total = atotal;
	}
	
	public void setMinimum( int amin )
	{
		minimum = amin;
	}

	public void setCreation( Date acreation )
	{
		creation = acreation;
	}
	
	public void setExpiration( Date aexpiration )
	{
		expiration = aexpiration;
	}
	
	public void setAnswersShown( boolean b )
	{
		areAnswersShown = b; 
	}
}
