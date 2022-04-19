package com.foodrecipe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.foodrecipe.entity.RecipeDetails;
import com.foodrecipe.service.IRecipeDetailsService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RecipeController {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger( RecipeController.class );

	@Autowired
	private IRecipeDetailsService recipeDetailsService;

	@GetMapping("/recipedetails/{id}")
	public ResponseEntity<List<RecipeDetails>> getRecipeDetailsById(@PathVariable("id") String item) {
		LOGGER.info("starting of getRecipeDetailsById, item="+ item);
		List<RecipeDetails> recipeDetails = recipeDetailsService.getRecipeDetailsByName(item);
		LOGGER.info("Ending of getRecipeDetailsById, item="+ item);
		return new ResponseEntity<List<RecipeDetails>>(recipeDetails, HttpStatus.OK);
	}

	@GetMapping("/recipedetails")
	public ResponseEntity<List<RecipeDetails>> getAllRecipeDetails() {
		LOGGER.info("Starting of getAllRecipeDetails() ");
		List<RecipeDetails> list = recipeDetailsService.getAllDetails();
		LOGGER.info("Ending of getAllRecipeDetails() ");
		return new ResponseEntity<List<RecipeDetails>>(list, HttpStatus.OK);
	}

	@PostMapping("/addrecipedetails")
	public ResponseEntity<Void> addRecipeDetails(@RequestBody RecipeDetails recipeDetails, UriComponentsBuilder builder) {
		LOGGER.info("Starting of addRecipeDetails(), recipeName="+ recipeDetails.getName());
		List<RecipeDetails> recipeDetailsList = recipeDetailsService.getRecipeDetailsByName(recipeDetails.getName());
		boolean flag = recipeDetailsService.addRecipeDetails(recipeDetails);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/recipedetails/{id}").buildAndExpand(recipeDetails.getReceipeId()).toUri());
		LOGGER.info("Ending of addRecipeDetails(), recipeName="+ recipeDetails.getName());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/updaterecipedetails")
	public ResponseEntity<RecipeDetails> updateRecipeDetails(@RequestBody RecipeDetails recipeDetails) {
		LOGGER.info("Starting of updateRecipeDetails(), recipeName="+ recipeDetails.getName());
		recipeDetailsService.updateRecipeDetails(recipeDetails);
		LOGGER.info("Ending of updateRecipeDetails(), recipeName="+ recipeDetails.getName());
		return new ResponseEntity<RecipeDetails>(recipeDetails, HttpStatus.OK);
	}

	@DeleteMapping("/recipedetails/{id}")
	public ResponseEntity<Void> deleteRecipeDetails(@PathVariable("id") Integer id) {
		LOGGER.info("Starting of deleteRecipeDetails(), id="+ id);
		recipeDetailsService.deleteRecipeDetails(id);
		LOGGER.info("Ending of deleteRecipeDetails(), id="+ id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}