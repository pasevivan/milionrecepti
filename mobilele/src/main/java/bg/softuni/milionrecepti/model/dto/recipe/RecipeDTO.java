package bg.softuni.milionrecepti.model.dto.recipe;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public class RecipeDTO {
    private Long id;
    private String name;
    private String category;
    private String subcategory;
    private Set<String> imageUrls;
    private int portions;
    private String ingredients;
    private String making;
    private int hours;
    private int minutes;
    private String authorFirstName;
    private String authorLastName;

    private String author;

    private boolean approved;




    public RecipeDTO() {
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public Set<String> getImages() {
        return imageUrls;
    }

    public void setImages(Set<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

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

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthor() {
        return getAuthorFirstName() + " " + getAuthorLastName();
    }
}
