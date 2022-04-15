package customer;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import customer.data.Customer;

/**
 * Stateless Session Bean CustomerManagement (@Stateless).
 * Implementation of the Customer Management component of the C/D-Model (Exercise 2).
 * Implemented as a stateless connector for the Entity Beans which holding the Customer data.
 * 
 * By problems with the project configuration look at JavaDoc of the entityManager attribute below.
 */
@Stateless
@LocalBean
public class CustomerManagement implements CustomerManagementRemote, CustomerManagementLocal {

	/**
	 * Connector to the database.
	 * unitName states which database configuration is used
	 * Annotation @PersistenceContext automatically connects the entityManager attribute to the Database.
	 * 
	 * 
	 * Tips for project JPA configuration (if needed):
	 * The database configuration can be found in META-INF/persistence.xml
	 * Please enable JPA with Right-Click on project -> Properties -> Project Facets -> check JPA
	 *
	 * For a simple example on eclipse project configuration deployment of a example JPA entity manager see:
	 * https://www.cs.hs-rm.de/~knauf/JavaEE6/kuchen/index.html
	 */
	@PersistenceContext(unitName = "JPAUnit")
	private EntityManager entityManager;  
	
	
    public CustomerManagement() {}
    
    
    /**
     * finds the customer in the database using the database connector entityManager 
     */
	@Override
	public Customer findCustomer(long customerId) {
		Customer p = entityManager.find(Customer.class, customerId);
		return p;
	}

	/**
     * finds the customer in the database using the database connector entityManager
     * if a customer with the foraign key (id) allready exists it updates the data.
     * if no customer is found it creates a new database entry
     */
	@Override
	public void addOrUpdate(Customer customer) {
		if(entityManager.find(Customer.class,customer.getId())!= null) {
			entityManager.merge(customer);
		}else {
			entityManager.persist(customer);
		}
	}

	@Override
	public void removeCustomer(long customerId) {
		entityManager.remove(findCustomer(customerId));
	}

	/**
     * finds all customer in the database using the database connector entityManager
     * uses a simple SQL query from javax.persistence
     */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Customer> listCustomers() {
		String q = "SELECT c from " + Customer.class.getName() + " c";
		Query query = entityManager.createQuery(q);
		List<Customer> customers = query.getResultList();
		return customers;
	}
    
    

}
