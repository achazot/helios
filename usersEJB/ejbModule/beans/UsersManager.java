package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class UsersManager {
	
	@PersistenceContext(unitName="heliosPU")
	EntityManager um; 

}
