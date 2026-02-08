# TechStore - Website Bán Máy Tính

Trang web bán máy tính (PC, Laptop) và phụ kiện công nghệ với thiết kế hiện đại, tông màu đỏ trắng.

## Cấu trúc Project

```
project/
├── index.html          # File HTML chính
├── styles.css          # File CSS cho giao diện
├── script.js           # File JavaScript cho tương tác
└── README.md           # Tài liệu hướng dẫn
```

## Tính năng

### 1. Header (Thanh điều hướng)
- Logo TechStore
- Menu danh mục sản phẩm dạng lưới popup
- Ô tìm kiếm lớn ở giữa
- Nút tài khoản và giỏ hàng
- Sticky header (dính khi cuộn trang)
- Responsive design

### 2. Hero Banner Carousel
- Carousel tự động trượt
- 3 slides quảng cáo sản phẩm
- Nút điều hướng trái/phải
- Dots indicator
- Auto-play 4 giây/slide
- Pause khi hover

### 3. Danh mục nổi bật
- Grid hiển thị 8 danh mục chính
- Icons cho từng danh mục
- Số lượng sản phẩm
- Hover effects

### 4. Sections Sản phẩm
- **Flash Sale**: Sản phẩm giảm giá sốc
- **Laptop bán chạy**: Laptop gaming, văn phòng
- **Phụ kiện gaming**: Chuột, bàn phím, tai nghe
- Mỗi sản phẩm hiển thị:
  - Hình ảnh
  - Tên sản phẩm
  - Thông số kỹ thuật
  - Giá gốc và giá khuyến mãi
  - Phần trăm giảm giá
  - Đánh giá sao
  - Badges (HOT, MỚI, BÁN CHẠY)
  - Nút thêm vào giỏ hàng

### 5. Banner Trả góp
- Thông tin ưu đãi trả góp 0%
- Call-to-action button

### 6. Service Highlights
- 4 điểm nổi bật:
  - Chính hãng 100%
  - Giá tốt nhất
  - Trả góp 0%
  - Bảo hành uy tín

### 7. Footer
- Thông tin công ty
- Social media links
- Links hỗ trợ khách hàng
- Danh mục sản phẩm
- Thông tin liên hệ
- Giờ làm việc

## Responsive Design

Website được thiết kế responsive cho các thiết bị:

- **Mobile** (< 768px): 2 cột sản phẩm, menu thu gọn
- **Tablet** (768px - 1023px): 3-4 cột sản phẩm
- **Desktop** (≥ 1024px): 4-5 cột sản phẩm, hiển thị đầy đủ

## Tích hợp vào Java Project

### Spring Boot / Spring MVC

Đặt files vào thư mục static resources:

```
src/
└── main/
    └── resources/
        └── static/
            ├── index.html
            ├── styles.css
            └── script.js
```

### JSP (Java Server Pages)

Đặt files vào webapp:

```
src/
└── main/
    └── webapp/
        ├── index.html (hoặc index.jsp)
        ├── css/
        │   └── styles.css
        └── js/
            └── script.js
```

Trong JSP, import CSS và JS:

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <!-- Nội dung HTML -->
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>
```

### Servlets

Đặt files trong thư mục WebContent:

```
WebContent/
├── index.html
├── css/
│   └── styles.css
└── js/
    └── script.js
```

## Thư viện sử dụng

- **Lucide Icons**: https://unpkg.com/lucide@latest
  - Thư viện icons miễn phí, nhẹ
  - Được load từ CDN

## Tùy chỉnh

### Thay đổi màu sắc

Trong `styles.css`, tìm và thay đổi:
- Màu đỏ chính: `#dc2626` (bg-red-600)
- Màu đỏ đậm: `#b91c1c` (bg-red-700)
- Màu đỏ hover: `#991b1b` (bg-red-800)

### Thêm/Sửa sản phẩm

Trong `script.js`, chỉnh sửa các mảng:
- `hotDeals`: Sản phẩm giảm giá
- `laptops`: Sản phẩm laptop
- `accessories`: Phụ kiện

Cấu trúc sản phẩm:
```javascript
{
    id: 1,
    name: 'Tên sản phẩm',
    price: 10000000,
    originalPrice: 12000000, // Optional
    image: 'URL hình ảnh',
    rating: 4.5,
    reviews: 100,
    badge: 'HOT', // Optional
    specs: ['Spec 1', 'Spec 2'], // Optional
    discount: 'Trả góp 0%' // Optional
}
```

### Thay đổi carousel

Trong `script.js`, function `initCarousel()`:
- Thời gian auto-play: Thay đổi `4000` (ms) trong `setInterval(nextSlide, 4000)`

## Browser Support

- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)
- IE 11+ (limited support)

## License

© 2026 TechStore. All rights reserved.

## Liên hệ

- Email: support@techstore.vn
- Hotline: 1900 xxxx
- Địa chỉ: 123 Đường Nguyễn Huệ, Quận 1, TP. Hồ Chí Minh
