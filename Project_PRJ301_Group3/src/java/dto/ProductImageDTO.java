/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author NamTQ
 * Date: 13/02/2026
 * Description: Model represents Product Image.
 */

public class ProductImageDTO {
    
    private int imageId;
    private String urlImage;

    public ProductImageDTO() {
    }

    public ProductImageDTO(int imageId, String urlImage) {
        this.imageId = imageId;
        this.urlImage = urlImage;
    }

    public int getImageId() {
        return imageId;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    
}
