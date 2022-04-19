package com.foodrecipe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foodrecipe.entity.RecipeDetails;
import com.foodrecipe.entity.User;

@Transactional
@Repository
public class RecipeDetailsDao implements IRecipeDetailsDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<RecipeDetails> getRecipeDetailsByName(String item) {

		String hql = "FROM RecipeDetails as rd where rd.item ='" + item + "'";
		List<RecipeDetails> recipeList = entityManager.createQuery(hql).getResultList();
		return recipeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RecipeDetails> getAllDetails() {
		String hql = "FROM RecipeDetails as rd ORDER BY rd.receipeId";
		return (List<RecipeDetails>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void addRecipeDetails(RecipeDetails details) {
		entityManager.persist(details);
	}

	@Override
	public void updateRecipeDetails(RecipeDetails details) {
		List<RecipeDetails> existDetailsList = getRecipeDetailsByName(details.getName());
		if(existDetailsList.size()>0 ) {
			RecipeDetails existDetails = existDetailsList.get(0);
			existDetails.setName(details.getName());
			existDetails.setIngradiants(details.getIngradiants());
			existDetails.setSteps(details.getSteps());
			entityManager.flush();
			
		}
		
	}

	@Override
	public void deleteRecipeDetails(int recipeId) {
		entityManager.remove(entityManager.find(RecipeDetails.class, recipeId));
	}

}
