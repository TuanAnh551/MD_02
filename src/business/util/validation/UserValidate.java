package business.util.validation;

import business.model.User;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserValidate {
    // Phương thức để kiểm tra tên đăng nhập có tồn tại hay không
    public static boolean isUsernameUnique(String inputUsername) {
        List<User> userList=IOFile.readFromFile(IOFile.USER);
        for (User user : userList) {
            Optional<String> userName = Optional.ofNullable(user.getUserName());
            if (userName.isPresent() && userName.get().equals(inputUsername)) {
                return false;
            }
        }
        return true;
    }

    // Phương thức để kiểm tra mật khẩu có đủ 6 kí tự hay không
    public static boolean isPasswordValid(String password) {
        return Pattern.matches("^[a-zA-Z0-9]{6,}$",password);
    }

    // Phương thức để kiểm tra email có đúng định dạng hay không
    public static boolean validateEmail(String email) {
        // Sử dụng biểu thức chính quy để kiểm tra định dạng email
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }
    public static boolean validatePhone(String phone) {
        // Thực hiện kiểm tra số điện thoại, ví dụ: chỉ chấp nhận số và có độ dài từ 10 đến 15 ký tự
        return phone.matches("\\d{10,15}");
    }
}
