<%-- 
    Document   : product-detail for computer web
    Created on : Feb 8, 2026, 8:57:27 AM
    Author     : NamTQ
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm - Tài Lộc Store</title>
    <link rel="stylesheet" href="./css/home.css">
    <link rel="stylesheet" href="./css/product-details.css">
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body>
    
    <jsp:include page="./common/header.jsp"/>

    <!-- Breadcrumb -->
    <div class="breadcrumb-wrapper">
        <div class="container">
            <nav class="breadcrumb">
                <a href="HomeServlet">Trang chủ</a>
                <i data-lucide="chevron-right"></i>
                <a href="SearchServlet?categoryIdName=${productFullInformation.category.categoryId}">${productFullInformation.category.categoryName}</a>
                <i data-lucide="chevron-right"></i>
                <a href="SearchServlet?categoryIdName=${productFullInformation.category.categoryId}&brandIdName=${productFullInformation.brand.brandId}">${productFullInformation.brand.brandName}</a>
                <i data-lucide="chevron-right"></i>
                <span>${productFullInformation.name}</span>
            </nav>
        </div>
    </div>
            
    <script>
        const galleryImages = [
            <c:forEach items="${productImages}" var="image" varStatus="st">
                    "${pageContext.request.contextPath}/images/${image.urlImage}"<c:if test="${!st.last}">, </c:if>
            </c:forEach>
        ];
    </script>

    <!-- Product Detail -->
    <main class="product-detail-main">
        <div class="container">
            <div class="product-detail-grid">
                <!-- Left Column - Images -->
                <div class="product-gallery">
                    <div class="main-image-wrapper">
                        <img 
                            src="" 
                            alt="${productFullInformation.name}" 
                            class="main-image"
                            id="mainImage"
                        >
                        <button class="gallery-nav gallery-prev" id="galleryPrev">
                            <i data-lucide="chevron-left"></i>
                        </button>
                        <button class="gallery-nav gallery-next" id="galleryNext">
                            <i data-lucide="chevron-right"></i>
                        </button>
                    </div>
                    
                    <!-- Thumbnail Gallery -->
                    <div class="thumbnail-gallery" id="thumbnailGallery">
                        <c:forEach items="${productImages}" var="image" varStatus="st">
                            <button class="thumbnail active" data-index="0">
                                <img src="${pageContext.request.contextPath}/images/${image.urlImage}" alt="${image.urlImage}">
                            </button>
                        </c:forEach>
                    </div>
                </div>

                <!-- Right Column - Product Info -->
                <div class="product-info-panel">
                    <!-- Product Title -->
                    <h1 class="product-detail-title">
                        ${productFullInformation.name}
                    </h1>
                    <div class="product-quantity">Còn: ${productFullInformation.quantity}</div>

                    <!-- Pricing -->
                    <div class="product-pricing-detail">
                        <div class="price-main">
                            <c:if test="${productFullInformation.discountRate > 0}">
                                <span class="original-price">
                                    <fmt:formatNumber 
                                    value="${productFullInformation.originalPrice}" 
                                    type="number" 
                                    groupingUsed="true" 
                                    maxFractionDigits="0"/>₫
                                </span>
                            </c:if>
                            <span class="current-price">
                                <fmt:formatNumber 
                                value="${productFullInformation.afterDiscountPrice}" 
                                type="number" 
                                groupingUsed="true"
                                maxFractionDigits="0"/>₫
                            </span>
                            <c:if test="${productFullInformation.discountRate > 0}">
                                <span class="discount-percent">${productFullInformation.discountRate}%</span>
                            </c:if>  
                        </div>
                    </div>

                    <!-- Action Buttons -->
                    <div class="product-actions">
                        <a class="btn-buy-now">
                            MUA NGAY
                        </a>
                        <a class="btn-add-cart">
                            THÊM VÀO GIỎ
                        </a>
                    </div>

                    <!-- Policies -->
                    <div class="product-more-information">
                        <div class="information-item">
                            <i data-lucide="palette"></i>
                            <span>Màu: ${productFullInformation.color}</span>
                        </div>
                        <div class="information-item">
                            <i data-lucide="calendar-days"></i>
                            <span>Ngày phát hành: ${productFullInformation.releaseDate}</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Product Specifications -->
            <div class="product-specs-section">
                <div class="specs-header">
                    <h2>Thông số nổi bật</h2>
                </div>
                <div class="specs-grid">
                    <c:forEach items="${productSpecs}" var="spec">
                        <div class="spec-card">
                            <div class="spec-info">
                                <div class="spec-label">${spec.specName}</div>
                                <div class="spec-value">${spec.specValue}</div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- Related Products -->
            <div class="related-products-section">
                <h2 class="section-title">Sản phẩm tương tự</h2>
                <div class="product-grid" id="relatedProducts">
                    <c:forEach items="${top10RelatedProducts}" var="product">
                        <%@ include file="./common/product-card.jsp" %>
                    </c:forEach>                    
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="./common/footer.jsp"/>

    <script src="./js/home.js"></script>
    <script src="./js/product-details.js"></script>
    <script>
        lucide.createIcons();
    </script>
</body>
</html>
