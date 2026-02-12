/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author NamTQ
 * Date: 11/02/2026
 * Description: Model represents Product Basic Information
 */
public class ProductBasicInformationDTO {
    
    private int productId;
    private String name;
    private String urlImage;
    private int quantity;
    private double originalPrice;
    private double afterDiscountPrice;
    private double discountRate;
    private CategoryDTO category;
    private BrandDTO brand;

    public ProductBasicInformationDTO() {
    }

    public ProductBasicInformationDTO(int productId, String name, String urlImage, int quantity, double originalPrice, double afterDiscountPrice, double discountRate, CategoryDTO category, BrandDTO brand) {
        this.productId = productId;
        this.name = name;
        this.urlImage = urlImage;
        this.quantity = quantity;
        this.originalPrice = originalPrice;
        this.afterDiscountPrice = afterDiscountPrice;
        this.discountRate = discountRate;
        this.category = category;
        this.brand = brand;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getAfterDiscountPrice() {
        return afterDiscountPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setAfterDiscountPrice(double afterDiscountPrice) {
        this.afterDiscountPrice = afterDiscountPrice;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

}
