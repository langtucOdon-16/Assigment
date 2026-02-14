/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// Product Detail Page JavaScript

let currentImageIndex = 0;

// Initialize Product Detail Page
function initProductDetail() {
    initGallery();
    renderRelatedProducts();
}

// Gallery Navigation
function initGallery() {
    const mainImage = document.getElementById('mainImage');
    const thumbnails = document.querySelectorAll('.thumbnail');
    const prevBtn = document.getElementById('galleryPrev');
    const nextBtn = document.getElementById('galleryNext');

    // Thumbnail click
    thumbnails.forEach((thumb, index) => {
        thumb.addEventListener('click', () => {
            currentImageIndex = index;
            updateGalleryImage();
        });
    });

    // Navigation buttons
    if (prevBtn) {
        prevBtn.addEventListener('click', () => {
            currentImageIndex = currentImageIndex > 0 ? currentImageIndex - 1 : galleryImages.length - 1;
            updateGalleryImage();
        });
    }

    if (nextBtn) {
        nextBtn.addEventListener('click', () => {
            currentImageIndex = currentImageIndex < galleryImages.length - 1 ? currentImageIndex + 1 : 0;
            updateGalleryImage();
        });
    }

    function updateGalleryImage() {
        if (mainImage) {
            mainImage.src = galleryImages[currentImageIndex];
            mainImage.alt = galleryImages[currentImageIndex];
        }

        // Update active thumbnail
        thumbnails.forEach((thumb, index) => {
            if (index === currentImageIndex) {
                thumb.classList.add('active');
            } else {
                thumb.classList.remove('active');
            }
        });
    }
    
    updateGalleryImage();
}

function renderRelatedProducts() {
    const container = document.getElementById('relatedProducts');
    if (!container) return;

    // Reuse createProductCard function from main script.js
    if (typeof createProductCard === 'function') {
        container.innerHTML = relatedProducts.map(product => createProductCard(product)).join('');
        
        // Reinitialize icons
        if (typeof lucide !== 'undefined') {
            lucide.createIcons();
        }
    }
}

// Initialize on DOM load
document.addEventListener('DOMContentLoaded', function() {
    initProductDetail();
    
    // Reinitialize icons
    if (typeof lucide !== 'undefined') {
        lucide.createIcons();
    }
});

