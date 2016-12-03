package beans;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.QCM;
import entities.User;

@Stateless
@LocalBean
public class QCMsManager 
{
	@PersistenceContext(unitName = "heliosPU")
	private EntityManager em; 
	
	/**
	 ***************** createQCM ************************** 
	 * @param title
	 * @param total
	 * @param expiration
	 * @param answersAreShown
	 */
	public void createQCM( String title, int total, Date expiration, boolean answersAreShown)
	{
		QCM qcm = new QCM();
		qcm.setTitle( title );
		qcm.setTotal( total );
		qcm.setMinimum( total );
		qcm.setCreation( new Date() );
		qcm.setExpiration( expiration );
		qcm.setAnswersShown( answersAreShown );
		
		em.persist(qcm); 
	}
	
}
