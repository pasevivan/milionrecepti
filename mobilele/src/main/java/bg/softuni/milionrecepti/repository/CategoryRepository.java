package bg.softuni.milionrecepti.repository;

import bg.softuni.milionrecepti.model.entity.CategoryEntity;
import bg.softuni.milionrecepti.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByCategory(CategoryEnum category);
}
