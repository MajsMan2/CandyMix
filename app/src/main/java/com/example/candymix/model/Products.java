package com.example.candymix.model;


public class Products {

    Integer productId;
    String productName;
    String productSize;
    String productPrice;
    Integer imgUrl;


    public Products(Integer productId, String productName, String productSize, String productPrice, Integer imgUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productSize = productSize;
        this.productPrice = productPrice;
        this.imgUrl = imgUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Integer imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }


}
