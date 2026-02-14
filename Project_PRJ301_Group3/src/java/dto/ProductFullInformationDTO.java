/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;

/**
 *
 * @author NamTQ
 * Date: 13/02/2026
 * Description: Model represents Product Full Information
 */
public class ProductFullInformationDTO extends ProductBasicInformationDTO {
    
    private String color;
    private LocalDate releaseDate;

    public ProductFullInformationDTO() {
    }

    public ProductFullInformationDTO(int productId, String name, String urlImage, int quantity, double originalPrice, double afterDiscountPrice, double discountRate, CategoryDTO category, BrandDTO brand, String color, LocalDate releaseDate) {
        super(productId, name, urlImage, quantity, originalPrice, afterDiscountPrice, discountRate, category, brand);
        this.color = color;
        this.releaseDate = releaseDate;
    }

    public String getColor() {
        return color;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    
}
