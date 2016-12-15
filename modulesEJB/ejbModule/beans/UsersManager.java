package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;

@Stateless
@LocalBean
public class UsersManager
{

	@PersistenceContext(unitName = "heliosPU")
	private EntityManager em;

	public void register(String login, String name, String surname, String mail, String password, String group)
	{
		User user = new User();
		user.setLogin(login);
		user.setName(name);
		user.setSurname(surname);
		user.setMail(mail);
		user.setPassword(password);
		user.setGrp(group);
		
		em.persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers()
	{
		return em.createQuery("From User u").getResultList();
	}
	
	/**
	 * 
	 * @param mail
	 * @param password
	 * @return true if authentication succeed
	 */
	public boolean check(String login, String password)
	{
		User user = em.find(User.class, login);
		return ((user != null) && (user.getPassword().equals(password)));
	}
	
	public User getUserByLogin(String login)
	{
		User user = em.find(User.class, login);		
		return user;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> getUsers(String grp)
	{
		List<User> list = em.createQuery("From User u").getResultList();
		List<User> res = new ArrayList();
		for(User u : list)
		{
			if( u.getGrp().equals(grp) )
				res.add(u);
		}
		return res;
	}
	
	public void deleteUser(String login)
	{
		User u = em.find(User.class, login);
		
		if(u != null)
			em.remove(u);
	
	}

	public boolean createUser(String name, String surname, String mail, String group, String log, String password) 
	{
		if( em.find(User.class, log) != null )
			return false;
		else 
			register(log, name, surname, mail, password, group);
		return true;
	}
}
