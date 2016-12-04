package beans;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Chapter;
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
	public void createQCM( String title, int total, Date creation,  Date expiration, boolean answersAreShown, Chapter chapter  )
	{
		QCM qcm = new QCM();
		
		qcm.setTitle( title );
		qcm.setTotal( total );
		qcm.setMinimum( total );
		qcm.setCreation( creation );
		qcm.setExpiration( expiration );
		qcm.setAnswersShown( answersAreShown );
		qcm.setChapter( chapter );
		
		chapter.setQcm( true );
		em.merge( chapter );
		em.persist(qcm); 
	}
	
}
