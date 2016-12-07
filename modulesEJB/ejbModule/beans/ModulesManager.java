package beans;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Chapter;
import entities.Module;
import entities.Subscription;
import entities.User;

@Stateless
@LocalBean
public class ModulesManager
{
	@PersistenceContext(unitName = "heliosPU")
	private EntityManager em;

		
	/**
	 ****************** createModule ****************
	 * 
	 * @param title
	 * @param teacher
	 */
	public void createModule(String title, User teacher)
	{
		if (teacher.getGrp().equals("teacher"))
		{
			Module module = new Module();
			module.setTitle(title);
			module.setTeacher(teacher);
			
			em.persist(module);
		}
	}

	/**
	 ****************** getModules ****************
	 *
	 * @return module list
	 */
	@SuppressWarnings("unchecked")
	public List<Module> getModules()
	{
		return em.createQuery("From Module m").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Module> getModules(User user)
	{
		if (user.getGrp().equals("teacher"))
		{
			Query query = em.createQuery("Select m from Module m where m.teacher = ?1");
			query.setParameter(1, user);
			return query.getResultList();
		}
		else if (user.getGrp().equals("student"))
		{
			Query query = em.createQuery("select s.module from Subscription s where s.student = ?1");
			query.setParameter(1, user);
			return query.getResultList();
		}
		else
			return null;
		
	}
	
	public Module getModule(int id)
	{
		return (Module) em.find(Module.class, id);
	}
	
	
	/**
	 ************** studentSubscribe **************
	 *
	 * @param student
	 * @param module
	 */
	public void studentSubscribe(User student, Module module)
	{
		if (student.getGrp().equals("student"))
		{
			Subscription sub = new Subscription();
			sub.setDate(new Date());
			sub.setModule(module);
			sub.setStudent(student);
			
			em.persist(sub);
		}
	}
	
	public Module findModuleByPK(List<Module> mList, int mId)
	{
		for(Module m : mList)
		{
			if( m.getId() == mId )
				return m;
		}
		return null; 
	}
	

	
	public Chapter findChapterByPK(List<Chapter> cList, int cId)
	{
		for(Chapter c : cList)
		{
			if( c.getId() == cId )
				return c;
		}
		return null; 
	}
	
	@SuppressWarnings("unchecked")
	public List<Chapter> getChapters(Module m)
	{
		Query query = em.createQuery("Select c from Chapter c where c.module = ?1");
		query.setParameter(1, m);
		return query.getResultList();
	}
	
	
	public Chapter getChapter(int id)
	{
		return (Chapter) em.find(Chapter.class, id);
	}

	public void createChapter(String chapTitle, String chapText, Module module) 
	{
		Chapter chapter = new Chapter();
		chapter.setTitle(chapTitle);
		chapter.setText(chapText);
		chapter.setQcm(false);
		chapter.setModule(module);
		
		em.persist(chapter);
	}
	
	public void incrementSubscription (User user, Module module)
	{
		Subscription sub = getSubscriptionByStudentAndModule(user, module);
		if (sub == null) return;
		
	}

	public Subscription getSubscriptionByStudentAndModule ( User student, Module module )
	{
		Query query = em.createQuery("Select s from Chapter s where s.module = ?1 and s.student = ?2");
		query.setParameter(1, module);
		query.setParameter(2, student);
		return query.getResultList().isEmpty() ? null : (Subscription) query.getSingleResult();
	}
}
