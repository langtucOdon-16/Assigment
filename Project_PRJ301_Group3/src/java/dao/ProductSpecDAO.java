/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import dto.ProductImageDTO;
import dto.ProductSpecDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NamTQ
 * Date: 14/02/2026
 * Description: Communicate with the database to use and update Product Specifications data.
 */
public class ProductSpecDAO extends DBContext{
    
    public List<ProductSpecDTO> getProductSpecs(int productId) {
        List<ProductSpecDTO> data = new ArrayList<>();
        try {
                String strSQL = "SELECT * "
                        + "FROM ProductSpec "
                        + "WHERE productId = ?;";
            PreparedStatement stm = connection.prepareStatement(strSQL);   
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();  
            
            while (rs.next()) {
                data.add(new ProductSpecDTO(
                        rs.getInt("specId"), 
                        rs.getNString("specName"),
                        rs.getNString("specValue"))
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getCategory: " + e.getMessage());
        }
        return data;
    }
}
