/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author NamTQ
 * Date: 13/02/2026
 * Description: Model represents Product Specifications.
 */
public class ProductSpecDTO {
    
    private int specId;
    private String specName;
    private String specValue;

    public ProductSpecDTO() {
    }

    public ProductSpecDTO(int specId, String specName, String specValue) {
        this.specId = specId;
        this.specName = specName;
        this.specValue = specValue;
    }

    public int getSpecId() {
        return specId;
    }

    public String getSpecName() {
        return specName;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }
    
}
