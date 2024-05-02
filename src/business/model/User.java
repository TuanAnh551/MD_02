package business.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class User implements  Serializable {

    private Integer userId;
    private String userName;
    private String password;
    private String email;
    private List<Cart> cart;

    private LocalDate createdDate;

    public User() {
    }

       public User(int userId, String userName, String password, String email, List<Cart> cart, LocalDate createdDate) {
            this.userId = userId;
            this.userName = userName;
            this.password = password;
            this.email = email;
            this.cart = cart;
            this.createdDate = createdDate;
        }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s",
                "Mã người dùng: ", userId,
                "Tên người dùng: ", userName,
                "Mật khẩu: ", password,
                "Email: ", email);
    }
}
