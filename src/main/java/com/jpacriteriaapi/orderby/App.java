package com.jpacriteriaapi.orderby;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class App {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student_details");
		EntityManager em = emf.createEntityManager();
		
		   em.getTransaction().begin( );  
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Student> cq = cb.createQuery(Student.class);
		
		Root<Student> stud = cq.from(Student.class);
		
		cq.orderBy(cb.asc(stud.get("age")));
		
		
		// we can also use WHERE Clause 
//		cq.where(cb.greaterThan(stud.get("s_age"), 22));  
		
		// we can also use BETWEEN Clause 
//		cq.where(cb.between(stud.get("s_age"), 22, 26)) ;
		
		// we can also use LIKE Clause
//		 cq.where(cb.like(stud.get("s_name"), "R%")); 
		
		
		// we can also use IN Clause
//		cq.where(cb.in(stud.get("s_age")).value(22).value(24));  
		
		
		CriteriaQuery<Student> select = cq.select(stud);
		
		TypedQuery<Student> q = em.createQuery(select);
		List<Student> list = q.getResultList();
		
		System.out.print("Rollno");
		System.out.print("\t Name");
		System.out.println("\t Age");
		
		for(Student s:list) {
			
			System.out.print(s.getRollno());
			System.out.print("\t"+s.getName());
			System.out.println("\t"+s.getAge());
		}
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
