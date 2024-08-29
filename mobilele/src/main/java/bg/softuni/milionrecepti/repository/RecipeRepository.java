package bg.softuni.milionrecepti.repository;

import bg.softuni.milionrecepti.model.entity.CategoryEntity;
import bg.softuni.milionrecepti.model.entity.RecipeEntity;
import bg.softuni.milionrecepti.model.enums.SubcategoryEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long>, JpaSpecificationExecutor<RecipeEntity> {
    Set<RecipeEntity> findAllBySubcategory(SubcategoryEnum subcategory);

    Set<RecipeEntity> findAllByCategory(CategoryEntity category);

    Page<RecipeEntity> findByApprovedTrue(Pageable pageable);


}
