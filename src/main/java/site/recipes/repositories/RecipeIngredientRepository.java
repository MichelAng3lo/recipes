package site.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import site.recipes.models.Recipe;
import site.recipes.models.RecipeIngredient;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {
}
