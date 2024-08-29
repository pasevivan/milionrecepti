package bg.softuni.milionrecepti.model.entity;

import bg.softuni.milionrecepti.model.enums.CategoryEnum;
import bg.softuni.milionrecepti.model.enums.SubcategoryEnum;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.sql.Array;
import java.util.*;

@Entity
@Table(name = "recipe")
public class RecipeEntity extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private boolean approved;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String ingredients;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubcategoryEnum subcategory;

    @Column(nullable = false)
    @Positive
    private int portions;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ImageEntity> images = new HashSet<>();

    @Column(nullable = false, columnDefinition = "TEXT")
    private String making;

    @Column(nullable = false)
    @Positive
    private int hours;
    @Column(nullable = false)
    private int minutes;
    @Column(name = "created_on", nullable = false)
    private Date createdOn;

    public String getName() {
        return name;
    }

    public RecipeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public SubcategoryEnum getSubcategory() {
        return subcategory;
    }

    public RecipeEntity setSubcategory(SubcategoryEnum subcategory) {
        this.subcategory = subcategory;
        return this;
    }

    public int getPortions() {
        return portions;
    }

    public RecipeEntity setPortions(int portions) {
        this.portions = portions;
        return this;
    }

    public String getMaking() {
        return making;
    }

    public RecipeEntity setMaking(String steps) {
        this.making = steps;
        return this;
    }

    public int getHours() {
        return hours;
    }

    public RecipeEntity setHours(int hours) {
        this.hours = hours;
        return this;
    }

    public int getMinutes() {
        return minutes;
    }

    public RecipeEntity setMinutes(int minutes) {
        this.minutes = minutes;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public RecipeEntity setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public boolean getApproved() {
        return approved;
    }

    public RecipeEntity setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public RecipeEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public RecipeEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public Set<ImageEntity> getImages() {
        return images;
    }

    public RecipeEntity setImages(Set<ImageEntity> images) {
        this.images = images;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeEntity setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

}
