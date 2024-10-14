package com.jpa_hibernate;

import com.jpa_hibernate.dto.CountedEnrollmentStudent;
import com.jpa_hibernate.dto.EnrolledStudent;
import com.jpa_hibernate.entities.*;
import com.jpa_hibernate.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        String puName = "pu-name";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "none");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager(); // represents the context

        try {
            em.getTransaction().begin();

//=====================Criteria Query==================================
            CriteriaBuilder builder = em.getCriteriaBuilder();

            CriteriaQuery<Object[]> cQ = builder.createQuery(Object[].class);

            Root<StudentQueries> root = cQ.from(StudentQueries.class);

//            cQ.select(root);
            cQ.multiselect(root.get("name"), root.get("id"));
            cQ.where(builder.ge(root.get("id"), 2));
            cQ.orderBy(builder.desc(root.get("id")));

            TypedQuery<Object[]> query = em.createQuery(cQ);

            query.getResultList().forEach(result -> {
                System.out.println("Name: " + result[0] + ", ID: " + result[1]);
            });

            //======================Join===============

//=====================Query Named==================================
//            TypedQuery<StudentQueries> q = em.createNamedQuery("getAll", StudentQueries.class);
//
//            q.getResultList().forEach(o -> System.out.println(o.toString()));

//=====================Group By, Order By, Having==================================
//            String jpql = "SELECT com.jpa_hinernate.dto.CountedEnrollmentStudent(s.name, count(s)) " +
//                    "FROM Student s " +
//                    "GROUP BY s.name " +
//                    "HAVING s.name LIKE '%e' " +
//                    "ORDER BY s.name DESC";
//
//            TypedQuery<CountedEnrollmentStudent> q = em.createQuery(jpql, CountedEnrollmentStudent.class);
//
//            q.getResultList().forEach(o -> System.out.println(o.s() + "" + o.count()));
//=====================INNER JOIN==================================
//            String jpql = "select s, e from StudentQueries s inner join s.enrollments e";
//
//            TypedQuery<Object[]> q = em.createQuery(jpql, Object[].class);
//
//            q.getResultList().forEach(o -> System.out.println(o[0] + "" + o[1]));
//
//            ==================================
//
//            String jpql = "select s, e from StudentQueries s, Enrollment e where s.id = e.student.id";
//
//            TypedQuery<Object[]> q = em.createQuery(jpql, Object[].class);
//
//            q.getResultList().forEach(o -> System.out.println(o[0] + "" + o[1]));

//==========================DTO======================================
//            String jpql =
//                    "select new com.jpa_hibernate.dto.EnrolledStudent(s, e) " +
//                            "from StudentQueries s inner join s.enrollments e";
//
//            TypedQuery<EnrolledStudent> q = em.createQuery(jpql, EnrolledStudent.class);
//
//            q.getResultList().forEach(o -> System.out.println(o.student() + "" + o.enrollment()));

//            ======================================
//
//            String jpql =
//                    "SELECT NEW com.jpa_hibernate.dto.CountedEnrollmentStudent(s, (SELECT COUNT(e) FROM Enrollment e WHERE e.student = s) )FROM StudentQueries s";
//
//            TypedQuery<CountedEnrollmentStudent> q = em.createQuery(jpql, CountedEnrollmentStudent.class);
//
//            q.getResultList().forEach(o -> System.out.println(o.s() + "" + o.count()));

//=====================Create queries==================================
//            String jpql = "SELECT p FROM ProductQueries  p";
//            String jpql = "SELECT p FROM ProductQueries  p WHERE p.price > 5";
//            String jpql = "SELECT p FROM ProductQueries  p WHERE p.price > :price AND p.name LIKE :name";
//            String jpql = "SELECT AVG(p.price) FROM ProductQueries  p";(AVG, SUM, MIN, MAX ...)
//
//            TypedQuery<ProductQueries> q = em.createQuery(jpql, ProductQueries.class);
//
//            q.setParameter("price", 5);
//            q.setParameter("name", "%a%");
//
//            List<ProductQueries> products = q.getResultList();
//
//            for (ProductQueries pq : products) {
//                System.out.println(pq);
//            }

//=================operações para o contexto============================
//            em.persist();
//            em.find();
//            em.remove();
//            em.merge();
//            em.refresh();
//            em.detach();
//            em.getReference();

            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
            emf.close();
        }
    }
}