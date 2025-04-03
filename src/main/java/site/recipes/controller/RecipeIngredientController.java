package site.recipes.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.recipes.models.Ingredient;
import site.recipes.models.Meal;
import site.recipes.models.Recipe;
import site.recipes.models.RecipeIngredient;
import site.recipes.repositories.IngredientRepository;
import site.recipes.repositories.RecipeIngredientRepository;
import site.recipes.repositories.RecipeRepository;

@Controller
@RequestMapping("/recipeingredient")
public class RecipeIngredientController {
    private RecipeIngredientRepository _recipeIngredientRepository;
    private RecipeRepository _recipeRepository;
    private IngredientRepository _ingredientRepository;

    public RecipeIngredientController(RecipeIngredientRepository recipeIngredientRepository, RecipeRepository recipeRepository, IngredientRepository ingredientRepository){
        _recipeIngredientRepository = recipeIngredientRepository;
        _recipeRepository = recipeRepository;
        _ingredientRepository = ingredientRepository;
    }

    @GetMapping("/add/{recipeId}")
    public String recipe(@PathVariable long recipeId, Model model) {
        Recipe r = _recipeRepository.findById(recipeId);

        if (r != null){
            model.addAttribute("ingredients", _ingredientRepository.findAll());
            model.addAttribute("recipe", r);
            model.addAttribute(new RecipeIngredient());
            return "recipeIngredients";
        }
        else{
            return "redirect:/recipes";
        }
    }
    // @RequestParam( name = "in") ,
    @PostMapping("/add/{recipeId}")
    public String create(@PathVariable long recipeId, RecipeIngredient recipeingredient) {
        Recipe r = _recipeRepository.findById(recipeId);
        Ingredient i = _ingredientRepository.findByName(recipeingredient.getIngredient().getName());
        if(i != null){
            recipeingredient.setIngredient(i);
        }
        recipeingredient.setRecipe(r);
        r.addRecipeIngredient(recipeingredient);


        //_recipeIngredientRepository.saveAll(recipe.getRecipeIngredientList());
        _recipeRepository.save(r);
        return "redirect:/recipeingredient/add/{recipeId}";
    }
}
