package tools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.OrderItem;

public class OrderItemService {
	private static EntityManager em = Persistence.createEntityManagerFactory(
			"persistence").createEntityManager();

	
	public static void save(OrderItem orderItem) {
		em.getTransaction().begin();
		em.persist(orderItem);
		em.getTransaction().commit();
	}

	public static void delete(OrderItem orderItem) {
		em.getTransaction().begin();
		em.remove(orderItem);
		em.getTransaction().commit();
	}
	
	public static OrderItem get(int id) {
		return em.find(OrderItem.class, id);		
	}

	public static void update(OrderItem orderItem) {
		em.getTransaction().begin();
		em.merge(orderItem);
		em.getTransaction().commit();
	}

	 public static List<OrderItem> getAll() {
		  
		 Query query =  em.createQuery("select c from OrderItem c ");
		 return (List<OrderItem>)query.getResultList();
	 }
}
