package site.recipes.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.recipes.models.Recipe;
import site.recipes.models.RecipeIngredient;
import site.recipes.repositories.RecipeIngredientRepository;
import site.recipes.repositories.RecipeRepository;

import java.util.Optional;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private RecipeRepository _recipeRepository;

    public RecipeController(RecipeRepository recipeRepository){
        _recipeRepository = recipeRepository;

    }

    @RequestMapping({"","/"})
    public String recipes(Model model) {
        model.addAttribute(new Recipe());
        model.addAttribute("recipes", _recipeRepository.findAll());
        return "recipes";
    }

    @RequestMapping("/{id}")
    public String recipe(@PathVariable long id, Model model) {
        Recipe r = _recipeRepository.findById(id);
        if (r != null){
            model.addAttribute("recipe", r);
            return "recipe";
        }
        else{
            return "redirect:/recipes";
        }
    }

    @GetMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute(new Recipe());
        return "recipesForm";
    }

    @PostMapping("/new")
    public String create( Recipe recipe) {
        _recipeRepository.save(recipe);
        return "redirect:/recipes/" +recipe.getId();
    }

    @GetMapping("/edit/{id}")
    public String newRecipe(@PathVariable long id, Model model, HttpServletRequest request) {
        Recipe r = _recipeRepository.findById(id);
        if (r != null){
            model.addAttribute("recipe", r);
            return "recipe";
        }
        else{
            return getPreviousPageByRequest(request).orElse("redirect:/recipes");
        }
    }

    @PostMapping("/edit/{id}")
    public String create(@PathVariable long id, Recipe recipe, HttpServletRequest request) {
        _recipeRepository.save(recipe);
        return "redirect:/recipes/{id}";
    }

    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request)
    {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }
}
