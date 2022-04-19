package com.foodrecipe.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipe.dao.IRecipeDetailsDao;
import com.foodrecipe.entity.RecipeDetails;

@Service
public class RecipeDetailsService implements IRecipeDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger( RecipeDetailsService.class );

	@Autowired
	private IRecipeDetailsDao recipeDetailsDao;

	@Override
	public List<RecipeDetails> getAllDetails() {
		return recipeDetailsDao.getAllDetails();
	}

	@Override
	public List<RecipeDetails> getRecipeDetailsByName(String item) {
		LOGGER.info("getRecipeDetailsByName() invoked");
		return recipeDetailsDao.getRecipeDetailsByName(item);
	}

	@Override
	public boolean addRecipeDetails(RecipeDetails details) {
		LOGGER.info("addRecipeDetails() invoked");
		List<RecipeDetails> list = recipeDetailsDao.getRecipeDetailsByName(details.getItem());
		Optional<RecipeDetails> optional =  list.stream().filter(recipe -> recipe.getName().equalsIgnoreCase(details.getName())).findAny();
		if(!optional.isPresent()) {
			recipeDetailsDao.addRecipeDetails(details);
			return true;
		} else {
			LOGGER.warn("Recipe is already exists fot his item, item: {}, name: {}", details.getItem(), details.getName());
			throw new RuntimeException("Recipe is already exists fot this item");
		}
		
		
	}

	@Override
	public void updateRecipeDetails(RecipeDetails details) {
		LOGGER.info("updateRecipeDetails() invoked");
		recipeDetailsDao.updateRecipeDetails(details);
	}

	@Override
	public void deleteRecipeDetails(int recipeId) {
		LOGGER.info("deleteRecipeDetails() invoked");
		recipeDetailsDao.deleteRecipeDetails(recipeId);
	}

}
