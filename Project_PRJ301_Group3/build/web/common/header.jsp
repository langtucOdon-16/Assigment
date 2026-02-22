<%-- 
    Document   : header for all page
    Created on : Feb 12, 2026, 10:54:31 PM
    Author     : NamTQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Header -->
<header class="header">
    <div class="container">
        <div class="header-content">
            <!-- Logo -->
            <div class="logo">
                <a href="HomeServlet" class="logo-link">
                    <div class="logo-icon">
                        <i data-lucide="laptop"></i>
                    </div>
                    <div class="logo-text">
                        <div class="logo-title">Tài Lộc Store</div>
                        <div class="logo-subtitle">Cửa hàng máy tính</div>
                    </div>
                </a>
            </div>

            <!-- Category Menu Button -->
            <div class="category-menu-wrapper">
                <button class="category-btn" id="categoryBtn">
                    <i data-lucide="menu" id="menuIcon"></i>
                    <span class="category-text">Danh mục</span>
                </button>

                <!-- Category Dropdown -->
                <div class="category-dropdown" id="categoryDropdown">
                    <div class="category-dropdown-inner">
                        <h3 class="category-title">Danh mục sản phẩm</h3>
                        <div class="category-grid">
                            <c:forEach items="${categories}" var="category">
                                <a class="category-item" data-category-id="${category.categoryId}"
                                   href="SearchServlet?categoryIdName=${category.categoryId}">
                                    <span>${category.categoryName}</span>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <!-- Brand Dropdown -->
                <div class="brand-dropdown" id="brandDropdown">
                    <div class="brand-dropdown-inner">
                        <c:forEach items="${categories}" var="category">
                            <div class="brand-group" data-category-id="${category.categoryId}">
                                <h3 class="brand-title">Hãng sản phẩm</h3>
                                <div class="brand-grid">
                                    <c:forEach items="${brands[category.categoryId]}" var="brand">
                                        <a class="brand-item"
                                           href="SearchServlet?categoryIdName=${category.categoryId}&brandIdName=${brand.brandId}">
                                            <span>${brand.brandName}</span>
                                        </a>
                                    </c:forEach>
                                </div>  
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="category-overlay" id="categoryOverlay"></div>
            </div>

            <!-- Search Bar -->
            <div class="search-bar">
                <form action="SearchServlet" method="get">
                    <input 
                        type="text" 
                        placeholder="Bạn tìm gì hôm nay? (Laptop, PC, màn hình,...)"
                        class="search-input"
                        id="searchInput"
                        name="keyword"
                        value="${param.keyword}"
                    >
                    <button class="search-btn" type="submit">
                        <i data-lucide="search"></i>
                    </button>
                </form>  
            </div>

            <!-- User & Cart -->
            <div class="header-actions">
                <a class="action-btn cart-btn">
                    <i data-lucide="shopping-cart"></i>
                    <span>Giỏ hàng</span>
                    <span class="cart-badge">3</span>
                </a>
                <a class="action-btn">
                    <i data-lucide="user"></i>
                    <span>Tài khoản</span>
                </a>
            </div>
        </div>
    </div>
</header>
