/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.BrandDAO;
import dao.CategoryDAO;
import dao.PriceFiltersDAO;
import dao.ProductDAO;
import dto.BrandWithStatusCheckboxDTO;
import dto.CategoryWithStatusCheckboxDTO;
import dto.PriceFiltersWithStatusCheckboxDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import utils.StringUtils;

/**
 *
 * @author NamTQ
 * Date: 08/02/2026
 * Description: The Servlet coordinates the activities of the Search page.
 */
@WebServlet(name="SearchServlet", urlPatterns={"/SearchServlet"})
public class SearchServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String keyword = request.getParameter("keyword");
        String[] categoryValues = request.getParameterValues("categoryIdName");
        String[] brandValues = request.getParameterValues("brandIdName");
        String[] priceFiltersValues = request.getParameterValues("priceIdName");
        String sortingType = request.getParameter("sortingType");
        String page = request.getParameter("currentPage");
        
        loadAllCategoryCheckbox(request, categoryValues);
        loadAllBrandCheckbox(request, brandValues);
        loadAllPriceFiltersCheckbox(request, priceFiltersValues);
        loadAllSortRadio(request, sortingType);
        loadSearchProduct(request, keyword, categoryValues, brandValues, priceFiltersValues, sortingType, page);
        
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
    
    private void loadAllCategoryCheckbox (HttpServletRequest request, String[] categoryValues){
        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<CategoryWithStatusCheckboxDTO> categoryList = categoryDAO.getAllCategoryWithStatusCheckbox();
        
        if (categoryValues == null || categoryValues.length == 0){
            categoryList.get(0).setStatus(true);
        }else {
            for (CategoryWithStatusCheckboxDTO category : categoryList){
                if (StringUtils.contains(categoryValues, category.getCategoryId() + "")){
                    category.setStatus(true);
                }
            }
        }

        request.setAttribute("allCategory", categoryList);
    }
    
    private void loadAllBrandCheckbox (HttpServletRequest request, String[] brandValues){
        BrandDAO brandDAO = new BrandDAO();
        ArrayList<BrandWithStatusCheckboxDTO> brandList = brandDAO.getAllBrandWithStatusCheckbox();

        if (brandValues == null || brandValues.length == 0){
            brandList.get(0).setStatus(true);
        }else {
            for (BrandWithStatusCheckboxDTO brand : brandList){
                if (StringUtils.contains(brandValues, brand.getBrandId() + "")){
                    brand.setStatus(true);
                }
            }
        }
        
        request.setAttribute("allBrand", brandList);
    }
    
    private void loadAllPriceFiltersCheckbox (HttpServletRequest request, String[] priceFiltersValues){
        PriceFiltersDAO priceFiltersDAO = new PriceFiltersDAO();
        ArrayList<PriceFiltersWithStatusCheckboxDTO> priceFiltersList = priceFiltersDAO.getAllPriceFiltersWithStatusCheckbox();
        
        if (priceFiltersValues == null || priceFiltersValues.length == 0){
            priceFiltersList.get(0).setStatus(true);
        }else {
            for (PriceFiltersWithStatusCheckboxDTO priceFilters : priceFiltersList){
                if (StringUtils.contains(priceFiltersValues, priceFilters.getPriceFiltersId() + "")){
                    priceFilters.setStatus(true);
                }
            }
        }
        
        request.setAttribute("allPriceFilters", priceFiltersList);
    }
    
    private  void loadAllSortRadio (HttpServletRequest request, String sortingType){
        if (sortingType == null || sortingType.isEmpty()){
            request.setAttribute("sortingType", "decreaseDiscount");
        }else {
            request.setAttribute("sortingType", sortingType);
        }
    }
    
    private void loadSearchProduct (HttpServletRequest request, String keyword, String[] categoryValues, String[] brandValues, String[] priceFiltersValues, String sortingType, String page){
        try{
            ProductDAO productDAO = new ProductDAO();
            int totalProduct = productDAO.getTotalSearchProduct(keyword, categoryValues, brandValues, priceFiltersValues);
            int numberOfPage = totalProduct / 20 + 1;
            int currentPage = 1;
            ArrayList<Integer> pageList = new ArrayList<>();
            
            if (page != null && !page.isEmpty()){
                currentPage = Integer.parseInt(page);
            }
            
            if (currentPage - 2 < 1){
                for (int i = 1; i <= 5; i++){
                    if (i <= numberOfPage){
                        pageList.add(i);
                    }
                }
            }else if (currentPage + 2 > numberOfPage){
                for (int i = numberOfPage - 5; i <= numberOfPage; i++){
                    if (i >= 1){
                        pageList.add(i);
                    }
                }
            }else {
                for (int i = currentPage - 2; i <= currentPage + 2; i++){
                    pageList.add(i);
                }
            }
            
            request.setAttribute("numberPageList", pageList);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("numberOfPage", numberOfPage);
            request.setAttribute("numberOfResult", totalProduct);
            request.setAttribute("searchProduct", productDAO.getSearchProduct(keyword, categoryValues, brandValues, priceFiltersValues, sortingType, currentPage));
        }
        catch (NullPointerException e){
            System.out.println("loadSearchProduct: " + e.getMessage());
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
