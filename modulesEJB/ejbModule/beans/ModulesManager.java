package beans;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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


}
