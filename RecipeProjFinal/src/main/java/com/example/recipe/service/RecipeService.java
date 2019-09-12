package com.example.recipe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipe.exception.RecordNotFoundException;
import com.example.recipe.repository.RecipeRepository;
import com.example.recipe.entity.RecipeEntity;

@Service
public class RecipeService {

	public static final String recordNotFound = "No recipe record exists for given id";
	@Autowired
	RecipeRepository repository;

	/**
	 * 
	 * @return list of all recipes
	 */
	public List<RecipeEntity> getAllRecipes() {
		List<RecipeEntity> RecipeList = (List<RecipeEntity>) repository.findAll();

		if (RecipeList.size() > 0) {
			return RecipeList;
		} else {
			return new ArrayList<RecipeEntity>();
		}
	}

	/**
	 * 
	 * @param id
	 * @return recipe of specific id
	 * @throws RecordNotFoundException
	 */
	public RecipeEntity getRecipeById(Long id) throws RecordNotFoundException {
		Optional<RecipeEntity> recipe = repository.findById(id);

		if (recipe.isPresent()) {
			return recipe.get();
		} else {
			throw new RecordNotFoundException(recordNotFound);
		}
	}

	/**
	 * 
	 * @param entity
	 * @return new created recipe entity for POST and updates recipe entity of
	 *         specified id for PUT
	 */
	public RecipeEntity createOrUpdateRecipe(RecipeEntity entity) {
		if (entity.getId() == null) {
			entity = repository.save(entity);

			return entity;
		} else {
			Optional<RecipeEntity> recipe = repository.findById(entity.getId());

			if (recipe.isPresent()) {
				RecipeEntity newEntity = recipe.get();
				newEntity.setDish(entity.getDish());
				newEntity.setIndicator(entity.getIndicator());
				newEntity.setServingCap(entity.getServingCap());
				newEntity.setIngredients(entity.getIngredients());
				newEntity.setInstructions(entity.getInstructions());

				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}

	/**
	 * Deletes recipe entity of specified id
	 * 
	 * @param id
	 * @throws RecordNotFoundException
	 */
	public void deleteRecipeById(Long id) throws RecordNotFoundException {
		Optional<RecipeEntity> recipe = repository.findById(id);

		if (recipe.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException(recordNotFound);
		}
	}
}