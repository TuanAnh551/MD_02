package business.design;

public interface ICatalogService {
    void addNewCategories(int numberOfCategories);
    void displayAllCategories();
    void editCategoryById(String categoryId, String newCategoryName);
    void deleteCategoryById(String categoryId);
}
