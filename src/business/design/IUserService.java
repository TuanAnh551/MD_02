package business.design;

public interface IUserService {
    void register();
    boolean login();
    void forgotPassword();

    //Phương thức để hiển thị thông tin người dùng
    void displayAllUserInfo();

    //Phương thức để xóa người dùng
    void deleteUser();
}
