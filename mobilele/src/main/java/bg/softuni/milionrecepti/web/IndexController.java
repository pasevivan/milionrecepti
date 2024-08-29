package bg.softuni.milionrecepti.web;

import bg.softuni.milionrecepti.exception.ObjectNotFoundException;
import bg.softuni.milionrecepti.model.entity.RecipeEntity;
import bg.softuni.milionrecepti.service.CategoryService;
import bg.softuni.milionrecepti.service.RecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public IndexController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }



    @GetMapping("/all")
    public String getAllRecipesPageable(
            Model model,
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 7) Pageable pageable) {

        model.addAttribute("recipes", recipeService.getApprovedRecipes(pageable));

        return "index";
    }

//    @GetMapping("/all")
//    public String getAllRecipesPageable(
//            Model model,
//            @PageableDefault(
//                    sort = "id",
//                    direction = Sort.Direction.ASC,
//                    page = 0,
//                    size = 7) Pageable pageable) {
//
//        model.addAttribute("recipes", recipeService.getAllRecipesActive(pageable));
//
//        return "index";
//    }



//    @GetMapping("/")
//    public String allRecipes(
//            Model model,
//            @PageableDefault(
//                    sort = "id",
//                    direction = Sort.Direction.ASC,
//                    page = 0,
//                    size = 5) Pageable pageable) {
//
//        model.addAttribute("recipes", recipeService.getAllRecipes(pageable));
//
//        return "index";
//    }

    @GetMapping("/")
    public String allRecipes(
            Model model,
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 5) Pageable pageable) {

        model.addAttribute("recipes", recipeService.getApprovedRecipes(pageable));

        return "index";
    }

//    @GetMapping("/")
//    public Page<RecipeEntity> allRecipes(Pageable pageable){
//
//
//        return recipeService.getApprovedRecipes1(pageable);
//        return "index";
//    }

//    @GetMapping("/")
//    public String allRecipes(
//            Model model,
//            @PageableDefault(
//                    sort = "id",
//                    direction = Sort.Direction.ASC,
//                    page = 0,
//                    size = 5) Pageable pageable) {
//
//        model.addAttribute("recipes", recipeService.recipeEntityToRecipeDTOisActive(pageable));
//
//        return "index";
//    }


//    @GetMapping("/")
//    public String allRecipes(
//            Model model,
//            @PageableDefault(
//                    sort = "id",
//                    direction = Sort.Direction.ASC,
//                    page = 0,
//                    size = 5) Pageable pageable) {
//
//        model.addAttribute("recipes", recipeService.getAllRecipesActive(pageable));
//
//        return "index";
//    }

    @GetMapping("/recipe_details/{id}")
    public String getRecipeDetail(@PathVariable("id") Long id,
                                  Model model) {

        var recipeDto =
                recipeService.findRecipeById(id).
                        orElseThrow(() -> new ObjectNotFoundException("Recipe with ID " +
                                id + " not found!"));

        //if (recipeDto.isVegetarian()) recipeDto.setVegetarianString("Да");
        //else recipeDto.setVegetarianString("Не");

        // recipeDto.setSplitSteps(Arrays.stream(recipeDto.getSteps().split("\\r?\\n")).collect(Collectors.toSet()));

        model.addAttribute("recipe", recipeDto);

        return "recipe_details";
    }


}
