package business.util.validation;

import java.util.Scanner;

public class InputMethod {
    private static final String ERROR_ALERT = "Định dạng không hợp lệ, hoặc ngoài phạm vi! Vui lòng thử lại";
    private static final String EMPTY_ALERT = "Trường nhập vào không thể để trống! Vui lòng thử lại";


    public static String getString() {
        while (true) {
            String result = getInput();
            if (result.equals("")) {
                System.err.println(EMPTY_ALERT);
                continue;
            }
            return result;
        }
    }
    public static char getChar() {
        return getString().charAt(0);
    }

    public static boolean getBoolean() {
        String result = getString();
        return result.equalsIgnoreCase("true");
    }


    public static byte getByte() {
        while (true) {
            try {
                return Byte.parseByte(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }


    public static short getShort() {
        while (true) {
            try {
                return Short.parseShort(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    public static int getInteger() {
        while (true) {
            try {
                return Integer.parseInt(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    public static long getLong() {
        while (true) {
            try {
                return Long.parseLong(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }



    public static float getFloat() {
        while (true) {
            try {
                return Float.parseFloat(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    public static double getDouble() {
        while (true) {
            try {
                return Double.parseDouble(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }
    // return a string from the user
    private static String getInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    public static void pressAnyKey() {
        getInput();
    }

    //validate product id
    public static String validateProductId() {
        while (true) {
            String result = getString();
            if (result.matches("^[A-Z]{1}\\d{4}$")) {
                return result;
            }
            System.err.println("Mã sản phẩm phải có 1 ký tự đầu là chữ cái viết hoa và 4 ký tự cuối là số");
        }
    }

    //validate product name
    public static String validateProductName() {
        while (true) {
            String result = getString();
            if (result.matches("^[a-zA-Z0-9\\s]{1,50}$")) {
                return result;
            }
            System.err.println("Tên sản phẩm không được chứa ký tự đặc biệt và không quá 50 ký tự");
        }
    }

    //validate product price
    public static double validateProductPrice() {
        while (true) {
            double result = getDouble();
            if (result > 10) {
                return result;
            }
            System.err.println("Giá sản phẩm phải lớn hơn 10");
        }
    }

    //validate product description
    public static String validateProductDescription() {
        while (true) {
            String result = getString();
            if (result.matches("^.{1,100}$")) {
                return result;
            }
            System.err.println("Mô tả sản phẩm không được chứa ký tự đặc biệt và không quá 100 ký tự");
        }
    }

    //validate product catalog
    public static String validateProductCatalog() {
        while (true) {
            String result = getString();
            if (result.matches("^[a-zA-Z0-9\\s]{1,50}$")) {
                return result;
            }
            System.err.println("Danh mục sản phẩm không được chứa ký tự đặc biệt và không quá 50 ký tự");
        }
    }

    //validate product stock
    public static int validateProductStock() {
        while (true) {
            int result = getInteger();
            if (result > 10) {
                return result;
            }
            System.err.println("Số lượng sản phẩm phải lớn hơn 10");
        }
    }



}



