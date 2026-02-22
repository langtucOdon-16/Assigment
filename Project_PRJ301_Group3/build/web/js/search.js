/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// Search Page JavaScript

let activeCategory = 'all';
let activeSortOption = 'featured';

// Initialize Search Page
function initSearchPage() {
    initClearSearch();
    initFilterCollapse();
    initBrandFilters();
    initPriceFilters('.category-checkbox-item');
    initPriceFilters('.brand-checkbox-item');
    initPriceFilters('.price-checkbox-item');
}

// Clear Search
function initClearSearch() {
    const clearBtn = document.getElementById('clearSearch');
    const searchInput = document.getElementById('searchInput');
    
    if (clearBtn && searchInput) {
        clearBtn.addEventListener('click', () => {
            searchInput.value = '';
            searchInput.focus();
        });

        // Show/hide clear button based on input
        searchInput.addEventListener('input', () => {
            if (searchInput.value) {
                clearBtn.style.display = 'flex';
            } else {
                clearBtn.style.display = 'none';
            }
        });

        // Initial state
        if (searchInput.value) {
            clearBtn.style.display = 'flex';
        }
    }
}

// Filter Collapse
function initFilterCollapse() {
    const collapseBtns = document.querySelectorAll('.filter-collapse');
    
    collapseBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            const targetId = btn.getAttribute('data-target');
            const target = document.getElementById(targetId);
            
            if (target) {
                target.classList.toggle('collapsed');
                btn.classList.toggle('collapsed');
            }
        });
    });
}

// Brand Filters
function initBrandFilters() {
    const brandItems = document.querySelectorAll('.brand-item-search');
    
    brandItems.forEach(item => {
        item.addEventListener('click', () => {
            item.classList.toggle('active');
            // Here you can add logic to filter by brand
        });
    });

    // Show more/less button
    const showMoreBtn = document.querySelector('.btn-show-more');
    if (showMoreBtn) {
        let isExpanded = true;
        showMoreBtn.addEventListener('click', () => {
            isExpanded = !isExpanded;
            showMoreBtn.textContent = isExpanded ? 'Thu gọn' : 'Xem thêm';
            // Here you can add logic to show/hide brands
        });
    }
}

// Price Filters
function initPriceFilters(classOfCheckbox) {
    const priceCheckboxes = document.querySelectorAll(classOfCheckbox + ' input[type="checkbox"]');
    
    priceCheckboxes.forEach((checkbox, index) => {
        checkbox.addEventListener('change', () => {
            if (index === 0) {
                // "Tất cả" checkbox
                priceCheckboxes.forEach((cb, i) => {
                    if (i !== 0) cb.checked = false;
                });
            } else {
                // Other checkboxes
                priceCheckboxes[0].checked = false;
            }
        });
    });
}

// Initialize on DOM load
document.addEventListener('DOMContentLoaded', function() {
    initSearchPage();
    
    // Reinitialize icons
    if (typeof lucide !== 'undefined') {
        lucide.createIcons();
    }
});

