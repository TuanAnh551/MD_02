package business.design;

public interface IManagerService {



    void addNewProduct(int numberOfProduct);

    void displayProduct();
    void sortProduct();

    void searchProductByName();
    void editProductById();
    void deleteProductById();


    void searchProductByCatalog();
}