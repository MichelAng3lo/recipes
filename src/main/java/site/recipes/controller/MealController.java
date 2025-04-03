package site.recipes.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.recipes.models.Meal;
import site.recipes.models.Recipe;
import site.recipes.repositories.MealRepository;
import site.recipes.repositories.RecipeRepository;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/meals")
public class MealController {
    private MealRepository _mealRepository;
    private RecipeRepository _recipeRepository;
    public MealController(RecipeRepository recipeRepository, MealRepository mealRepository){
        _mealRepository = mealRepository;
        _recipeRepository = recipeRepository;
    }

    @RequestMapping({"","/"})
    public String recipes(Model model) {
        model.addAttribute(new Meal());
        model.addAttribute("meals", _mealRepository.findAllByDateBetween(LocalDate.now(), LocalDate.now().plusDays(7)));
        return "meals";
    }

    @RequestMapping("/{id}")
    public String recipe(@PathVariable long id, Model model) {
        Meal m = _mealRepository.findById(id);
        if (m != null){
            model.addAttribute("meal", m);
            return "meal";
        }
        else{
            return "redirect:/recipes";
        }
    }

    @GetMapping("/new")
    public String newRecipe(@RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> date, Model model) {
        model.addAttribute("data", date.orElseGet(LocalDate::now));
        model.addAttribute(new Recipe());
        model.addAttribute("recipes", _recipeRepository.findAll());
        model.addAttribute(new Meal());
        return "mealsForm";
    }

    @PostMapping("/new")
    public String create(@RequestParam( name = "times") long num, Meal meal) {
        for (int i = 0; i < num; i++) {
            _mealRepository.save(new Meal(meal, i));
        }
        return "redirect:/meals";
    }
}
