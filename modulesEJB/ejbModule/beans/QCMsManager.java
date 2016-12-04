package beans;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Chapter;
import entities.QCM;
import entities.Question;

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
		em.persist( qcm ); 
	}
	
	public void createQuestions( int points, String text, QCM qcm )
	{
		Question question = new Question();
		question.setPoints( points );
		question.setText( text );
		question.setQcm( qcm );
		
		em.persist( question );
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> getQuestions( QCM qcm )
	{
		Query query = em.createQuery("Select q from Question q where q.qcm = ?1");
		query.setParameter(1, qcm);
		return query.getResultList();	
	}

	public QCM findQCMByPK(int qId) 
	{
		QCM qcm = em.find(QCM.class, qId);		
		return qcm;
	}
	
	public QCM getQCMByChapter( Chapter chapter )
	{
		Query query = em.createQuery("Select c from QCM c where c.chapter = ?1");
		query.setParameter(1, chapter);
		return (QCM) query.getResultList().get(0);	
	}
}
