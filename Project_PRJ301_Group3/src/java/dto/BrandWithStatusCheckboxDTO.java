/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author NamTQ
 * Date: 19/02/2026
 * Description: Model represents Brand with Status checkbox
 */
public class BrandWithStatusCheckboxDTO extends BrandDTO{
    
    private boolean status;

    public BrandWithStatusCheckboxDTO() {
    }

    public BrandWithStatusCheckboxDTO(int brandId, String brandName, boolean status) {
        super(brandId, brandName);
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
