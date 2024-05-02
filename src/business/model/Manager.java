package business.model;

import java.io.Serializable;

public class Manager implements Serializable {
    private String productId;
    private String productName;
    private double productPrice;
    private String productDescription;
    private String productCatalog;
    private int productStock;
    private boolean productStatus;

    public Manager(String productId, String productName, double productPrice, String productDescription, String productCatalog, int productStock, boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productCatalog = productCatalog;
        this.productStock = productStock;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(String productCatalog) {
        this.productCatalog = productCatalog;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        String status = productStatus ? "Đang bán" : "Hết hàng";
        return String.format("%-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s",
                "Mã sản phẩm: ", productId,
                "Tên sản phẩm: ", productName,
                "Giá: ", productPrice,
                "Mô tả: ", productDescription,
                "Danh mục: ", productCatalog,
                "Số lượng: ", productStock,
                "Trạng thái: ", status);

    }
}
