package site.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import site.recipes.models.Recipe;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Recipe findByName(String name);
    Recipe findById(long id);
}
