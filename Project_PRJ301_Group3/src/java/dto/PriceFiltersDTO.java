/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author NamTQ
 * Date: 19/02/2026
 * Description: Model represents Price Filters
 */
public class PriceFiltersDTO {
    
    private int priceFiltersId;
    private String label;
    private double minPrice;
    private double maxPrice;

    public PriceFiltersDTO() {
    }

    public PriceFiltersDTO(int priceFiltersId, String label, double minPrice, double maxPrice) {
        this.priceFiltersId = priceFiltersId;
        this.label = label;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public int getPriceFiltersId() {
        return priceFiltersId;
    }

    public String getLabel() {
        return label;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setPriceFiltersId(int priceFiltersId) {
        this.priceFiltersId = priceFiltersId;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

}
