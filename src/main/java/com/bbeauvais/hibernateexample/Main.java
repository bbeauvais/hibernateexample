package com.bbeauvais.hibernateexample;

import java.math.BigDecimal;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.bbeauvais.hibernateexample.entity.Preparation;
import com.bbeauvais.hibernateexample.entity.PreparationLigne;

/**
 * Hello world!
 *
 */
public class Main {

	public static void main(String[] args) {
		JPAUtils utils = new JPAUtils(PreparationLigne.class, Preparation.class);

		EntityManager entityManager = utils.getEntityManager();

		
		Preparation preparation = new Preparation();
		preparation.setDescription("Hola que tal");
		addPreparationLigne(preparation, new PreparationLigne());
		addPreparationLigne(preparation, new PreparationLigne());
		addPreparationLigne(preparation, new PreparationLigne());
		
		doInTransaction(entityManager, em -> em.persist(preparation));
		
		Preparation persistedPreparation = entityManager.createQuery("FROM Preparation", Preparation.class).getSingleResult();
		doInTransaction(entityManager, em -> persistedPreparation.getPreparationLignes().get(1).setQuantite(BigDecimal.TEN));
		
		entityManager.createQuery("FROM PreparationLigne", PreparationLigne.class).getResultList().forEach(System.out::println);
		entityManager.close();
	}

	public static void doInTransaction(EntityManager entityManager, Consumer<EntityManager> consumer) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		consumer.accept(entityManager);
		transaction.commit();
	}
	
	public static void addPreparationLigne(Preparation preparation, PreparationLigne preparationLigne) {
		preparation.getPreparationLignes().add(preparationLigne);
		preparationLigne.setPreparation(preparation);
	}
}
