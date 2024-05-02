package business.designImpl;

import business.design.ICatalogService;
import business.model.Catalog;
import business.util.validation.IOFile;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class CatalogService implements ICatalogService {
    private File catalogFile = new File(IOFile.CATALOG);

    //Phương thức kiểm tra id trong Catalog đã tồn tại chưa
    public boolean checkCatalogIdExist(String id) {
        List<Catalog> catalogs = IOFile.readFromFile(IOFile.CATALOG);
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    //Phương thức kiểm tra tên trong Catalog đã tồn tại chưa
    public boolean checkCatalogNameExist(String name) {
        List<Catalog> catalogs = IOFile.readFromFile(IOFile.CATALOG);
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //Phương thức kiểm tra mô tả trong Catalog đã tồn tại chưa
    public boolean checkCatalogDescriptionExist(String description) {
        List<Catalog> catalogs = IOFile.readFromFile(IOFile.CATALOG);
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogDescription().equals(description)) {
                return true;
            }
        }
        return false;
    }

   @Override
public void addNewCategories(int numberOfCategories) {
    Scanner scanner = new Scanner(System.in);
    List<Catalog> catalogs = IOFile.readFromFile(IOFile.CATALOG);

    for (int i = 0; i < numberOfCategories; i++) {
        String id;
        do {
            System.out.println("Nhập id danh mục: ");
            id = scanner.nextLine();
            if (id.isEmpty()) {
                System.out.println("Id không thể để trống. Vui lòng nhập lại.");
            } else if (checkCatalogIdExist(id)) {
                System.out.println("Id này đã tồn tại. Vui lòng nhập id khác.");
            }
        } while (id.isEmpty() || checkCatalogIdExist(id));

        String name;
        do {
            System.out.println("Nhập tên danh mục: ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Tên không thể để trống. Vui lòng nhập lại.");
            } else if (checkCatalogNameExist(name)) {
                System.out.println("Tên này đã tồn tại. Vui lòng nhập tên khác.");
            }
        } while (name.isEmpty() || checkCatalogNameExist(name));

        String description;
        do {
            System.out.println("Nhập mô tả danh mục: ");
            description = scanner.nextLine();
            if (description.isEmpty()) {
                System.out.println("Mô tả không thể để trống. Vui lòng nhập lại.");
            } else if (checkCatalogDescriptionExist(description)) {
                System.out.println("Mô tả này đã tồn tại. Vui lòng nhập mô tả khác.");
            }
        } while (description.isEmpty() || checkCatalogDescriptionExist(description));

        Catalog catalog = new Catalog(id, name, description);
        catalogs.add(catalog);
    }
    IOFile.writeToFile(IOFile.CATALOG, catalogs);
}

@Override
public void displayAllCategories() {
    List<Catalog> catalogs = IOFile.readFromFile(IOFile.CATALOG);
    if (catalogs.isEmpty()) {
        System.out.println("Danh mục của bạn đang trống");
        return;
    }
    for (Catalog catalog : catalogs) {
        System.out.println(catalog);
    }
}
   @Override
public void editCategoryById(String categoryId, String newCategoryName) {
    List<Catalog> catalogs = IOFile.readFromFile(IOFile.CATALOG);
    for (Catalog catalog : catalogs) {
        if (catalog.getCatalogId().equals(categoryId)) {
            catalog.setCatalogName(newCategoryName);
        }
    }
    IOFile.writeToFile(IOFile.CATALOG, catalogs);
}

    @Override
public void deleteCategoryById(String categoryId) {
    List<Catalog> catalogs = IOFile.readFromFile(IOFile.CATALOG);
    catalogs.removeIf(catalog -> catalog.getCatalogId().equals(categoryId));
    IOFile.writeToFile(IOFile.CATALOG, catalogs);
}
    //Tìm kiếm xem có catalog nào trùng với tên catalog truyền vào không
public static Catalog findCatalogByName(String catalogName) {
    List<Catalog> catalogNameToFind = IOFile.readFromFile(IOFile.CATALOG);
    for (Catalog catalog : catalogNameToFind) {
        if (catalog.getCatalogName().equals(catalogName)) {
            return catalog;
        }
    }
    return null;
}
}