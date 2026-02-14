<%-- 
    Document   : Home for computer web
    Created on : Feb 8, 2026, 8:55:05 AM
    Author     : NamTQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tài Lộc Store - Cửa hàng máy tính</title>
    <link rel="stylesheet" href="./css/home.css">
    <!-- Lucide Icons -->
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body>
    
    <jsp:include page="./common/header.jsp"/>
    
    <!-- Hero Banner Carousel -->
    <section class="hero-banner">
        <div class="carousel-container">
            <div class="carousel-wrapper" id="carouselWrapper">
                <!-- Slide 1 -->
                <div class="carousel-slide active">
                    <img src="https://cdn.tgdd.vn//News/1173869//23-730x408-1.jpg" alt="Laptop Gaming">
                    <div class="carousel-overlay"></div>
                    <div class="carousel-content">
                        <h2>Laptop Gaming Mới Nhất</h2>
                        <p>Giảm giá lên đến 30%</p>
                    </div>
                </div>
                <!-- Slide 2 -->
                <div class="carousel-slide">
                    <img src="https://tinhocanhphat.vn/media/product/25435_titan_c____m__n.jpg" alt="PC Gaming">
                    <div class="carousel-overlay"></div>
                    <div class="carousel-content">
                        <h2>PC Gaming Cao Cấp</h2>
                        <p>Cấu hình khủng - Chiến game mượt mà</p>
                    </div>
                </div>
                <!-- Slide 3 -->
                <div class="carousel-slide">
                    <img src="https://images.unsplash.com/photo-1719523676561-10af04209e8c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxsYXB0b3AlMjB1bHRyYWJvb2t8ZW58MXx8fHwxNzcwMDE2NTE0fDA&ixlib=rb-4.1.0&q=80&w=1080" alt="Laptop Văn Phòng">
                    <div class="carousel-overlay"></div>
                    <div class="carousel-content">
                        <h2>Laptop Văn Phòng</h2>
                        <p>Nhẹ - Mỏng - Hiệu suất cao</p>
                    </div>
                </div>
            </div>
            <!-- Navigation Arrows -->
            <button class="carousel-arrow carousel-prev" id="carouselPrev">
                <i data-lucide="chevron-left"></i>
            </button>
            <button class="carousel-arrow carousel-next" id="carouselNext">
                <i data-lucide="chevron-right"></i>
            </button>
            <!-- Dots -->
            <div class="carousel-dots" id="carouselDots"></div>
        </div>
    </section>

    <!-- Category Grid -->
    <section class="category-section">
        <div class="container">
            <h2 class="section-title">Danh mục nổi bật</h2>
            <div class="category-grid-main">
                <% int topCategory = 1; %>
                <c:forEach items="${featuredCategories}" var="category">
                    <a class="category-card">
                        <div class="category-card-icon">
                            <h3><%= topCategory %> </h3>                         
                        </div>
                        <div class="category-card-name">${category.categoryName}</div>
                        <div class="category-card-count">${category.numberOfProducts} sản phẩm</div>
                    </a>
                    <% topCategory++; %>        
                </c:forEach>                
            </div>
        </div>
    </section>

    <!-- Hot Deals Products -->
    <section class="product-section">
        <div class="container">
            <div class="section-header">
                <div>
                    <h2 class="section-title">⚡ Giảm giá sốc hôm nay</h2>
                    <p class="section-subtitle">Cập nhật mỗi ngày - Số lượng có hạn</p>
                </div>
                <button class="view-all-btn">
                    Xem tất cả
                    <i data-lucide="chevron-right"></i>
                </button>
            </div>
            <div class="product-slider-wrapper">
                <button class="slider-arrow prev" id="hotDealsPrev">
                    <i data-lucide="chevron-left"></i>
                </button>

                <div class="product-grid slider-mode" id="hotDealsGrid">
                    <c:forEach items="${top10HotDealProducts}" var="product">
                        <div class="product-card">
                            <a>
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
                    </c:forEach>
                </div>

                <button class="slider-arrow next" id="hotDealsNext">
                    <i data-lucide="chevron-right"></i>
                </button>
            </div>
        </div>
    </section>

    <!-- Hot Selling Products -->
    <section class="product-section bg-gray">
        <div class="container">
            <div class="section-header">
                <div>
                    <h2 class="section-title">💻 Sản phẩm bán chạy</h2>
                    <p class="section-subtitle">Laptop, PC, chuột, tàn phím, tai nghe chính hãng</p>
                </div>
                <button class="view-all-btn">
                    Xem tất cả
                    <i data-lucide="chevron-right"></i>
                </button>
            </div>
            <div class="product-slider-wrapper">
                <button class="slider-arrow prev" id="hotSellingPrev">
                    <i data-lucide="chevron-left"></i>
                </button>

                <div class="product-grid slider-mode" id="hotSellingGrid">
                    <c:forEach items="${top10SellingProducts}" var="product">
                        <div class="product-card">
                            <a>
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
                    </c:forEach>
                </div>

                <button class="slider-arrow next" id="hotSellingNext">
                    <i data-lucide="chevron-right"></i>
                </button>
            </div>  
        </div>
    </section>

    <!-- Service Highlights -->
    <section class="service-section">
        <div class="container">
            <div class="service-grid">
                <div class="service-card">
                    <div class="service-icon">
                        <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                        </svg>
                    </div>
                    <h3 class="service-title">Chính hãng 100%</h3>
                    <p class="service-text">Cam kết sản phẩm chính hãng</p>
                </div>
                <div class="service-card">
                    <div class="service-icon">
                        <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                        </svg>
                    </div>
                    <h3 class="service-title">Giá tốt nhất</h3>
                    <p class="service-text">Hoàn tiền nếu thấy rẻ hơn</p>
                </div>
                <div class="service-card">
                    <div class="service-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-truck-icon lucide-truck">
                            <path d="M14 18V6a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2v11a1 1 0 0 0 1 1h2"/><path d="M15 18H9"/>
                            <path d="M19 18h2a1 1 0 0 0 1-1v-3.65a1 1 0 0 0-.22-.624l-3.48-4.35A1 1 0 0 0 17.52 8H14"/>
                            <circle cx="17" cy="18" r="2"/><circle cx="7" cy="18" r="2"/>
                        </svg>  
                    </div>
                    <h3 class="service-title">Giao hàng tận nơi</h3>
                    <p class="service-text">Trên toàn quốc</p>
                </div>
                <div class="service-card">
                    <div class="service-icon">
                        <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z" />
                        </svg>
                    </div>
                    <h3 class="service-title">Bảo hành uy tín</h3>
                    <p class="service-text">Bảo hành tận nơi toàn quốc</p>
                </div>
            </div>
        </div>
    </section>
    
    <jsp:include page="./common/footer.jsp"/>

    <script src="./js/home.js"></script>
    <script>
        // Initialize Lucide icons
        lucide.createIcons();
    </script>
</body>
</html>
