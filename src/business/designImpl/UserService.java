package business.designImpl;

import business.design.IUserService;
import business.model.Cart;
import business.model.User;
import business.util.validation.IOFile;
import business.util.validation.InputMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static business.util.validation.UserValidate.*;

public class UserService implements IUserService {
    public List<User> userList = IOFile.readFromFile(IOFile.USER);
    public User loggedInUser;


    @Override
    public void register() {
        System.out.println("Nhập tên đăng nhập:");
        String username = InputMethod.getString();
        userList = IOFile.readFromFile(IOFile.USER);
        while (!isUsernameUnique(username)) {
            System.out.println("Tên đã tồn tại. Vui lòng đăng kí với tên khác.");
            username = InputMethod.getString();
        }

        System.out.println("Nhập mật khẩu:");
        String password = InputMethod.getString();
        while (!isPasswordValid(password)) {
            System.out.println("Mật khẩu phải có 6 chữ số trở lên. Vui lòng thử lại.");
            password = InputMethod.getString();
        }

        System.out.println("Nhập email:");
        String email = InputMethod.getString();
        while (!validateEmail(email)) {
            System.out.println("Email không hợp lệ. Vui lòng thử lại.");
            email = InputMethod.getString();
        }

        List<Cart> newCart = new ArrayList<>();
        User newUser = new User(userList.size() + 1, username, password, email, newCart, java.time.LocalDate.now());
        userList.add(newUser);
        IOFile.writeToFile(IOFile.USER, userList);
        System.out.println("Tạo tài khoản thành công");
        userList = IOFile.readFromFile(IOFile.USER);
    }

    public boolean login() {
        System.out.println("Nhập tên đăng nhập:");
        String username = InputMethod.getString();
        System.out.println("Nhập mật khẩu:");
        String password = InputMethod.getString();

        userList = IOFile.readFromFile(IOFile.USER);

     for (User user : userList) {
    if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {
        loggedInUser = user;
        Map<String, List<Cart>> userCarts = IOFile.readFromFileWithCart(IOFile.USER_CART);
        List<Cart> userCart = userCarts.get(username);

        // Nếu người dùng có giỏ hàng, cập nhật nó. Nếu không, tạo một giỏ hàng mới cho họ
        if (userCart != null) {
            userCart.addAll(user.getCart());
        } else {
            userCart = new ArrayList<>(user.getCart());
        }
        userCarts.put(username, userCart);
        IOFile.writeToFile(IOFile.USER_CART, userCarts);

        return true;
    }
}
        System.out.println("Mật khẩu không tồn tại, vui lòng đăng kí.");
        return false;
    }


    //Phương thức để lấy người dùng đang đăng nhập
    public User getLoggedInUser() {
        return loggedInUser;
    }

    //Phương thức để thay đổi mật khẩu
    @Override
    public void forgotPassword() {
        System.out.println("Nhập tên đăng nhập:");
        String username = InputMethod.getString();
        System.out.println("Nhập email:");
        String email = InputMethod.getString();

        userList = IOFile.readFromFile(IOFile.USER);

        for (User user : userList) {
            if (user.getUserName().equals(username) && user.getEmail().equals(email)) {
                System.out.println("Nhập mật khẩu mới:");
                String newPassword = InputMethod.getString();
                if (isPasswordValid(newPassword)) {
                    user.setPassword(newPassword);
                    IOFile.writeToFile(IOFile.USER, userList); // Ghi userList đã cập nhật vào tệp
                    System.out.println("Mật khẩu đã được thay đổi thành công.");
                } else {
                    System.out.println("Mật khẩu không hợp lệ. Vui lòng thử lại.");
                }
                break;
            }
        }
    }

    //Phương thức để hiển thị toàn bộ thông tin người dùng
    @Override
    public void displayAllUserInfo() {
        userList = IOFile.readFromFile(IOFile.USER);
        for (User user : userList) {
            System.out.println(user);
        }

    }

    //Phương thức để xóa người dùng
    @Override
    public void deleteUser() {
        userList = IOFile.readFromFile(IOFile.USER);
        System.out.println("Nhập tên đăng nhập để xóa:");
        String username = InputMethod.getString();
        for (User user : userList) {
            if (user.getUserName().equals(username)) {
                userList.remove(user);
                IOFile.writeToFile(IOFile.USER, userList);
                System.out.println("Người dùng đã được xóa.");
                return;
            }
        }
        System.out.println("Không tìm thấy người dùng.");
    }
}