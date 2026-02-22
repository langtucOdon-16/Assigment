<%-- 
    Document   : product-card for all page
    Created on : Feb 21, 2026, 8:08:04 PM
    Author     : NamTQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="product-card">
    <a href="ProductDetailsServlet?productId=${product.productId}">
        <div class="product-image-wrapper">
            <img src="${pageContext.request.contextPath}/images/${product.urlImage}" alt="${product.name}" class="product-image">
            <c:if test="${product.discountRate > 0}">
                <div class="product-discount-badge">-${product.discountRate}%</div>
            </c:if>
        </div>
        <div class="product-info">
            <div class="product-name">${product.name}</div>
            <div class="product-meta">
                <div class="meta-item">${product.category.categoryName}</div>
                <div class="meta-item">${product.brand.brandName}</div>
            </div>
            <div class="product-pricing">
                <div class="product-quantity">Còn: ${product.quantity}</div>
                <div class="price-wrapper">
                    <c:if test="${product.discountRate > 0}">
                        <span class="product-original-price">
                            <fmt:formatNumber 
                            value="${product.originalPrice}" 
                            type="number" 
                            groupingUsed="true" 
                            maxFractionDigits="0"/>₫
                        </span>
                    </c:if>
                    <span class="product-price"> 
                        <fmt:formatNumber 
                            value="${product.afterDiscountPrice}" 
                            type="number" 
                            groupingUsed="true"
                            maxFractionDigits="0"/>₫
                    </span>  
                </div>
            </div>
        </div>
    </a>
    <a class="add-to-cart-btn">
        <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z"/>
        </svg>
        Thêm vào giỏ
    </a>
</div>
