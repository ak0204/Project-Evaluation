package com.foodrecipe.service;

import java.util.List;

import com.foodrecipe.entity.RecipeDetails;

public interface IRecipeDetailsService {

	List<RecipeDetails> getAllDetails();

	List<RecipeDetails> getRecipeDetailsByName(String item);

	boolean addRecipeDetails(RecipeDetails details);

	void updateRecipeDetails(RecipeDetails details);

	void deleteRecipeDetails(int recipeId);
}
