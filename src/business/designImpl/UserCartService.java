package business.designImpl;

import business.design.IUserCartService;
import business.model.Cart;
import business.model.Manager;
import business.model.User;
import business.util.validation.IOFile;
import business.util.validation.InputMethod;
import business.util.validation.UserValidate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserCartService implements IUserCartService {
  public List<Manager> productList = IOFile.readFromFile(IOFile.MANAGER_USER);
    public Map<String, List<Cart>> cart = IOFile.readFromFileWithCart(IOFile.USER_CART);
public List<User> userList = IOFile.readFromFile(IOFile.USER);

    @Override
    public void displayProducts() {
        if (productList.isEmpty()) {
            System.out.println("Không có sản phẩm nào cả");
        } else {
            for (Manager product : productList) {
                System.out.println(product.toString());
            }
        }
    }
 @Override
public void addToCart(String productId, int quantity, User user) {
    String strProductId = String.valueOf(productId);
    for (Manager product : productList) {
        if (product.getProductId().equals(strProductId)) {
            if (product.getProductStock() >= quantity) {
                List<Cart> userCart = cart.get(user.getUserName()); // Lấy giỏ hàng của người dùng
                if (userCart == null) {
                    userCart = new ArrayList<>();
                    cart.put(user.getUserName(), userCart);
                }
                for (Cart item : userCart) {
                    if (item.getProduct().equals(product.getProductName())) {
                        item.setQuantity(item.getQuantity() + quantity);
                        product.setProductStock(product.getProductStock() - quantity);
                        IOFile.writeToFile(IOFile.USER_CART, cart);
//                        IOFile.writeToFile(IOFile.MANAGER_USER, productList); // Lưu lại thay đổi vào tệp
                        return;
                    }
                }
                userCart.add(new Cart(userCart.size() + 1, product.getProductName(), product.getProductPrice(), quantity));
                product.setProductStock(product.getProductStock() - quantity);
                IOFile.writeToFile(IOFile.USER_CART, cart);
//                IOFile.writeToFile(IOFile.MANAGER_USER, productList); // Lưu lại thay đổi vào tệp
            } else {
                System.out.println("Không đủ hàng. Chỉ còn " + product.getProductStock() + " sản phẩm.");
            }
            return;
        }
    }
    System.out.println("Không tìm thấy sản phẩm.");
}
@Override
public void displayCart(User user) {
    List<Cart> userCart = cart.get(user.getUserName());
    if (userCart == null || userCart.isEmpty()) {
        System.out.println("Giỏ hàng của bạn đang trống.");
    } else {
        double total = 0;
        System.out.println("Giỏ hàng của bạn chứa các mặt hàng sau:");
        for (Cart item : userCart) {
            System.out.println(item.toString());
        }
    }
}
    @Override
    public void updateQuantity(int cartItemId, int newQuantity, User user) {
        List<Cart> userCart = cart.get(user.getUserName());
        for (Cart item : userCart) {
            if (item.getCartItemId() == cartItemId) {
                int difference = newQuantity - item.getQuantity();
                for (Manager product : productList) {
                    if (product.getProductId().equals(item.getProduct())) {
                        if (newQuantity > item.getQuantity()) {
                            if (product.getProductStock() >= difference) {
                                item.setQuantity(newQuantity);
                                product.setProductStock(product.getProductStock() - difference);
                            } else {
                                System.out.println("Không đủ hàng. ");
                            }
                        } else {
                            item.setQuantity(newQuantity);
                            product.setProductStock(product.getProductStock() + difference);
                        }
                        IOFile.writeToFile(IOFile.USER_CART, cart);
                        return;
                    }
                }
            }
        }
        System.out.println("Không tìm thấy mặt hàng trong giỏ.");
    }

    // xóa 1 sản phẩm
    @Override
    public void removeFromCart(int cartItemId, User user) {
        List<Cart> userCart = cart.get(user.getUserName());
        for (int i = 0; i < userCart.size(); i++) {
            if (userCart.get(i).getCartItemId() == cartItemId) {
                int quantity = userCart.get(i).getQuantity();
                String productName = userCart.get(i).getProduct();
                for (Manager product : productList) {
                    if (product.getProductName().equals(productName)) {
                        product.setProductStock(product.getProductStock() + quantity);
                        break;
                    }
                }
                userCart.remove(i);
                return;
            }
        }
        System.out.println("Không tìm thấy mặt hàng trong giỏ.");
    }

    // xóa hết sản phẩm
    @Override
    public void clearCart(User user) {
        List<Cart> userCart = cart.get(user.getUserName());
        for (Cart item : userCart) {
            int quantity = item.getQuantity();
            String productName = item.getProduct();
            for (Manager product : productList) {
                if (product.getProductName().equals(productName)) {
                    product.setProductStock(product.getProductStock() + quantity);
                    break;
                }
            }
        }
        userCart.clear();
    }
    @Override
    public void checkout(User user) {
        List<Cart> userCart = cart.get(user.getUserName());
        double total = 0;
        for (Cart item : userCart) {
            total += item.getPrice() * item.getQuantity();
        }
        System.out.println("Tổng cộng: " + total);
        userCart.clear();
    }





    public void changePassword() {
        System.out.println("Nhập tên đăng nhập:");
        String username = InputMethod.getString();
        System.out.println("Nhập mật khẩu cũ:");
        String oldPassword = InputMethod.getString();

        for (User user : userList) {
            if (user.getUserName().equals(username) && user.getPassword().equals(oldPassword)) {
                System.out.println("Nhập mật khẩu mới:");
                String newPassword = InputMethod.getString();
                // Nếu mật khẩu mới hợp lệ
                if (UserValidate.isPasswordValid(newPassword)) {
                    // Thay đổi mật khẩu của người dùng
                    user.setPassword(newPassword);
                    IOFile.writeToFile(IOFile.USER, userList);
                    System.out.println("Đổi mật khẩu thành công.");
                } else {
                    System.out.println("Mật khẩu không hợp lệ. Vui lòng thử lại.");
                }
                return;
            }
        }
        System.out.println("Tên đăng nhập hoặc mật khẩu không hợp lệ.");
    }

    @Override

    public void logout(User user) {
    System.out.println("Bạn đã đăng xuất.");
    Map<String, List<Cart>> userCarts = IOFile.readFromFileWithCart(IOFile.USER_CART);
    userCarts.put(user.getUserName(), user.getCart());
    IOFile.writeToFile(IOFile.USER_CART, userCarts);
}
}