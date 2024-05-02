package presentation.run;

import business.designImpl.CatalogService;
import business.designImpl.ManagerService;
import business.designImpl.UserCartService;
import business.designImpl.UserService;
import business.model.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static UserService userService = new UserService();
    public static ManagerService managerService = new ManagerService();
    public static CatalogService catalogService = new CatalogService();
    public static List<Manager> managers = new ArrayList<>();
    public static UserCartService userCartService = new UserCartService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng kí");
            System.out.println("3. Quên mật khẩu");
            System.out.println("4. Exit");
            System.out.println("Mởi bạn chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    if (userService.login()) {
                        String username = userService.getLoggedInUser().getUserName();
                        if ("admin".equals(username)) {
                            System.out.println("Xin chào " + username);
                            boolean isUser = true;
                            while (isUser) {
                                System.out.println("1. Quản lý danh mục");
                                System.out.println("2. Quản lý sản phẩm");
                                System.out.println("3. Hiển thị toàn bộ thông tin người dùng");
                                System.out.println("4. Xóa người dùng");
                                System.out.println("5. Đăng xuất");
                                System.out.println("Mời bạn hãy chọn: ");
                                int choice1 = scanner.nextInt();
                                scanner.nextLine();
                                switch (choice1) {
                                    case 1:
                                        boolean isCatalog = true;
                                        while (isCatalog) {
                                            System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
                                            System.out.println("2. Hiển thị thông tin tất cả các danh mục");
                                            System.out.println("3. Sửa tên danh mục theo mã danh mục");
                                            System.out.println("4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm)");
                                            System.out.println("5. Quay lại");
                                            System.out.println("Mời bạn hãy chọn: ");
                                            int catalogChoice = scanner.nextInt();
                                            scanner.nextLine();
                                            switch (catalogChoice) {
                                                case 1:
                                                    System.out.println("Nhập số danh mục bạn muốn thêm: ");
                                                    int numberOfCategories = scanner.nextInt();
                                                    catalogService.addNewCategories(numberOfCategories);
                                                    break;
                                                case 2:
                                                    catalogService.displayAllCategories();
                                                    break;
                                                case 3:
                                                    System.out.println("Nhập mã danh mục bạn muốn sửa: ");
                                                    String categoryId = scanner.nextLine();
                                                    System.out.println("Nhập tên danh mục mới: ");
                                                    String newCategoryName = scanner.nextLine();
                                                    catalogService.editCategoryById(categoryId, newCategoryName);
                                                    break;
                                                case 4:
                                                    System.out.println("Nhập mã danh mục bạn muốn xóa: ");
                                                    String deleteId = scanner.nextLine();
                                                    catalogService.deleteCategoryById(deleteId);
                                                    break;
                                                case 5:
                                                    isCatalog = false;
                                                    break;
                                                default:
                                                    System.out.println("Lựa chọn không phù hợp, vui lòng chọn lại");
                                            }
                                        }
                                        break;
                                    case 2:
                                        boolean isProduct = true;
                                        while (isProduct) {
                                            System.out.println("1. Thêm sản phẩm");
                                            System.out.println("2. Hiển thị tất cả sản phẩm");
                                            System.out.println("3. Sắp xếp sản phẩm theo tên sản phẩm");
                                            System.out.println("4. Tìm kiếm sản phẩm theo tên sản phẩm");
                                            System.out.println("5. Tìm kiếm sản phẩm theo danh mục");
                                            System.out.println("6. Sửa thông tin sản phẩm theo mã sản phẩm");
                                            System.out.println("7. Xóa sản phẩm theo mã sản phẩm");
                                            System.out.println("8. Quay lại");
                                            System.out.println("Mời bạn hãy chọn: ");
                                            int productChoice = scanner.nextInt();
                                            scanner.nextLine();
                                            switch (productChoice) {
                                                case 1:
                                                    System.out.println("Nhập số sản phẩm bạn muốn thêm: ");
                                                    int numberOfProduct = scanner.nextInt();
                                                    managerService.addNewProduct(numberOfProduct);
                                                    break;
                                                case 2:
                                                    managerService.displayProduct();
                                                    break;
                                                case 3:
                                                    managerService.sortProduct();
                                                    break;
                                                case 4:
                                                    managerService.searchProductByName();
                                                    break;
                                                case 5:
                                                    managerService.searchProductByCatalog();
                                                    break;
                                                case 6:
                                                    managerService.editProductById();
                                                    break;
                                                case 7:
                                                    managerService.deleteProductById();
                                                    break;
                                                case 8:
                                                    isProduct = false;
                                                    break;

                                                default:
                                                    System.out.println("Lựa chọn không phù hợp, vui lòng chọn lại");
                                            }
                                        }
                                        break;
                                    case 3:
                                        userService.displayAllUserInfo();
                                        break;
                                    case 4:
                                        userService.deleteUser();
                                        break;
                                    case 5:
                                        isUser = false;
                                        break;
                                }
                            }
                        } else {
                            System.out.println("Xin chào " + username);
                            boolean isUser = true;
                            while (isUser) {
                                System.out.println("1. Hiển thị tất cả sản phẩm");
                                System.out.println("2. Thêm sản phẩm vào giỏ hàng");
                                System.out.println("3. Hiển thị giỏ hàng");
                                System.out.println("4. Xóa 1 sản phẩm khỏi giỏ hàng");
                                System.out.println("5. Xóa tất cả sản phẩm trong giỏ hàng");
                                System.out.println("6. Thay đổi số lượng sản phẩm trong giỏ hàng");
                                System.out.println("7. Thanh toán");
                                System.out.println("8. Thay đổi mật khẩu");
                                System.out.println("9. Đăng xuất");
                                System.out.println("Mời bạn hãy chọn: ");
                                int userChoice = scanner.nextInt();
                                scanner.nextLine();
                                switch (userChoice) {
                                    case 1:
                                        userCartService.displayProducts();
                                        break;
                                    case 2:
                                        System.out.println("Nhập mã sản phẩm bạn muốn thêm vào giỏ hàng: ");
                                        String productId = scanner.nextLine();
                                        System.out.println("Nhập số lượng sản phẩm bạn muốn thêm vào giỏ hàng: ");
                                        int quantity = scanner.nextInt();
                                        userCartService.addToCart(productId, quantity, userService.getLoggedInUser());
                                        break;
                                    case 3:
                                        userCartService.displayCart(userService.getLoggedInUser());
                                        break;
                                    case 4:
                                        System.out.println("Nhập mã sản phẩm bạn muốn xóa khỏi giỏ hàng: ");
                                        int cartItemId = scanner.nextInt();
                                        userCartService.removeFromCart(cartItemId, userService.getLoggedInUser());
                                        break;
                                    case 5:
                                        userCartService.clearCart(userService.getLoggedInUser());
                                        break;
                                    case 6:
                                        System.out.println("Nhập mã sản phẩm bạn muốn thay đổi số lượng: ");
                                        int cartItemId1 = scanner.nextInt();
                                        System.out.println("Nhập số lượng mới: ");
                                        int newQuantity = scanner.nextInt();
                                        userCartService.updateQuantity(cartItemId1, newQuantity, userService.getLoggedInUser());
                                        break;
                                    case 7:
                                        userCartService.checkout(userService.getLoggedInUser());
                                        break;
                                    case 8:
                                        userCartService.changePassword();
                                        break;
                                    case 9:
                                        userCartService.logout(userService.getLoggedInUser());
                                        isUser = false;
                                        break;
                                    default:
                                        System.out.println("Lựa chọn không phù hợp, vui lòng chọn lại");
                                }
                            }

                        }
                    }
                    break;
                case 2:
                    userService.register();
                    break;
                case 3:
                    userService.forgotPassword();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, vui lòng chọn lại");
            }
        }


    }


}