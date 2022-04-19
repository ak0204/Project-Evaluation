package com.foodrecipe.dao;

import java.util.List;

import com.foodrecipe.entity.RecipeDetails;

public interface IRecipeDetailsDao {

	List<RecipeDetails> getAllDetails();

	List<RecipeDetails> getRecipeDetailsByName(String item);

	void addRecipeDetails(RecipeDetails details);

	void updateRecipeDetails(RecipeDetails details);

	void deleteRecipeDetails(int recipeId);

}
