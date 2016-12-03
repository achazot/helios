package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Module;
import entities.User;

@Stateless
@LocalBean
public class ModulesManager
{
	@PersistenceContext(unitName = "heliosPU")
	private EntityManager em;

	public void create(String title, User teacher)
	{
		if (teacher.getGrp().equals("teacher"))
		{
			Module module = new Module();
			module.setTitle(title);
			module.setTeacher(teacher);
			
			em.persist(module);
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<Module> getModules()
	{
		return em.createQuery("From Module m").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Module> getModules(User teacher)
	{
		Query query = em.createQuery("Select m from Module m where m.teacher:=arg1");
		query.setParameter("arg1", teacher);
		return query.getResultList();
	}
	
}
