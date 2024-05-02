package business.model;


import java.io.Serializable;

public class Cart implements Serializable {

    private int cartItemId;
    private String product;
    private double price;
    private int quantity;
    private boolean status;

    public Cart(  int cartItemId, String product, double price, int quantity) {

        this.cartItemId = cartItemId;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public Cart(int cartItemId, Manager product, int quantity) {
    }

    public Cart() {

    }


    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s",
                "Mã sản phẩm: ", cartItemId,
                "Tên sản phẩm: ", product,
                "Giá: ", price,
                "Số lượng: ", quantity);
        }

    }
