/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author NamTQ
 * Date: 18/02/2026
 * Description: Model represents Category with Status checkbox
 */
public class CategoryWithStatusCheckboxDTO extends CategoryDTO{
    
    private boolean status;

    public CategoryWithStatusCheckboxDTO() {
    }

    public CategoryWithStatusCheckboxDTO(int categoryId, String categoryName, boolean status) {
        super(categoryId, categoryName);
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
 
}
