package bg.softuni.milionrecepti.init;

import bg.softuni.milionrecepti.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private CategoryService categoryService;

    @PostConstruct
    public void populateInitialData() {
        categoryService.populateCategories();
    }
}
