package bg.softuni.milionrecepti.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class ImageEntity extends BaseEntity{
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private RecipeEntity recipe;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public ImageEntity setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
        return this;
    }


}
