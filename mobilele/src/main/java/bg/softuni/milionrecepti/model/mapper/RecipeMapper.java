package bg.softuni.milionrecepti.model.mapper;

import bg.softuni.milionrecepti.model.dto.recipe.CreateRecipeDTO;
import bg.softuni.milionrecepti.model.dto.recipe.RecipeDTO;
import bg.softuni.milionrecepti.model.entity.CategoryEntity;
import bg.softuni.milionrecepti.model.entity.ImageEntity;
import bg.softuni.milionrecepti.model.entity.RecipeEntity;
import bg.softuni.milionrecepti.model.entity.UserEntity;
import bg.softuni.milionrecepti.model.enums.CategoryEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
//    @Named("categoriesSet")
//    static String mapCategories(CategoryEntity category){
//        return category.getCategory().name();
//    }
//
//
//    @Named("images")
//    static Set<String> mapImages(Set<ImageEntity> images){
//        return images.stream().map(ImageEntity::getImageUrl)
//            .collect(Collectors.toSet());
//
//    }
//
////    @Named("images")
////    List<ImageEntity> getImageUrls(List<String> images);
//
//    @Named("catMap")
//    static CategoryEntity catMap(CategoryEnum category){
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setCategory(category);
//        return categoryEntity;
//    }
//    @Mapping(source = "category", target = "category", qualifiedByName = "catMap")
//    RecipeEntity createRecipeDTOToRecipeEntity(CreateRecipeDTO createRecipeDTO);
//    @Mapping(source = "category", target = "category", qualifiedByName = "categoriesSet")
//    @Mapping(source = "images", target = "images", qualifiedByName = "images")
//    @Mapping(source = "author.firstName", target = "authorFirstName")
//    @Mapping(source = "author.lastName", target = "authorLastName")
//    RecipeDTO recipeEntityToRecipeDTO(RecipeEntity recipeEntity);

    @Named("categoriesSet")
    static String mapCategories(CategoryEntity category){
        return category.getCategory().name();
    }

    @Named("images")
    static Set<String> mapImages(Set<ImageEntity> images) {
        return images.stream().map(ImageEntity::getImageUrl)
                .collect(Collectors.toSet());
    }

    @Named("catMap")
    static CategoryEntity catMap(CategoryEnum category){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategory(category);
        return categoryEntity;
    }

    @Mapping(source = "category", target = "category", qualifiedByName = "catMap")
    RecipeEntity createRecipeDTOToRecipeEntity(CreateRecipeDTO createRecipeDTO);

    @Mapping(source = "category", target = "category", qualifiedByName = "categoriesSet")
    @Mapping(source = "images", target = "images", qualifiedByName = "images")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    RecipeDTO recipeEntityToRecipeDTO(RecipeEntity recipeEntity);

}






