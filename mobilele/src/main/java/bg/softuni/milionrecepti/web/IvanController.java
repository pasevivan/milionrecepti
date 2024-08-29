package bg.softuni.milionrecepti.web;

import bg.softuni.milionrecepti.service.CategoryService;
import bg.softuni.milionrecepti.service.RecipeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ivan")
public class IvanController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public IvanController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }



    @GetMapping("/ivan")
    public String getAllRecipesPageable(
            Model model,
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 8) Pageable pageable) {

        model.addAttribute("recipes", recipeService.getAllRecipesPageable(pageable));

        return "ivan";
    }


}
