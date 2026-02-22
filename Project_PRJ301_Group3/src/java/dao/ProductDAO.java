/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import dto.BrandDTO;
import dto.CategoryDTO;
import dto.PriceFiltersDTO;
import dto.ProductBasicInformationDTO;
import dto.ProductFullInformationDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.StringUtils;

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
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getTop10HotDealProducts: " + e.getMessage());
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
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getTop10SellingProducts: " + e.getMessage());
        }
        return data;
    }
    
    public ProductFullInformationDTO getProductFullInformation(int productId) {
        try {
            String strSQL = "SELECT productId, name, urlImage, quantity, price, discountRate, P.categoryId, categoryName, P.brandId, brandName, color, releaseDate "
                    + "FROM Product P INNER JOIN Category C ON P.categoryId = C.categoryId "
                    + "INNER JOIN Brand B ON P.brandId = B.brandId "
                    + "WHERE productId = ?;";
            PreparedStatement stm = connection.prepareStatement(strSQL);   
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();  
            
            while (rs.next()) {
                double originalPrice = rs.getDouble("price");
                double discountRate = rs.getDouble("discountRate");
                return new ProductFullInformationDTO(
                        productId, 
                        rs.getNString("name"), 
                        rs.getString("urlImage"), 
                        rs.getInt("quantity"), 
                        originalPrice, 
                        originalPrice * (100 - discountRate) / 100, 
                        discountRate, 
                        new CategoryDTO(rs.getInt("categoryId"), rs.getNString("categoryName")), 
                        new BrandDTO(rs.getInt("brandId"), rs.getNString("brandName")), 
                        rs.getNString("color"), 
                        rs.getDate("releaseDate").toLocalDate());
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getProductFullInformation: " + e.getMessage());
        }
        return null;
    }
    
    public List<ProductBasicInformationDTO> getTop5RelatedProducts(int productId, int categoryId, int brandId){
        ArrayList<ProductBasicInformationDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT TOP 5 productId, name, urlImage, quantity, price, discountRate, P.categoryId, categoryName, P.brandId, brandName "
                    + "FROM Product P INNER JOIN Category C ON P.categoryId = C.categoryId "
                    + "INNER JOIN Brand B ON P.brandId = B.brandId "
                    + "WHERE productId != ? AND C.categoryId = ? "
                    + "ORDER BY CASE WHEN B.brandId = ? THEN 0 ELSE 1 END ASC, discountRate DESC;";
            PreparedStatement stm = connection.prepareStatement(strSQL);        
            stm.setInt( 1, productId);
            stm.setInt(2, categoryId);
            stm.setInt(3, brandId);
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
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getTop5RelatedProducts: " + e.getMessage());
        }
        return data;
    }
    
    private void buildSearchCondition(StringBuilder strSQL, String keyword, String[] categoryValues, String[] brandValues, String[] priceFiltersValues){
        strSQL.append("WHERE 1=1 ");
            
        if (keyword != null && !keyword.isEmpty()){
            strSQL.append("AND (name LIKE ? OR color LIKE ? OR categoryName LIKE ? OR brandName LIKE ?) ");
        }

        if (categoryValues != null && categoryValues.length != 0 && !StringUtils.contains(categoryValues, "-1")){
            int sizeCategory = categoryValues.length;
            strSQL.append("AND ");
            strSQL.append("(");
            for (int i = 0; i < sizeCategory; i++){
                strSQL.append("P.categoryId = ? ");
                if (i != sizeCategory - 1){
                    strSQL.append("OR ");
                }
            }
            strSQL.append(") ");
        }

        if (brandValues != null && brandValues.length != 0 && !StringUtils.contains(brandValues, "-1")){
            int sizeBrand = brandValues.length;
            strSQL.append("AND ");
            strSQL.append("(");
            for (int i = 0; i < sizeBrand; i++){
                strSQL.append("P.brandId = ? ");
                if (i != sizeBrand - 1){
                    strSQL.append("OR ");
                }
            }
            strSQL.append(") ");
        }

        if (priceFiltersValues != null && priceFiltersValues.length != 0 && !StringUtils.contains(priceFiltersValues, "-1")){
            int sizePriceFilters = priceFiltersValues.length;
            strSQL.append("AND ");
            strSQL.append("(");
            for (int i = 0; i < sizePriceFilters; i++){
                strSQL.append("(price * (100 - discountRate) / 100 >= ? AND ");
                strSQL.append("price * (100 - discountRate) / 100 <= ? ) ");
                if (i != sizePriceFilters - 1){
                    strSQL.append("OR ");
                }
            }
            strSQL.append(") ");
        }
    }
    
    private int setParameterMarkerCondition(PreparedStatement stm, int count, String keyword, String[] categoryValues, String[] brandValues, String[] priceFiltersValues) throws SQLException{
        if (keyword != null && !keyword.isEmpty()){
            for (int i = 0; i < 4; i++){
                stm.setString(count++, "%" + keyword + "%");
            }
        }

        if (categoryValues != null && categoryValues.length != 0 && !StringUtils.contains(categoryValues, "-1")){
            for (String str : categoryValues){
                stm.setInt(count++, Integer.parseInt(str));
            }
        }

        if (brandValues != null && brandValues.length != 0 && !StringUtils.contains(brandValues, "-1")){
            for (String str : brandValues){
                stm.setInt(count++, Integer.parseInt(str));
            }
        }

        if (priceFiltersValues != null && priceFiltersValues.length != 0 && !StringUtils.contains(priceFiltersValues, "-1")){
            PriceFiltersDAO priceFiltersDAO = new PriceFiltersDAO();
            for (String str : priceFiltersValues){
                PriceFiltersDTO priceFiltersDTO = priceFiltersDAO.getPriceFiltersByPriceFiltersId(Integer.parseInt(str));
                stm.setDouble(count++, priceFiltersDTO.getMinPrice());
                if (priceFiltersDTO.getMaxPrice() == 0){
                    stm.setDouble(count++, Double.MAX_VALUE);
                }else {
                    stm.setDouble(count++, priceFiltersDTO.getMaxPrice());
                } 
            }
        }
        return count;
    }
    
    public int getTotalSearchProduct(String keyword, String[] categoryValues, String[] brandValues, String[] priceFiltersValues){
        ArrayList<ProductBasicInformationDTO> data = new ArrayList<>();
        try {    
            
            int count = 1;
            
            StringBuilder strSQL = new StringBuilder("SELECT COUNT(*) AS totalProduct FROM Product P ");
            strSQL.append("INNER JOIN Category C ON P.categoryId = C.categoryId ");
            strSQL.append("INNER JOIN Brand B ON B.brandId = P.brandId ");
            
            buildSearchCondition(strSQL, keyword, categoryValues, brandValues, priceFiltersValues);
     
            PreparedStatement stm = connection.prepareStatement(strSQL.toString()); 
            
            count = setParameterMarkerCondition(stm, count, keyword, categoryValues, brandValues, priceFiltersValues);
            
            ResultSet rs = stm.executeQuery(); 
            
            while (rs.next()) {
                return rs.getInt("totalProduct");
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getTotalSearchProduct: " + e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("getTotalSearchProduct: " + e.getMessage());
        }
        return -1;
    }
    
    public ArrayList<ProductBasicInformationDTO> getSearchProduct(String keyword, String[] categoryValues, String[] brandValues, String[] priceFiltersValues, String sortingType, int currentPage){
        ArrayList<ProductBasicInformationDTO> data = new ArrayList<>();
        try {        
            int count = 1;
            
            StringBuilder strSQL = new StringBuilder("SELECT * FROM Product P ");
            strSQL.append("INNER JOIN Category C ON P.categoryId = C.categoryId ");
            strSQL.append("INNER JOIN Brand B ON B.brandId = P.brandId ");
            
            buildSearchCondition(strSQL, keyword, categoryValues, brandValues, priceFiltersValues);
            
            if (sortingType == null){
                strSQL.append("ORDER BY discountRate DESC ");
            }else {
                switch (sortingType) {
                    case "decreaseDiscount":
                        strSQL.append("ORDER BY discountRate DESC ");
                        break;
                    case "increasingPrice":
                        strSQL.append("ORDER BY price ASC ");
                        break;
                    case "decreasePrice":
                        strSQL.append("ORDER BY price DESC ");
                        break;
                    default:
                        strSQL.append("ORDER BY discountRate DESC ");
                        break;
                }
            }
            
            strSQL.append("OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");
     
            PreparedStatement stm = connection.prepareStatement(strSQL.toString()); 
            
            count = setParameterMarkerCondition(stm, count, keyword, categoryValues, brandValues, priceFiltersValues);
            
            stm.setInt(count++, (currentPage - 1) * 20);
            stm.setInt(count++, 20);

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
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getSearchProduct: " + e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("getSearchProduct: " + e.getMessage());
        }
        return data;
    }
}
