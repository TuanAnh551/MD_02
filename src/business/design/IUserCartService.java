package business.design;

import business.model.User;

public interface IUserCartService {
    void displayProducts();

    void addToCart(String productId, int quantity, User user);

    void displayCart( User user);

//    void addToCart(  int productId, int quantity, int userId);

    void updateQuantity(int cartItemId, int newQuantity, User user);

    // xóa 1 sản phẩm
    void removeFromCart(int cartItemId, User user);
    void clearCart( User user);
    void checkout( User user);
    void changePassword();

    void logout(User user);
}
