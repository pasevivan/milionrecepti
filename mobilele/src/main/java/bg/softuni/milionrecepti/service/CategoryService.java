package bg.softuni.milionrecepti.service;

import bg.softuni.milionrecepti.model.dto.category.CategoryDTO;
import bg.softuni.milionrecepti.model.entity.CategoryEntity;
import bg.softuni.milionrecepti.model.enums.CategoryEnum;
import bg.softuni.milionrecepti.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository brandRepository){
        this.categoryRepository = brandRepository;
    }

    public Set<CategoryDTO> getAllCategories(){
        return categoryRepository.
                findAll().
                stream().
                map(this::mapCategory).
                collect(Collectors.toSet());
    }

    private CategoryDTO mapCategory(CategoryEntity categoryEntity){
        return new CategoryDTO().
                setCategory(categoryEntity.getCategory());
    }

    public void populateCategories() {
        CategoryEnum[] categories = CategoryEnum.values();
        for (CategoryEnum category : categories) {
            CategoryEntity newCategory = new CategoryEntity();
            newCategory.setCategory(category);
            categoryRepository.save(newCategory);
        }
    }


}
