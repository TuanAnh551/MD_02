package business.util.validation;


import business.model.Cart;
import business.model.Manager;
import business.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOFile {
    public final static String MANAGER_USER = "src/business/data/Manager.txt";
    public final static String USER = "src/business/data/Users.txt";
    public final static String CATALOG = "src/business/data/Catalog.txt";

    public static final  String USER_CART = "src/business/data/UserCart.txt";
    public static <T> void  writeToFile(){
        File file = new File(MANAGER_USER);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<T>());
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


 public static <T> void writeToFile(String path, T obj) {
    File file = new File(path);
    try (FileOutputStream fos = new FileOutputStream(file);
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(obj);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


public static <T> List<T> readFromFile(String path) {
    File file = new File(path);
    List<T> list = new ArrayList<>();
    try (FileInputStream fis = new FileInputStream(file);
         ObjectInputStream ois = new ObjectInputStream(fis)) {
        list = (List<T>) ois.readObject();
    } catch (EOFException | FileNotFoundException e) {
        // Handle exception if necessary
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return list;
}



   public static List<Manager> readFromFile2(String fileName) {
    List<Manager> managers = new ArrayList<>();
    try {
        FileInputStream fis = new FileInputStream(fileName);
        if (fis.available() > 0) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object object = ois.readObject();
            if (object instanceof List) {
                managers = (List<Manager>) object;
            }
            ois.close();
        }
        fis.close();
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return managers;
}
  public static Map<String, List<Cart>> readFromFileWithCart(String fileName) {
    Map<String, List<Cart>> userCarts = new HashMap<>();
    try {
        FileInputStream fis = new FileInputStream(fileName);
        if (fis.available() > 0) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object object = ois.readObject();
            if (object instanceof Map) {
                userCarts = (Map<String, List<Cart>>) object;
            }
            ois.close();
        }
        fis.close();
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return userCarts;
}

   public static User readDataLogin(String path) {
        File file = new File(path);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        User object = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            object = (User) ois.readObject();
            ois.close();
        } catch (EOFException | FileNotFoundException e) {
            // Xử lý các ngoại lệ nếu cần
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }


}
