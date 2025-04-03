package site.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import site.recipes.models.Meal;
import site.recipes.models.Recipe;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends CrudRepository<Meal, Long> {
    List<Meal> findAllByDateBetween(LocalDate from, LocalDate to);
    List<Meal> findAllByDateAndType(LocalDate date, String type);
    Meal findById(long id);
}
