<%-- 
    Document   : search for computer web
    Created on : Feb 8, 2026, 8:58:07 AM
    Author     : NamTQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:url var="changePage" value="SearchServlet">

    <c:param name="keyword" value="${param.keyword}" />
    
    <c:param name="sortingType" value="${param.sortingType}" />

    <c:forEach items="${paramValues.categoryIdName}" var="c">
        <c:param name="categoryIdName" value="${c}" />
    </c:forEach>

    <c:forEach items="${paramValues.brandIdName}" var="b">
        <c:param name="brandIdName" value="${b}" />
    </c:forEach>

    <c:forEach items="${paramValues.priceIdName}" var="p">
        <c:param name="priceIdName" value="${p}" />
    </c:forEach>

</c:url>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tìm kiếm - Tài Lộc Store</title>
    <link rel="stylesheet" href="./css/home.css">
    <link rel="stylesheet" href="./css/search.css">
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body>
    <!-- Header -->
    <jsp:include page="./common/header.jsp"/>

    <!-- Search Results -->
    <main class="search-results-main">
        <div class="container">
            <!-- Search Results Layout -->
            <div class="search-layout">
                <!-- Sidebar Filters -->
                <aside class="search-sidebar">
                    <form action="SearchServlet" method="get">
                        <input 
                            type="hidden" 
                            name="keyword"
                            value="${param.keyword}"
                        >
                        <div class="filter-section">
                            <div class="filter-toggle" id="filterToggle">
                                <i data-lucide="sliders-horizontal"></i>
                                <span>Bộ lọc tìm kiếm</span>
                            </div>
                        </div>

                        <!-- Sort Filter-->
                        <div class="filter-group">
                            <div class="filter-header">
                                <h3>Sắp xếp</h3>
                                <button class="filter-collapse" data-target="sortFilter"  type="button">
                                    <i data-lucide="chevron-up"></i>
                                </button>
                            </div>
                            <div class="filter-content" id="sortFilter">
                                <div class="radioes">
                                    <label class="sort-radio-item">
                                        <input 
                                            type="radio" 
                                            name="sortingType" 
                                            value="decreaseDiscount" 
                                            ${sortingType == 'decreaseDiscount' ? 'checked' : ''}
                                        >
                                        <span class="radio-label">Giảm giá giảm dần</span>
                                    </label>
                                    <label class="sort-radio-item">
                                        <input 
                                            type="radio" 
                                            name="sortingType" 
                                            value="increasingPrice" 
                                            ${sortingType == 'increasingPrice' ? 'checked' : ''}
                                        >
                                        <span class="radio-label">Giá tăng dần</span>
                                    </label>
                                    <label class="sort-radio-item">
                                        <input 
                                            type="radio" 
                                            name="sortingType" 
                                            value="decreasePrice" 
                                            ${sortingType == 'decreasePrice' ? 'checked' : ''}
                                        >
                                        <span class="radio-label">Giá giảm dần</span>
                                    </label>
                                </div>    
                            </div>
                        </div>
                        
                        <!-- Category Filter -->
                        <div class="filter-group">
                            <div class="filter-header">
                                <h3>Danh mục sản phẩm</h3>
                                <button class="filter-collapse" data-target="categoryFilter"  type="button">
                                    <i data-lucide="chevron-up"></i>
                                </button>
                            </div>
                            <div class="filter-content" id="categoryFilter">
                                <div class="checkboxes">
                                    <c:forEach items="${allCategory}" var="category">
                                        <label class="category-checkbox-item">
                                            <input 
                                                type="checkbox" 
                                                name="categoryIdName" 
                                                value="${category.categoryId}" 
                                                ${category.status ? 'checked' : ''}
                                            >
                                            <span class="checkbox-label">${category.categoryName}</span>
                                        </label>
                                    </c:forEach> 
                                </div>    
                            </div>
                        </div>

                        <!-- Brand Filter -->
                        <div class="filter-group">
                            <div class="filter-header">
                                <h3>Hãng sản xuất</h3>
                                <button class="filter-collapse" data-target="brandFilter" type="button">
                                    <i data-lucide="chevron-up"></i>
                                </button>
                            </div>
                            <div class="filter-content" id="brandFilter">
                                <div class="checkboxes">
                                    <c:forEach items="${allBrand}" var="brand">
                                        <label class="brand-checkbox-item">
                                            <input 
                                                type="checkbox" 
                                                name="brandIdName" 
                                                value="${brand.brandId}"
                                                ${brand.status ? 'checked' : ''}
                                            >
                                            <span class="checkbox-label">${brand.brandName}</span>
                                        </label>
                                    </c:forEach> 
                                </div>  
                            </div>
                        </div>

                        <!-- Price Filter -->
                        <div class="filter-group">
                            <div class="filter-header">
                                <h3>Mức giá</h3>
                                <button class="filter-collapse" data-target="priceFilter" type="button">
                                    <i data-lucide="chevron-up"></i>
                                </button>
                            </div>
                            <div class="filter-content" id="priceFilter">
                                <div class="checkboxes">
                                    <c:forEach items="${allPriceFilters}" var="priceFilters">
                                        <label class="price-checkbox-item">
                                            <input 
                                                type="checkbox" 
                                                name="priceIdName" 
                                                value="${priceFilters.priceFiltersId}"
                                                ${priceFilters.status ? 'checked' : ''}
                                            >
                                            <span class="checkbox-label">${priceFilters.label}</span>
                                        </label>
                                    </c:forEach> 
                                </div>
                            </div>
                        </div>
                        
                        <!-- Submit Button -->
                        <div>
                            <button class="filter-submit-btn" type="submit">
                                <span class="filter-submit-text">Áp dụng</span>
                            </button>
                        </div>  
                    </form>
                </aside>

                <!-- Results Content -->
                <div class="search-content">
                    <!-- Results Header -->
                    <div class="results-header">
                        <div class="results-info">
                            Tìm thấy <strong>${numberOfResult} kết quả</strong> với từ khóa <strong>"${param.keyword}"</strong>
                        </div>
                    </div>

                    <!-- Product Grid -->
                    <div class="search-results-grid" id="searchResultsGrid">
                        <c:forEach items="${searchProduct}" var="product">
                            <%@ include file="./common/product-card.jsp" %>
                        </c:forEach>
                    </div>

                    <!-- Pagination -->
                    <div class="pagination-wrapper">
                        <div class="pagination">
                            <a href="${changePage}&currentPage=${currentPage - 1}" class="pagination-btn-link">
                                <button class="pagination-btn pagination-prev" ${currentPage == 1 ? 'disabled' : ''}>
                                    <i data-lucide="chevron-left"></i>
                                    <span>Trước</span>
                                </button>
                            </a>
                            
                            
                            <div class="pagination-numbers">
                                <c:forEach items="${numberPageList}" var="page">
                                    <a class="pagination-number ${page == currentPage ? 'active' : ''}" 
                                       href="${changePage}&currentPage=${page}">${page}</a>
                                </c:forEach>
                            </div>
                            
                            <a href="${changePage}&currentPage=${currentPage + 1}" class="pagination-btn-link">
                                <button class="pagination-btn pagination-next" ${currentPage == numberOfPage ? 'disabled' : ''}>
                                     <span>Tiếp</span>
                                     <i data-lucide="chevron-right"></i>
                                </button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <!-- Footer -->
    <jsp:include page="./common/footer.jsp"/>

    <script src="./js/home.js"></script>
    <script src="./js/search.js"></script>
    <script>
        lucide.createIcons();
    </script>
</body>
</html>
