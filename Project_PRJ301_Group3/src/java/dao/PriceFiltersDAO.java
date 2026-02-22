/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import dto.PriceFiltersDTO;
import dto.PriceFiltersWithStatusCheckboxDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author NamTQ
 * Date: 20/02/2026
 * Description: Communicate with the database to use and update Price Filters data.
 */
public class PriceFiltersDAO extends DBContext{
    
    public ArrayList<PriceFiltersWithStatusCheckboxDTO> getAllPriceFiltersWithStatusCheckbox() {
        ArrayList<PriceFiltersWithStatusCheckboxDTO> data = new ArrayList<>();
        data.add(new PriceFiltersWithStatusCheckboxDTO(-1, "Tất cả", 0, 0, false));
        try {
            String strSQL = "SELECT * FROM PriceFilters ORDER BY minPrice ASC;";
            PreparedStatement stm = connection.prepareStatement(strSQL); 
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new PriceFiltersWithStatusCheckboxDTO(
                        rs.getInt("priceFiltersId"), 
                        rs.getNString("label"), 
                        rs.getDouble("minPrice"), 
                        rs.getDouble("maxPrice"), 
                        false));
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getAllPriceFiltersWithStatusCheckbox: " + e.getMessage());
        }
        return data;
    }
    
    public PriceFiltersDTO getPriceFiltersByPriceFiltersId(int priceFiltersId) {
        try {
            String strSQL = "SELECT * FROM PriceFilters WHERE priceFiltersId = ?;";
            PreparedStatement stm = connection.prepareStatement(strSQL); 
            stm.setInt(1, priceFiltersId);
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                return new PriceFiltersDTO(priceFiltersId, rs.getNString("label"), rs.getDouble("minPrice"), rs.getDouble("maxPrice"));
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getPriceFiltersByPriceFiltersId: " + e.getMessage());
        }
        return null;
    }
}
