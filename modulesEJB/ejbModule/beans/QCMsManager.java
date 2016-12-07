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
import entities.QCMInstance;
import entities.Question;
import entities.Subscription;
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
	
	@SuppressWarnings("unchecked")
	public QCM getQCMByChapter( Chapter chapter )
	{
		Query query = em.createQuery("Select c from QCM c where c.chapter = ?1");
		query.setParameter(1, chapter);
		List<Object> rl = query.getResultList();
		return rl.isEmpty() ? null : (QCM) rl.get(0);	
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

	public Answer getAnswer( int ansId )
	{
		Answer answer = em.find(Answer.class, ansId);		
		return answer;
	}
	
	public void setAnswerToQuestion(Question question, Answer answer) 
	{
		question.addAnswer(answer);
	}
	
	public QCMInstance getQCMInstance( QCM origin, User student ) 
	{
		Query query = em.createQuery("Select q from QCMInstance q where q.origin = ?1 and q.student = ?2");
		query.setParameter(1, origin);
		query.setParameter(2, student);

		return query.getResultList().isEmpty() ? null : (QCMInstance) query.getSingleResult() ;
	}

	public void updateQCMInstance( QCM origin, User student, boolean done, int note, Subscription subscription) 
	{
		Query query = em.createQuery("Select q from QCMInstance q where q.origin = ?1 and q.student = ?2");
		query.setParameter(1, origin);
		query.setParameter(2, student);

		if (query.getResultList().isEmpty()) // create instance
		{
			QCMInstance inst = new QCMInstance();
			inst.setDate(new Date());
			inst.setDone(done);
			inst.setNote(note);
			inst.setOrigin(origin);
			inst.setStudent(student);
			inst.setTrials(1);
			
			em.persist(inst);
		}
		else // update instance
		{
			QCMInstance inst = (QCMInstance) query.getSingleResult();
			inst.setDate(new Date());
			inst.setDone(done);
			inst.setNote(note);
			inst.setTrials(inst.getTrials()+1);
			
			em.merge(inst);
		}
		
	}

	
}
