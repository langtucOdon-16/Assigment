/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import dto.ProductImageDTO;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author NamTQ
 * Date: 08/02/2026
 * Description: Communicate with the database to use and update Product Image data.
 */
public class ProductImageDAO extends DBContext{
    
    public List<ProductImageDTO> getProductImages(int productId) {
        List<ProductImageDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT * "
                    + "FROM ProductImage "
                    + "WHERE productId = ?;";
            PreparedStatement stm = connection.prepareStatement(strSQL);   
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();  
            
            while (rs.next()) {
                data.add(new ProductImageDTO(
                        rs.getInt("imageId"),  
                        rs.getString("urlImage"))
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getProductImages: " + e.getMessage());
        }
        return data;
    }
}
