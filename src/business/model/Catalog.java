package business.model;

import java.io.Serializable;

public class Catalog implements Serializable {
    private String catalogId;
    private String catalogName;
    private String catalogDescription;

    public Catalog(String catalogId, String catalogName, String catalogDescription) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.catalogDescription = catalogDescription;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogDescription() {
        return catalogDescription;
    }

    public void setCatalogDescription(String catalogDescription) {
        this.catalogDescription = catalogDescription;
    }


    @Override
    public String toString() {
        return String.format("%-10s %-20s %-10s %-20s %-10s %-20s",
                "Mã danh mục: ", catalogId,
                "Tên danh mục: ", catalogName,
                "Mô tả: ", catalogDescription);
    }
}
