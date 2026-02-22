/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author NamTQ
 * Date: 20/02/2026
 * Description: Model represents Price Filters with Status Checkbox
 */
public class PriceFiltersWithStatusCheckboxDTO extends PriceFiltersDTO{
    
    private boolean status;

    public PriceFiltersWithStatusCheckboxDTO() {
    }

    public PriceFiltersWithStatusCheckboxDTO(int priceFiltersId, String label, double minPrice, double maxPrice, boolean status) {
        super(priceFiltersId, label, minPrice, maxPrice);
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
