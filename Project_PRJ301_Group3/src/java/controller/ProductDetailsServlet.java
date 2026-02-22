/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.ProductDAO;
import dao.ProductImageDAO;
import dao.ProductSpecDAO;
import dto.ProductFullInformationDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author NamTQ
 * Date: 08/02/2026
 * Description: The Servlet coordinates the activities of the Product Details page.
 */
@WebServlet(name="ProductDetailsServlet", urlPatterns={"/ProductDetailsServlet"})
public class ProductDetailsServlet extends HttpServlet {
   
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
        
        String productIdString = request.getParameter("productId");
        
        loadProductInformation(request, productIdString);
        
        request.getRequestDispatcher("product-details.jsp").forward(request, response);
    } 
    
    private void loadProductInformation (HttpServletRequest request, String productIdString){  
        try {
            int productId = Integer.parseInt(productIdString);
            
            ProductDAO productDAO = new ProductDAO();
            ProductImageDAO productImageDAO = new ProductImageDAO();
            ProductSpecDAO productSpecDAO = new ProductSpecDAO();
            ProductFullInformationDTO productFullInformationDTO = productDAO.getProductFullInformation(productId);
            
            request.setAttribute("productFullInformation", productFullInformationDTO);
            request.setAttribute("productImages", productImageDAO.getProductImages(productId));
            request.setAttribute("productSpecs", productSpecDAO.getProductSpecs(productId));
            request.setAttribute("top10RelatedProducts", productDAO.getTop5RelatedProducts(
                    productId,
                    productFullInformationDTO.getCategory().getCategoryId(), 
                    productFullInformationDTO.getBrand().getBrandId()));
        } catch (NumberFormatException e) {
            System.out.println("loadProductFullInformation: " + e.getMessage());
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
