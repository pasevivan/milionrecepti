package bg.softuni.milionrecepti.model.dto.recipe;


import bg.softuni.milionrecepti.model.enums.CategoryEnum;
import bg.softuni.milionrecepti.model.enums.RecipeSpeedEnum;
import bg.softuni.milionrecepti.model.enums.SubcategoryEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Set;


public class CreateRecipeDTO {

    @NotBlank
    private String name;
    @NotNull
    private CategoryEnum category;
    @NotNull
    private SubcategoryEnum subcategory;
    @NotNull
    private boolean vegetarian;
//    @NotBlank
//    private String firstImage;
//    private String secondImage;
//    private String thirdImage;
    @NotNull
    @Min(1)
    private int portions;
    @NotBlank
    private String ingredients;
    @NotBlank
    private String making;
    @NotNull
    @Positive
    private int hours;
    @NotNull
    private int minutes;

    private Set<MultipartFile> images;

    //@JsonProperty(defaultValue = "НЕКАТЕГОРИЗИРАНА") // Default value for deserialization
    private RecipeSpeedEnum recipeSpeed;

//    public CreateRecipeDTO() {
//        this.recipeSpeed = RecipeSpeedEnum.НЕКАТЕГОРИЗИРАНА; // Default value
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public SubcategoryEnum getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubcategoryEnum subcategory) {
        this.subcategory = subcategory;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

//    public String getFirstImage() {
//        return firstImage;
//    }
//
//    public void setFirstImage(String firstImage) {
//        this.firstImage = firstImage;
//    }
//
//    public String getSecondImage() {
//        return secondImage;
//    }
//
//    public void setSecondImage(String secondImage) {
//        this.secondImage = secondImage;
//    }
//
//    public String getThirdImage() {
//        return thirdImage;
//    }
//
//    public void setThirdImage(String thirdImage) {
//        this.thirdImage = thirdImage;
//    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMaking() {
        return making;
    }

    public void setMaking(String making) {
        this.making = making;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Set<MultipartFile> getImages() {
        return images;
    }

    public void setImages(Set<MultipartFile> images) {
        this.images = images;
    }

    public RecipeSpeedEnum getRecipeSpeed() {
        return recipeSpeed != null ? recipeSpeed : RecipeSpeedEnum.НЕКАТЕГОРИЗИРАНА;
        //return recipeSpeed;
    }

    public void setRecipeSpeed(RecipeSpeedEnum recipeSpeed) {
        this.recipeSpeed = recipeSpeed;
    }
}