package com.example.recipe.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.recipe.entity.RecipeEntity;
import com.example.recipe.exception.RecordNotFoundException;
import com.example.recipe.repository.RecipeRepository;
import com.example.recipe.service.RecipeService;

//Unit Tests for Service Class methods
@RunWith(SpringJUnit4ClassRunner.class)
public class RecipeServiceMockTest {

	@Mock
	private RecipeRepository recipeRepo;
	
	@InjectMocks
	private RecipeService recipeServ;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	//JUnit for displaying all recipes
	@Test
	public void testgetAllRecipes(){
		List<RecipeEntity> RecipeList = new ArrayList<RecipeEntity>();
		RecipeList.add(new RecipeEntity(1L,"coffee","veg",2L,"coffee,milk,sugar","Add sugar and coffee to milk and stir",null,null));
		RecipeList.add(new RecipeEntity(2L,"lemonade","veg",3L,"lemon,water,sugar","Add sugar and lemon extract to water and stir",null,null));
		when(recipeRepo.findAll()).thenReturn(RecipeList);
		
		List<RecipeEntity> result = recipeServ.getAllRecipes();
		assertEquals(2, result.size());
	}
	
	//JUnit for GET method
	@Test 
	public void testgetRecipeById() throws RecordNotFoundException
	    {
	        when(recipeRepo.findById(1L)).thenReturn(Optional.of(new RecipeEntity(1L,"coffee","veg",2L,"coffee,milk,sugar","Add sugar and coffee to milk and stir",null,null)));
	         
	        RecipeEntity rec = recipeServ.getRecipeById(1L);
	         
	        assertEquals("coffee", rec.getDish());
	        assertEquals("veg", rec.getIndicator());
	        assertEquals(2L, rec.getServingCap());
	        assertEquals("coffee,milk,sugar", rec.getIngredients());
	        assertEquals("Add sugar and coffee to milk and stir", rec.getInstructions());
	        assertEquals(null, rec.getCreatedAt());
	        assertEquals(null, rec.getUpdatedAt());
	    }
	
	//JUnit for POST and PUT method
	@Test
	public void testcreateOrUpdateRecipeWithoutId() throws RecordNotFoundException {
		RecipeEntity recipeEntity = new RecipeEntity(null,"coffee","veg",2L,"coffee,milk,sugar","Add sugar and coffee to milk and stir",null,null);
		if(recipeEntity.getId()== null) {
			recipeRepo.save(recipeEntity);
			verify(recipeRepo,times(1)).save(recipeEntity);
		}
	}
	
	@Test
	public void testcreateOrUpdateRecipeWithId() throws RecordNotFoundException 
	{
			RecipeEntity newEntity = new RecipeEntity(1L,"coffee","veg",2L,"coffee,milk,sugar","Add sugar and coffee to milk and stir",null,null);
			when(recipeRepo.findById(1L)).thenReturn(Optional.of(newEntity));
			RecipeEntity entity = new RecipeEntity(1L,"coffee updated","veg",2L,"coffee,milk,sugar","Add sugar and coffee to milk and stir",null,null);
			when(recipeRepo.save(newEntity)).thenReturn(entity);
						
			assertEquals(entity.getDish(), recipeServ.createOrUpdateRecipe(entity).getDish());
	}
	
	@Test
	public void testcreateOrUpdateRecipe() throws RecordNotFoundException 
	{
			when(recipeRepo.findById(1L)).thenReturn(Optional.empty());
			RecipeEntity recSave = new RecipeEntity(1L,"coffee updated","veg",2L,"coffee,milk,sugar","Add sugar and coffee to milk and stir",null,null);
			when(recipeRepo.save(recSave)).thenReturn(recSave);
			
			assertEquals(recSave.getDish(), recipeServ.createOrUpdateRecipe(recSave).getDish());
		}
	
	//JUnit for DELETE method
	@Test
	public void testdeleteRecipeById(){
		RecipeEntity recipeEntity = new RecipeEntity(1L,"coffee","veg",2L,"coffee,milk,sugar","Add sugar and coffee to milk and stir",null,null);
		recipeRepo.deleteById(1L);
		verify(recipeRepo, times(1)).deleteById(recipeEntity.getId());
	} 
}
