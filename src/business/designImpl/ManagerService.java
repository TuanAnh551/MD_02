package business.designImpl;

import business.design.IManagerService;
import business.model.Manager;
import business.util.validation.IOFile;
import business.util.validation.InputMethod;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class ManagerService implements IManagerService {
    private File managerFile = new File(IOFile.MANAGER_USER);


    //Phương thức kiểm tra id trong Manager đã tồn tại chưa
    public boolean checkProductIdExist(String id) {
        List<Manager> managers = IOFile.readFromFile2(IOFile.MANAGER_USER);
        for (Manager manager : managers) {
            if (manager.getProductId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkCatalogExist(String catalogName) {
        File file = new File(IOFile.CATALOG);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(catalogName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addNewProduct(int numberOfProduct) {
        List<Manager> managers = IOFile.readFromFile2(IOFile.MANAGER_USER);
        for (int i = 0; i < numberOfProduct; i++) {
            String id;
            do {
                System.out.println("Nhập id sản phẩm: ");
                id = InputMethod.validateProductId();
                if (checkProductIdExist(id)) {
                    System.out.println("Id này đã tồn tại. Vui lòng nhập id khác.");
                }
            } while (checkProductIdExist(id));

            System.out.println("Nhập tên sản phẩm: ");
            String name = InputMethod.validateProductName();
            System.out.println("Nhập giá sản phẩm: ");
            double price = InputMethod.validateProductPrice();
            System.out.println("Nhập mô tả sản phẩm: ");
            String description = InputMethod.validateProductDescription();
            String catalogName;
            do {
                System.out.println("Nhập danh mục sản phẩm: ");
                catalogName = InputMethod.validateProductCatalog();
                if (!checkCatalogExist(catalogName)) {
                    System.out.println("Danh mục này không tồn tại. Vui lòng nhập danh mục khác.");
                }
            } while (!checkCatalogExist(catalogName));

            System.out.println("Nhập số lượng sản phẩm: ");
            int stock = InputMethod.validateProductStock();
            System.out.println("Nhập trạng thái sản phẩm: ");
            boolean status = InputMethod.getBoolean();
            managers.add(new Manager(id, name, price, description, catalogName, stock, status));
        }
        IOFile.writeToFile(IOFile.MANAGER_USER, managers);
    }


    @Override
    public void displayProduct() {
        List<Manager> managers = IOFile.readFromFile(IOFile.MANAGER_USER);
        if (managers.isEmpty()) {
            System.out.println("Sản phẩm của bạn đang trống");
            return;
        }
        for (Manager manager : managers) {
            System.out.println(manager);
        }
    }


    @Override
    public void sortProduct() {
        List<Manager> productList = IOFile.readFromFile(IOFile.MANAGER_USER);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập 1 để sắp xếp theo thứ tự tăng dần, 2 để sắp xếp theo thứ tự giảm dần: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            productList.sort(Comparator.comparing(Manager::getProductName));
        } else if (choice == 2) {
            productList.sort(Comparator.comparing(Manager::getProductName).reversed());
        } else {
            System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập 1 hoặc 2.");
            return;
        }

        // Hiển thị danh sách sản phẩm đã sắp xếp
        for (Manager manager : productList) {
            System.out.println(manager);
        }

        IOFile.writeToFile(IOFile.MANAGER_USER, productList);
        System.out.println("Danh sách sản phẩm đã được sắp xếp và lưu.");
    }

    @Override
    public void searchProductByName() {
        List<Manager> managers = IOFile.readFromFile(IOFile.MANAGER_USER);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên sản phẩm để tìm kiếm: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Manager manager : managers) {
            if (manager.getProductName().contains(name)) {
                System.out.println(manager);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sản phẩm bạn đang tìm kiếm");
        }
    }

    @Override
    public void searchProductByCatalog() {
        List<Manager> managers = IOFile.readFromFile(IOFile.MANAGER_USER);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập danh mục sản phẩm để tìm kiếm: ");
        String catalog = scanner.nextLine();
        boolean found = false;
        for (Manager manager : managers) {
            if (manager.getProductCatalog().contains(catalog)) {
                System.out.println(manager);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sản phẩm bạn đang tìm kiếm");
        }
    }

    @Override
    public void editProductById() {
        List<Manager> managers = IOFile.readFromFile(IOFile.MANAGER_USER);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id sản phẩm để chỉnh sửa: ");
        String id = scanner.nextLine();
        System.out.println("Nhập tên sản phẩm mới: ");
        String name = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm mới: ");
        double price = scanner.nextDouble();
        System.out.println("Nhập mô tả sản phẩm mới: ");
        String description = scanner.next();
        for (Manager manager : managers) {
            if (manager.getProductId().equals(id)) {
                manager.setProductName(name);
                manager.setProductPrice(price);
                manager.setProductDescription(description);
            }
        }
        IOFile.writeToFile(IOFile.MANAGER_USER, managers);
    }

    @Override
    public void deleteProductById() {
        List<Manager> managers = IOFile.readFromFile(IOFile.MANAGER_USER);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id sản phẩm để xóa: ");
        String id = scanner.nextLine();
        for (Manager manager : managers) {
            if (manager.getProductId().equals(id)) {
                managers.remove(manager);
                break;
            }


        }
        IOFile.writeToFile(IOFile.MANAGER_USER, managers);
    }
}


