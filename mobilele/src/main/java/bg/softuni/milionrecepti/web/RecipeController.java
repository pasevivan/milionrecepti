package bg.softuni.milionrecepti.web;

import bg.softuni.milionrecepti.model.dto.recipe.CreateRecipeDTO;
import bg.softuni.milionrecepti.model.entity.ImageEntity;
import bg.softuni.milionrecepti.model.entity.RecipeEntity;
import bg.softuni.milionrecepti.repository.ImageRepository;
import bg.softuni.milionrecepti.repository.RecipeRepository;
import bg.softuni.milionrecepti.service.CategoryService;
import bg.softuni.milionrecepti.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.validation.Valid;


@Controller
@RequestMapping("recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private ImageRepository imageRepository;

    private  final String uploadDir = "src/main/resources/static/images";

    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    public RecipeController(RecipeService recipeService, CategoryService categoryService) throws IOException {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }



        @GetMapping("/ivan")
    public String getAllRecipesPageable(
            Model model,
            @PageableDefault(
                    sort = "createdOn",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 8) Pageable pageable) {
        model.addAttribute("recipes", recipeService.getAllRecipesPageable(pageable));
        return "recipes_dashboard";
    }

//    @GetMapping("/recipes_dashboard")
//    public String getAllRecipes(Model model) {
//        model.addAttribute("recipes", recipeService.getAllRecipes(pag));
//        return "recipes_dashboard";
//    }

    @GetMapping("/recipes_dashboard/category/{category}")
    public String getAllRecipesByCategory(@PathVariable("category") String category,
                                          Model model) {
        model.addAttribute("recipes", recipeService.getRecipesByCategory(category));
        return "recipes_dashboard";
    }

    @GetMapping("/recipes_dashboard/subcategory/{subcategory}")
    public String getAllRecipesBySubcategory(@PathVariable("subcategory") String subcategory,
                                             Model model) {
        model.addAttribute("recipes", recipeService.getRecipesBySubcategory(subcategory));
        return "recipes_dashboard";
    }

    @GetMapping("/recipe_add")
    public String addRecipe(Model model) {
        if (!model.containsAttribute("addRecipeModel")) {
            model.addAttribute("addRecipeModel", new CreateRecipeDTO());
        }
        model.addAttribute("category", categoryService.getAllCategories());
        return "recipe_add";
    }

//    @PostMapping("/recipe_add")
//    public String addRecipe(@Valid CreateRecipeDTO addRecipeDTO,
//                            BindingResult bindingResult,
//                            RedirectAttributes redirectAttributes,
//                            @AuthenticationPrincipal UserDetails userDetails) throws IOException {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("addRecipeModel", addRecipeDTO);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addRecipeModel", bindingResult);
//
//            return "redirect:/recipes/recipe_add";
//        }
//        recipeService.addRecipe(addRecipeDTO, userDetails);
//
//        return "redirect:/";
//    }

    @PostMapping("/recipe_add")
    public String addRecipe(@ModelAttribute("addRecipeModel") CreateRecipeDTO addRecipeDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addRecipeModel", addRecipeDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addRecipeModel", bindingResult);
            return "redirect:/recipes/recipe_add";
        }

         //Cast Principal to UserDetails
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();

        try {
            recipeService.addRecipe(addRecipeDTO, userDetails);
        } catch (IOException e) {
            logger.error("Error uploading image", e);
            redirectAttributes.addFlashAttribute("errorMessage", "There was an error uploading the images. Please try again.");
            return "redirect:/recipes/recipe_add";
        }

        return "redirect:/";
    }

//    @PostMapping("/recipe_add")
//    public String addRecipe(@ModelAttribute("recipe") CreateRecipeDTO addRecipeDTO) {
//        RecipeEntity recipe = new RecipeEntity();
//        recipe.setName(addRecipeDTO.getName());
//
//        recipeRepository.save(recipe);  // Save to generate ID for the RecipeEntity
//
//        if (addRecipeDTO.getImages() != null && !addRecipeDTO.getImages().isEmpty()) {
//            for (MultipartFile imageFile : addRecipeDTO.getImages()) {
//                try {
//                    String imageFileName = saveImage(imageFile);
//                    ImageEntity imageEntity = new ImageEntity();
//                    imageEntity.setImageUrl(imageFileName);
//                    imageEntity.setRecipe(recipe);
//
//                    imageRepository.save(imageEntity);
//                    recipe.getImages().add(imageEntity);
//                } catch (IOException e) {
//                    e.printStackTrace();  // Handle the exception properly in production
//                }
//            }
//        }
//
//        recipeRepository.save(recipe);  // Save recipe with associated images
//
//        return "redirect:/";
//    }
//
//    private String saveImage(MultipartFile imageFile) throws IOException {
//        Path uploadPath = Paths.get(uploadDir);
//
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        String imageFileName = imageFile.getOriginalFilename();
//        assert imageFileName != null;
//        Path filePath = uploadPath.resolve(imageFileName);
//        Files.write(filePath, imageFile.getBytes());
//
//        return imageFileName;
//    }

   // @GetMapping("/evict")
    //public ResponseEntity<RecipeDTO> evict() {
        //recipeService.refresh();
        //return ResponseEntity.noContent().build();
    //}

//    @GetMapping("/recipe_details/{id}")
//    public String getRecipeDetail(@PathVariable("id") Long id,
//                                  Model model) {
//
//        var recipeDto =
//                recipeService.findRecipeById(id).
//                        orElseThrow(() -> new ObjectNotFoundException("Recipe with ID " +
//                                id + " not found!"));
//
//        //if (recipeDto.isVegetarian()) recipeDto.setVegetarianString("Да");
//        //else recipeDto.setVegetarianString("Не");
//
//        // recipeDto.setSplitSteps(Arrays.stream(recipeDto.getSteps().split("\\r?\\n")).collect(Collectors.toSet()));
//
//        model.addAttribute("recipe", recipeDto);
//
//        return "recipe_details";
//    }

//    @GetMapping("/recipes_dashboard/{id}")
//    public String getRecipeDetail(@PathVariable("id") Long id,
//                                  Model model) {
//
//        var recipeDto =
//                recipeService.findRecipeById(id).
//                        orElseThrow(() -> new ObjectNotFoundException("Recipe with ID " +
//                                id + " not found!"));
//
//        //if (recipeDto.isVegetarian()) recipeDto.setVegetarianString("Да");
//        //else recipeDto.setVegetarianString("Не");
//
//       // recipeDto.setSplitSteps(Arrays.stream(recipeDto.getSteps().split("\\r?\\n")).collect(Collectors.toSet()));
//
//        model.addAttribute("recipe", recipeDto);
//
//        return "recipe_details";
//    }

}
