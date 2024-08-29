package bg.softuni.milionrecepti.repository;

import bg.softuni.milionrecepti.model.entity.ImageEntity;
import bg.softuni.milionrecepti.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findAllByRecipe(RecipeEntity recipe);
}
