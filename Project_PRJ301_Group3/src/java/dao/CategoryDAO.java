/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import dto.CategoryDTO;
import dto.CategoryWithCountDTO;
import dto.CategoryWithStatusCheckboxDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author NamTQ
 * Date: 08/02/2026
 * Description: Communicate with the database to use and update Category data.
 */
public class CategoryDAO extends DBContext{
    
    public ArrayList<CategoryDTO> getAllCategory() {
        ArrayList<CategoryDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT * FROM Category ORDER BY categoryName ASC;";
            PreparedStatement stm = connection.prepareStatement(strSQL);       
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new CategoryDTO(
                    rs.getInt("categoryId"), 
                    rs.getNString("categoryName"))
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getAllCategory: " + e.getMessage());
        }
        return data;
    }
    
    public ArrayList<CategoryWithCountDTO> getFeaturedCategories() {
        ArrayList<CategoryWithCountDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT * FROM featured_categories_view;";
            PreparedStatement stm = connection.prepareStatement(strSQL);       
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new CategoryWithCountDTO(
                    rs.getInt("categoryId"), 
                    rs.getNString("categoryName"),
                    rs.getInt("numberOfProducts"))
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getFeaturedCategories: " + e.getMessage());
        }
        return data;
    }
    
    public ArrayList<CategoryWithStatusCheckboxDTO> getAllCategoryWithStatusCheckbox() {
        ArrayList<CategoryWithStatusCheckboxDTO> data = new ArrayList<>();
        data.add(new CategoryWithStatusCheckboxDTO(-1, "Tất cả", false));
        try {
            String strSQL = "SELECT * FROM Category ORDER BY categoryName ASC;";
            PreparedStatement stm = connection.prepareStatement(strSQL);       
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new CategoryWithStatusCheckboxDTO(
                    rs.getInt("categoryId"), 
                    rs.getNString("categoryName"),
                    false)
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getAllCategoryWithStatusCheckbox: " + e.getMessage());
        }
        return data;
    }
}
