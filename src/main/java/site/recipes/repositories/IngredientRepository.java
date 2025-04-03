package site.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import site.recipes.models.Ingredient;
import site.recipes.models.Recipe;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Ingredient findByName(String name);
}
