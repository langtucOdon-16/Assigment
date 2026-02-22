/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import dto.BrandDTO;
import dto.BrandWithStatusCheckboxDTO;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author NamTQ
 * Date: 08/02/2026
 * Description: Communicate with the database to use and update Brand data.
 */
public class BrandDAO extends DBContext{
    
    public ArrayList<BrandDTO> getBrandsByCategoryId(int categoryId) {
        ArrayList<BrandDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT DISTINCT B.brandId, brandName " 
                    + "FROM Brand B INNER JOIN Product P "
                    + "ON B.brandId = P.brandId AND categoryId = ? "
                    + "ORDER BY brandName ASC;";
            PreparedStatement stm = connection.prepareStatement(strSQL); 
            stm.setInt(1, categoryId);
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new BrandDTO(
                        rs.getInt("brandId"), 
                        rs.getNString("brandName"))
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getBrandByCategoryId: " + e.getMessage());
        }
        return data;
    }
    
    public ArrayList<BrandDTO> getAllBrand(){
        ArrayList<BrandDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT * " 
                    + "FROM Brand "
                    + "ORDER BY brandName ASC; ";
            PreparedStatement stm = connection.prepareStatement(strSQL); 
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new BrandDTO(
                        rs.getInt("brandId"), 
                        rs.getNString("brandName"))
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getAllBrand: " + e.getMessage());
        }
        return data;
    }
    
    public  ArrayList<BrandWithStatusCheckboxDTO> getAllBrandWithStatusCheckbox (){
        ArrayList<BrandWithStatusCheckboxDTO> data = new ArrayList<>();
        data.add(new BrandWithStatusCheckboxDTO(-1, "Tất cả", false));
        try {
            String strSQL = "SELECT * FROM Brand ORDER BY brandName ASC;";
            PreparedStatement stm = connection.prepareStatement(strSQL); 
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new BrandWithStatusCheckboxDTO(
                        rs.getInt("brandId"), 
                        rs.getNString("brandName"),
                        false)
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getAllBrandWithStatusCheckbox: " + e.getMessage());
        }
        return data;
    }
    
}
