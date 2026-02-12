/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import dto.BrandDTO;
import dto.CategoryDTO;
import dto.ProductBasicInformationDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NamTQ
 * Date: 08/02/2026
 * Description: Communicate with the database to use and update Product data.
 */
public class ProductDAO extends DBContext{
    
    public List<ProductBasicInformationDTO> getTop10HotDealProducts(){
        ArrayList<ProductBasicInformationDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT TOP 10 productId, name, urlImage, quantity, price, discountRate, P.categoryId, categoryName, P.brandId, brandName "
                    + "FROM Product P INNER JOIN Category C ON P.categoryId = C.categoryId "
                    + "INNER JOIN Brand B ON P.brandId = B.brandId "
                    + "WHERE discountRate > 0 "
                    + "ORDER BY discountRate DESC;";
            PreparedStatement stm = connection.prepareStatement(strSQL);       
            ResultSet rs = stm.executeQuery();  
            
            while (rs.next()) {
                double originalPrice = rs.getDouble("price");
                double discountRate = rs.getDouble("discountRate");
                data.add(new ProductBasicInformationDTO(
                        rs.getInt("productId"), 
                        rs.getNString("name"), 
                        rs.getString("urlImage"), 
                        rs.getInt("quantity"),
                        originalPrice, 
                        originalPrice * (100 - discountRate) / 100,
                        discountRate,
                        new CategoryDTO(rs.getInt("categoryId"), rs.getNString("categoryName")),
                        new BrandDTO(rs.getInt("brandId"), rs.getNString("brandName")))
                );
            }
        } catch (SQLException e) {
            System.out.println("getCategory: " + e.getMessage());
        }
        return data;
    }
    
    public List<ProductBasicInformationDTO> getTop10SellingProducts(){
        ArrayList<ProductBasicInformationDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT * FROM top10_selling_products_view;";
            PreparedStatement stm = connection.prepareStatement(strSQL);       
            ResultSet rs = stm.executeQuery();  
            
            while (rs.next()) {
                double originalPrice = rs.getDouble("price");
                double discountRate = rs.getDouble("discountRate");
                data.add(new ProductBasicInformationDTO(
                        rs.getInt("productId"), 
                        rs.getNString("name"), 
                        rs.getString("urlImage"), 
                        rs.getInt("quantity"),
                        originalPrice, 
                        originalPrice * (100 - discountRate) / 100,
                        discountRate,
                        new CategoryDTO(rs.getInt("categoryId"), rs.getNString("categoryName")),
                        new BrandDTO(rs.getInt("brandId"), rs.getNString("brandName")))
                );
            }
        } catch (SQLException e) {
            System.out.println("getCategory: " + e.getMessage());
        }
        return data;
    }
}
