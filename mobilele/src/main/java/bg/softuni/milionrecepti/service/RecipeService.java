package bg.softuni.milionrecepti.service;

//import bg.softuni.milionrecepti.config.CloudinaryConfig;
import bg.softuni.milionrecepti.model.dto.recipe.CreateRecipeDTO;
import bg.softuni.milionrecepti.model.dto.recipe.RecipeDTO;
import bg.softuni.milionrecepti.model.entity.CategoryEntity;
import bg.softuni.milionrecepti.model.entity.ImageEntity;
import bg.softuni.milionrecepti.model.entity.RecipeEntity;
import bg.softuni.milionrecepti.model.entity.UserEntity;
import bg.softuni.milionrecepti.model.enums.CategoryEnum;
import bg.softuni.milionrecepti.model.enums.SubcategoryEnum;
import bg.softuni.milionrecepti.model.enums.UserRoleEnum;
import bg.softuni.milionrecepti.model.mapper.RecipeMapper;
import bg.softuni.milionrecepti.repository.CategoryRepository;
import bg.softuni.milionrecepti.repository.ImageRepository;
import bg.softuni.milionrecepti.repository.RecipeRepository;
import bg.softuni.milionrecepti.repository.UserRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeMapper recipeMapper;
    //private final CloudinaryConfig cloudinary = new  CloudinaryConfig();
    private final String uploadDir = "images";  // Directory to save images
    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository, ImageRepository imageRepository, CategoryRepository categoryRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.recipeMapper = recipeMapper;
    }



    public boolean isOwner(String userName, Long recipeId) {
        boolean isOwner = recipeRepository.
                findById(recipeId).
                filter(o -> o.getAuthor().getEmail().equals(userName)).
                isPresent();

        if (isOwner) {
            return true;
        }

        return userRepository.
                findByEmail(userName).
                filter(this::isAdmin).
                isPresent();
    }





    private boolean isAdmin(UserEntity user) {
        return user.getUserRoles().
                stream().
                anyMatch(r -> r.getUserRole() == UserRoleEnum.ADMIN);

    }

//    public Page<RecipeEntity> getApprovedRecords(Pageable pageable) {
//        return recipeRepository.findByApprovedTrue(pageable);
//    }

    public Page<RecipeDTO> getApprovedRecipes(Pageable pageable) {
        return recipeRepository.
                findByApprovedTrue(pageable).
                //stream().
                        map(recipeMapper::recipeEntityToRecipeDTO);//.collect(Collectors.toSet());
    }

//    public Page<RecipeEntity> getApprovedRecipes1(Pageable pageable) {
//        return recipeRepository.findByApprovedTrue(pageable);
//    }

//    public Page<RecipeDTO> getAllRecipes(Pageable pageable) {
//        return recipeRepository.
//                findAll(pageable).
//                //stream().
//                map(recipeMapper::recipeEntityToRecipeDTO);//.collect(Collectors.toSet());
//    }

    public Page<RecipeDTO> getAllRecipesPageable(Pageable pageable) {
        return recipeRepository.
                findAll(pageable).
                map(recipeMapper::recipeEntityToRecipeDTO);

    }

//    public Page<RecipeDTO> recipeEntityToRecipeDTOisStatus(Pageable pageable) {
//        return recipeRepository.
//                findAll(pageable).
//                map(recipeMapper::recipeEntityToRecipeDTOisStatus);
//    }





    @Cacheable("recipeByCategory")
    public Set<RecipeDTO> getRecipesByCategory(String category) {
        CategoryEntity categoryEntity = categoryRepository.findByCategory(CategoryEnum.valueOf(category)).orElseThrow();
        return recipeRepository.
                findAllByCategory(categoryEntity).
                stream().
                map(recipeMapper::recipeEntityToRecipeDTO).collect(Collectors.toSet());
    }

    @Cacheable("recipeBySubcategory")
    public Set<RecipeDTO> getRecipesBySubcategory(String subcategory) {
        return recipeRepository.
                findAllBySubcategory(SubcategoryEnum.valueOf(subcategory)).
                stream().
                map(recipeMapper::recipeEntityToRecipeDTO).collect(Collectors.toSet());
    }



    @Cacheable("recipeById")
    public Optional<RecipeDTO> findRecipeById(Long recipeId) {
        return recipeRepository.
                findById(recipeId).
                map(recipeMapper::recipeEntityToRecipeDTO);
    }



//    public void addRecipe(CreateRecipeDTO addRecipeDTO, UserDetails userDetails) throws IOException {
//        long millis = System.currentTimeMillis();
//        Date date = new Date(millis);
//        ImageEntity image = new ImageEntity();
//
//        RecipeEntity newRecipe = recipeMapper.
//                createRecipeDTOToRecipeEntity(addRecipeDTO);
//
//        UserEntity author = userRepository.findByEmail(userDetails.getUsername()).
//                orElseThrow();
//
//        CategoryEntity categoryEntity = categoryRepository.findByCategory(addRecipeDTO.getCategory()).orElseThrow();
//        newRecipe.setCategory(categoryEntity);
//        newRecipe.setCreatedOn(date);
//        newRecipe.setApproved(false);
//        newRecipe.setAuthor(author);
//
//        RecipeEntity r = recipeRepository.save(newRecipe);
//        if (!addRecipeDTO.getFirstImage().isEmpty()) {
//            Map uploadResult = cloudinary.cloudinary.uploader().upload(addRecipeDTO.getFirstImage(), cloudinary.params);
//            String g = (String) uploadResult.get("secure_url");
//            image.setImageUrl(g);
//            image.setRecipe(r);
//            imageRepository.save(image);
//        }
//        if (!addRecipeDTO.getSecondImage().isEmpty()) {
//            image.setImageUrl(addRecipeDTO.getSecondImage());
//            image.setRecipe(r);
//            imageRepository.save(image);
//        }
//        if (!addRecipeDTO.getThirdImage().isEmpty()) {
//            image.setImageUrl(addRecipeDTO.getThirdImage());
//            image.setRecipe(r);
//            imageRepository.save(image);
//        }
//
//        /*public List<RecipeDTO> searchRecipe(SearchRecipeDTO searchRecipeDTO) {
//            return this.recipeRepository.findAll(new RecipeSpecification(searchRecipeDTO)).
//                    stream().map(recipeMapper::recipeEntityToRecipeDTO).
//                    toList();
//        }
//
//        @CacheEvict(cacheNames = "recipes", allEntries = true)
//        public void refresh() {
//        }*/
//    }


//    public void addRecipe(CreateRecipeDTO addRecipeDTO, UserDetails userDetails) throws IOException {
//        long millis = System.currentTimeMillis();
//        Date date = new Date(millis);
//
//        RecipeEntity newRecipe = recipeMapper.createRecipeDTOToRecipeEntity(addRecipeDTO);
//        UserEntity author = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
//        CategoryEntity categoryEntity = categoryRepository.findByCategory(addRecipeDTO.getCategory()).orElseThrow();
//
//        newRecipe.setCategory(categoryEntity);
//        newRecipe.setCreatedOn(date);
//        newRecipe.setApproved(false);
//        newRecipe.setAuthor(author);
//
//        RecipeEntity savedRecipe = recipeRepository.save(newRecipe);
//
//        if (addRecipeDTO.getImages() != null && !addRecipeDTO.getImages().isEmpty()) {
//            for (MultipartFile imageFile : addRecipeDTO.getImages()) {
//                if (!imageFile.isEmpty()) {
//                    Map<String, String> uploadResult = cloudinary.cloudinary.uploader().upload(imageFile.getBytes(), cloudinary.params);
//                    String imageUrl = uploadResult.get("secure_url");
//                    ImageEntity imageEntity = new ImageEntity();
//                    imageEntity.setImageUrl(imageUrl);
//                    imageEntity.setRecipe(savedRecipe);
//                    imageRepository.save(imageEntity);
//                }
//            }
//        }
//    }

//    public void addRecipe(CreateRecipeDTO createRecipeDTO, UserDetails userDetails) throws IOException {
//        long millis = System.currentTimeMillis();
//        Date date = new Date(millis);
//
//        RecipeEntity newRecipe = new RecipeEntity();
//        newRecipe.setName(createRecipeDTO.getName());
//        newRecipe.setCategory(categoryRepository.findByCategory(createRecipeDTO.getCategory())
//                .orElseThrow(() -> new RuntimeException("Category not found")));
//        newRecipe.setSubcategory(createRecipeDTO.getSubcategory());
//        //newRecipe.setVegetarian(createRecipeDTO.isVegetarian());
//        newRecipe.setPortions(createRecipeDTO.getPortions());
//        newRecipe.setIngredients(createRecipeDTO.getIngredients());
//        newRecipe.setMaking(createRecipeDTO.getMaking());
//        newRecipe.setHours(createRecipeDTO.getHours());
//        newRecipe.setMinutes(createRecipeDTO.getMinutes());
//        newRecipe.setCreatedOn(date);
//        newRecipe.setApproved(false);
//
//        UserEntity author = userRepository.findByEmail(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        newRecipe.setAuthor(author);
//
//        // Save the recipe first to get the ID
//        RecipeEntity savedRecipe = recipeRepository.save(newRecipe);
//
//        // Handle image uploads
//        for (MultipartFile imageFile : createRecipeDTO.getImages()) {
//            if (!imageFile.isEmpty()) {
//                saveImageToCloudinary(imageFile, savedRecipe);
//            }
//        }
//    }
//
//    private void saveImageToCloudinary(MultipartFile imageFile, RecipeEntity recipe) throws IOException {
//        // Upload image to Cloudinary
//        Map<String, Object> uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
//        String imageUrl = (String) uploadResult.get("secure_url");
//
//        if (imageUrl != null) {
//            ImageEntity imageEntity = new ImageEntity();
//            imageEntity.setImageUrl(imageUrl);
//            imageEntity.setRecipe(recipe);
//
//            imageRepository.save(imageEntity);
//        } else {
//            throw new IOException("Image URL is null after upload");
//        }
//    }

    public void addRecipe(CreateRecipeDTO createRecipeDTO, UserDetails userDetails) throws IOException {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        RecipeEntity newRecipe = new RecipeEntity();
        newRecipe.setName(createRecipeDTO.getName());
        newRecipe.setCategory(categoryRepository.findByCategory(createRecipeDTO.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found")));
        newRecipe.setSubcategory(createRecipeDTO.getSubcategory());
        //newRecipe.setVegetarian(createRecipeDTO.isVegetarian());
        newRecipe.setPortions(createRecipeDTO.getPortions());
        newRecipe.setIngredients(createRecipeDTO.getIngredients());
        newRecipe.setMaking(createRecipeDTO.getMaking());
        newRecipe.setHours(createRecipeDTO.getHours());
        newRecipe.setMinutes(createRecipeDTO.getMinutes());
        newRecipe.setCreatedOn(date);
        newRecipe.setApproved(false);

        UserEntity author = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        newRecipe.setAuthor(author);

        // Save the recipe first to get the ID
        RecipeEntity savedRecipe = recipeRepository.save(newRecipe);

        // Handle image uploads
        for (MultipartFile imageFile : createRecipeDTO.getImages()) {
            if (!imageFile.isEmpty()) {
                saveImageLocally(imageFile, savedRecipe);
            }
        }
    }

    private void saveImageLocally(MultipartFile imageFile, RecipeEntity recipe) throws IOException {
        // Create directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save image to the local filesystem
        String originalFilename = imageFile.getOriginalFilename();
        if (originalFilename != null) {
            String fileExtension = getFileExtension(originalFilename);
            String newFileName = System.currentTimeMillis() + fileExtension; // Unique filename
            Path filePath = uploadPath.resolve(newFileName);
            Files.write(filePath, imageFile.getBytes());

            // Save image information in the database
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setImageUrl("/" + uploadDir + "/" + newFileName);  // Relative URL
            imageEntity.setRecipe(recipe);
            imageRepository.save(imageEntity);
        }
    }

    private String getFileExtension(String filename) {
        if (filename.lastIndexOf(".") != -1 && filename.lastIndexOf(".") != 0) {
            return filename.substring(filename.lastIndexOf("."));
        } else {
            return ""; // Empty extension
        }
    }



}
