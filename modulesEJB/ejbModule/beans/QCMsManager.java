package beans;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Answer;
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
	public QCM createQCM( String title, Date creation,  Date expiration, boolean answersAreShown, Chapter chapter  )
	{
		QCM qcm = new QCM();
		
		qcm.setTitle( title );
		qcm.setTotal( 0 );
		qcm.setMinimum( 100 );
		qcm.setCreation( creation );
		qcm.setExpiration( expiration );
		qcm.setAnswersShown( answersAreShown );
		qcm.setChapter( chapter );
		
		chapter.setQcm( true );
		em.merge( chapter );
		em.persist( qcm ); 
		return qcm;
	}
	
	public void createQuestions( int points, String text, QCM qcm )
	{
		Question question = new Question();
		qcm.setTotal( qcm.getTotal() + points );
		
		question.setPoints( points );
		question.setText( text );
		question.setQcm( qcm );
		
		em.merge(qcm);
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

	public Question findQuestionByPK(int questId) 
	{
		Question question = em.find(Question.class, questId);		
		return question;
	}
	
	public Answer createAnswer( String text, boolean valid, Question question)
	{
		Answer answer = new Answer();
		answer.setText( text );
		answer.setValid( valid );
		answer.setQuestion( question );
		
		em.persist( answer );
		return answer;
	}
	
	@SuppressWarnings("unchecked")
	public List<Answer> getAnswers( Question question )
	{
		Query query = em.createQuery("Select q from Answer q where q.question = ?1");
		query.setParameter(1, question);
		return query.getResultList();	
	}

	public void setAnswerToQuestion(Question question, Answer answer) 
	{
		question.addAnswer(answer);
	}
}
