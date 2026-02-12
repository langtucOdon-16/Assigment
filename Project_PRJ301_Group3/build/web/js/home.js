/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/* 
    Created on : Feb 8, 2026, 8:59:17 AM
    Author     : NamTQ
    Description: JS for Home for computer web 
*/

// Category Menu Toggle
function initCategoryMenu() {
    const categoryBtn = document.getElementById('categoryBtn');
    const categoryDropdown = document.getElementById('categoryDropdown');
    const categoryOverlay = document.getElementById('categoryOverlay');

    function toggleCategoryMenu() {
        const isActive = categoryDropdown.classList.contains('active');
        
        if (isActive) {
            categoryDropdown.classList.remove('active');
            categoryOverlay.classList.remove('active');
        } else {
            categoryDropdown.classList.add('active');
            categoryOverlay.classList.add('active');
        }
    }

    if (categoryBtn) {
        categoryBtn.addEventListener('click', toggleCategoryMenu);
    }

    if (categoryOverlay) {
        categoryOverlay.addEventListener('click', toggleCategoryMenu);
    }
}

// Brand Menu Toggle
function initBrandMenu() {
    const categoryItems = document.querySelectorAll('.category-item');
    const brandDropdown = document.getElementById('brandDropdown');
    const categoryOverlay = document.getElementById('categoryOverlay');
    let preItem = null;
    let preItemCategoryId = null;
    
    function showBrands (categoryId){
        if (preItemCategoryId !== null){
            document.querySelector(`.brand-group[data-category-id="${preItemCategoryId}"]`).style.display = 'none';
        }
        document.querySelector(`.brand-group[data-category-id="${categoryId}"]`).style.display = 'block';
        preItemCategoryId = categoryId;
    }
    
    function toggleBrandMenu(item) {
        const isActive = brandDropdown.classList.contains('active');
        
        if (isActive && item === preItem){
            brandDropdown.classList.remove('active');
            preItem = null;
            item.blur();
        } else {
            brandDropdown.classList.add('active'); 
            preItem = item;
        }
    }
    
    categoryItems.forEach(item => {
        item.addEventListener('click', () => {
            const categoryId = item.dataset.categoryId;
            showBrands(categoryId);
            toggleBrandMenu(item);
        }); 
    });
    
    if (categoryOverlay) {
        categoryOverlay.addEventListener('click', () => brandDropdown.classList.remove('active'));
        preItem = null;
    }
}

// Carousel functionality
function initCarousel() {
    const slides = document.querySelectorAll('.carousel-slide');
    const prevBtn = document.getElementById('carouselPrev');
    const nextBtn = document.getElementById('carouselNext');
    const dotsContainer = document.getElementById('carouselDots');
    
    let currentSlide = 0;
    const totalSlides = slides.length;
    let autoplayInterval;

    // Create dots
    for (let i = 0; i < totalSlides; i++) {
        const dot = document.createElement('button');
        dot.classList.add('carousel-dot');
        if (i === 0) dot.classList.add('active');
        dot.addEventListener('click', () => goToSlide(i));
        dotsContainer.appendChild(dot);
    }

    const dots = document.querySelectorAll('.carousel-dot');

    function updateSlides() {
        slides.forEach((slide, index) => {
            slide.classList.remove('active');
            if (index === currentSlide) {
                slide.classList.add('active');
            }
        });

        dots.forEach((dot, index) => {
            dot.classList.remove('active');
            if (index === currentSlide) {
                dot.classList.add('active');
            }
        });
    }

    function goToSlide(n) {
        currentSlide = n;
        if (currentSlide >= totalSlides) currentSlide = 0;
        if (currentSlide < 0) currentSlide = totalSlides - 1;
        updateSlides();
        resetAutoplay();
    }

    function nextSlide() {
        goToSlide(currentSlide + 1);
    }

    function prevSlide() {
        goToSlide(currentSlide - 1);
    }

    function startAutoplay() {
        autoplayInterval = setInterval(nextSlide, 5000);
    }

    function stopAutoplay() {
        clearInterval(autoplayInterval);
    }

    function resetAutoplay() {
        stopAutoplay();
        startAutoplay();
    }

    if (prevBtn) prevBtn.addEventListener('click', prevSlide);
    if (nextBtn) nextBtn.addEventListener('click', nextSlide);

    // Pause autoplay on hover
    const carouselContainer = document.querySelector('.carousel-container');
    if (carouselContainer) {
        carouselContainer.addEventListener('mouseenter', stopAutoplay);
        carouselContainer.addEventListener('mouseleave', startAutoplay);
    }

    // Start autoplay
    startAutoplay();
}

// Function to set up slider for each item
function setupProductSlider(containerId, prevBtnId, nextBtnId) {
    const container = document.getElementById(containerId);
    const prevBtn = document.getElementById(prevBtnId);
    const nextBtn = document.getElementById(nextBtnId);

    if (!container || !prevBtn || !nextBtn) return;

    const scrollAmount = 280; 

    nextBtn.addEventListener('click', () => {
        container.scrollLeft += scrollAmount;
    });

    prevBtn.addEventListener('click', () => {
        container.scrollLeft -= scrollAmount;
    });
}

document.addEventListener('DOMContentLoaded', function() {
    initCategoryMenu();
    initBrandMenu();
    initCarousel();
    
    setupProductSlider('hotDealsGrid', 'hotDealsPrev', 'hotDealsNext');
    setupProductSlider('hotSellingGrid', 'hotSellingPrev', 'hotSellingNext');
    
    if (typeof lucide !== 'undefined') {
        lucide.createIcons();
    }
});

