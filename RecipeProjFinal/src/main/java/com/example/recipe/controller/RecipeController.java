package com.example.recipe.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.recipe.entity.RecipeEntity;
import com.example.recipe.exception.RecordNotFoundException;
import com.example.recipe.service.RecipeService;

@Controller
@RequestMapping("/")
public class RecipeController {
	@Autowired
	RecipeService service;

	/**
	 * 
	 * @param model
	 * @returns the ListOfRecipes web page on load which has the list of recipes present
	 */
	@RequestMapping
	public String getAllRecipes(Model model) {
		List<RecipeEntity> list = service.getAllRecipes();

		model.addAttribute("recipes", list);
		return "ListOfRecipes";
	}
	
	/**
	 * 
	 * @param model
	 * @param id
	 * @returns CreateAndUpdateRecipe page to POST a new recipe or PUT/update a recipe of specified id
	 * @throws RecordNotFoundException
	 */
	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editRecipeById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			RecipeEntity entity = service.getRecipeById(id.get());
			model.addAttribute("recipe", entity);
		} else {
			model.addAttribute("recipe", new RecipeEntity());
		}
		return "CreateAndUpdateRecipe";
	}

	/**
	 * 
	 * @param model
	 * @param id
	 * @returns the updated ListOfRecipes after deleting recipe of specified id
	 * @throws RecordNotFoundException
	 */
	@RequestMapping(path = "/delete/{id}")
	public String deleteRecipeById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteRecipeById(id);
		return "redirect:/";
	}

	/**
	 * 
	 * @param recipe
	 * @returns ListOfRecipes page to show the updated recipe
	 * @throws RecordNotFoundException
	 */
	@RequestMapping(path = "/createRecipe", method = RequestMethod.POST)
	public String createOrUpdateRecipe(RecipeEntity recipe) throws RecordNotFoundException {
		service.createOrUpdateRecipe(recipe);
		return "redirect:/";
	}
}