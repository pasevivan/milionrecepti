package bg.softuni.milionrecepti.model.dto.category;

import bg.softuni.milionrecepti.model.enums.CategoryEnum;

public class  CategoryDTO {

    private long id;
    private CategoryEnum category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public CategoryDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}

