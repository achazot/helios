package beans;

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

	public void register(String name, String surname, String mail, String password, String group)
	{
		User user = new User();
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
	public boolean check(String mail, String password)
	{
		User user = em.find(User.class, mail);
		return ((user != null) && (user.getPassword().equals(password)));
	}
}
