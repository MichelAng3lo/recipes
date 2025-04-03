package site.recipes.models;

import java.time.LocalDate;
import java.util.List;

public class DayOfMealsDTO {
    private List<Meal> breakfast;
    private List<Meal> dinner;
    private List<Meal> supper;
    private LocalDate date;

    public List<Meal> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<Meal> breakfast) {
        this.breakfast = breakfast;
    }

    public List<Meal> getDinner() {
        return dinner;
    }

    public void setDinner(List<Meal> dinner) {
        this.dinner = dinner;
    }

    public List<Meal> getSupper() {
        return supper;
    }

    public void setSupper(List<Meal> supper) {
        this.supper = supper;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
