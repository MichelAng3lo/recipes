package site.recipes.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import site.recipes.models.DayOfMealsDTO;
import site.recipes.repositories.MealRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    private MealRepository _mealRepository;

    public IndexController(MealRepository mealRepository) {
        _mealRepository = mealRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<DayOfMealsDTO> days = new ArrayList<DayOfMealsDTO>();
        for (int i = 0; i < 7; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            days.add(new DayOfMealsDTO());
            days.get(i).setBreakfast(_mealRepository.findAllByDateAndType(date, "Śniadanie"));
            days.get(i).setDinner(_mealRepository.findAllByDateAndType(date, "Obiad"));
            days.get(i).setSupper(_mealRepository.findAllByDateAndType(date, "Kolacja"));
            days.get(i).setDate(date);
        }
        model.addAttribute("days", days);
        return "index";
    }
}


